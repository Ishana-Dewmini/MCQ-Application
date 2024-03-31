import React, { useState, useEffect } from 'react';
import { useLocation, useParams, useNavigate } from 'react-router-dom';
import { quizQuestions } from '../../questions/Questions';
import { getResponses,isQuizCompleted } from '../../services/ResponseService';
import Button from '@mui/material/Button';
// import '../Quiz/Quiz.scss';
import './Review.scss';

const ReviewComponent = () => {
  const location = useLocation(); 
  const [answers, setAnswers] = useState(location.state.results);
  const [reviewedQuestions, setReviewedQuestions] = useState([]);
  const [quizCompleted, setQuizCompleted] = useState(false);
  const [correctAnswerCount, setCorrectAnswerCount] = useState();
  const [score, setScore] = useState(0);

  const navigate = useNavigate();
  const { id } = useParams();

  async function quizCompletedScoreStatus(id) {
    try {
      const response = await isQuizCompleted(id);
      setQuizCompleted(response.data.questionnaireTaken);
      setScore(response.data.questionnaireScore);
    } catch (error) {
      alert('An error occurred while checking score. Please try again later.');
      console.error('Error checking quiz score:', error); // Log the error
    }
  }

  useEffect(() => {
    quizCompletedScoreStatus(id);
  }, [id]);

  useEffect(() => {

    if (quizCompleted) 
    {
      getResponses(id).then((response) => {
        setAnswers(response.data);
      }).catch((error) => {
        console.error('Error:', error);
      });
    
    const reviewed = answers.map((item,index) => {
      const questionIndex = index; // Adjust index to match array indexing
      const question = quizQuestions.questions[questionIndex].question;
      const choices = quizQuestions.questions[questionIndex].choices;
      const correctAnswer = quizQuestions.questions[questionIndex].correctAnswer;
      const correctAnswerStr = choices[correctAnswer -1];
      const generalFeedback = quizQuestions.questions[questionIndex].generalFeedback;
      const specificFeedback = quizQuestions.questions[questionIndex].specificFeedback[item-1];
      return {
        question,
        choices,
        givenAnswer: choices[item-1],
        correctAnswer: correctAnswerStr,
        answerStatus: item == correctAnswer,
        generalFeedback,
        specificFeedback,
      };
    });

    setReviewedQuestions(reviewed);
    setCorrectAnswerCount(reviewed.filter((item) => item.answerStatus).length);
    }
  }, [quizCompleted, answers]);

  const renderReviewItems = () => {
    return reviewedQuestions.map((item, index) => (
      <div key={index} className="review-item">
        <p className="review-question">{`Question ${index + 1}: ${item.question}`}</p>
        {item.choices ? (
          <ul className="review-choices">
            {item.choices.map((choice, choiceIndex) => (
              <li key={choiceIndex} className={getChoiceClass(item, choice)}>
                {choice}
              </li>
            ))}
          </ul>
        ) : (
          <p>No choices available for this question.</p>
        )}
        <p className="review-given-answer">{`Given Answer: ${item.givenAnswer}`}</p>
        <p className="review-correct-answer">{`Correct Answer: ${item.correctAnswer}`}</p>
        <p className="review-correctness">{`Your answer is ${item.answerStatus ? 'Correct !' : 'Wrong !'}`}</p>
        <p className="review-general-feedback">{`General Feedback: ${item.generalFeedback}`}</p>
        <p className="review-specific-feedback">{`Specific Feedback: ${item.specificFeedback}`}</p>
      </div>
    ));
  };

  
  
  // Function to determine the class for each choice
  const getChoiceClass = (item, choice) => {
    if (item.answerStatus) {
      // If answer is correct
      if (choice === item.correctAnswer) {
        return "correct-answer"; // Apply green background
      }
    } else {
      // If answer is wrong
      if (choice === item.correctAnswer) {
        return "correct-answer"; // Apply green background
      } else if (choice === item.givenAnswer) {
        return "wrong-answer"; // Apply red background
      }
      
    }
    return "other-answer"; // Default class
  };
  
  

  const gameEnvironment = () => {
    
  }


  let wrongAnswerCount = reviewedQuestions.length - correctAnswerCount;
  
  return (
    <div className='text-center'>
        <div className="review-header">
          <h1>Result</h1>
          <br />
          <p>
            Total Questions: <span>{reviewedQuestions.length}</span>
          </p>
          <p>
            Total Score: <span>{isNaN(score) ? 0 : score}</span>
          </p>
          <p>
            Correct Answers: <span>{isNaN(correctAnswerCount) ? 0 : correctAnswerCount}</span>
          </p>
          <p>
            Wrong Answers: <span>{isNaN(wrongAnswerCount) ? 0 : wrongAnswerCount}</span>
          </p>

        </div>
      <div className="review-container">
          <h2>Review Answers:</h2>
          {renderReviewItems()}
        
        <center>
          <Button variant="contained" color="success" onClick={gameEnvironment}>
          Go to Game Environment
          </Button> 
        </center>
      </div>
    </div>
  );
};

export default ReviewComponent;
