import React, { useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { quizQuestions } from '../../questions/Questions';
import '../Quiz/Quiz.scss';

const ReviewComponent = () => {
  const location = useLocation(); 
  const [answers, setAnswers] = useState(location.state.results);
  const [reviewedQuestions, setReviewedQuestions] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    // if (id) 
    // {
    //   getAnswers(id).then((data) => {
    //     setAnswers(data);
    //   }).catch((error) => {
    //     console.error('Error:', error);
    //   });
    // }
    console.log(answers);

    const reviewed = answers.map(item => {
      const questionIndex = item.question - 1; // Adjust index to match array indexing
      const question = quizQuestions.questions[questionIndex].question;
      const choices = quizQuestions.questions[questionIndex].choices;
      const GeneralFeedback = quizQuestions.questions[questionIndex].GeneralFeedback;
      //const SpecificFeedback = quizQuestions.questions[questionIndex].SpecificFeedback[];
      return {
        question,
        choices,
        answerStatus: item.answer,
        givenAnswer: item.selectedAnswer,
        correctAnswer: quizQuestions.questions[questionIndex].correctAnswer,
        GeneralFeedback,
        //SpecificFeedback
      };
    });

    setReviewedQuestions(reviewed);
  }, [answers]);
  
  const renderReviewItems = () => {
    return reviewedQuestions.map((item, index) => (
      <div key={index} className="review-item">
        <p className="review-question">{`Question ${index + 1}: ${item.question}`}</p>
        {item.choices ? (
          <ul className="review-choices">
            {item.choices.map((choice, choiceIndex) => (
              <li key={choiceIndex}>{choice}</li>
            ))}
          </ul>
        ) : (
          <p>No choices available for this question.</p>
        )}
        <p className="review-given-answer">{`Given Answer: ${item.givenAnswer}`}</p>
        <p className="review-correct-answer">{`Correct Answer: ${item.correctAnswer}`}</p>
        <p className="review-correctness">{`Your answer is ${item.answerStatus ? 'correct' : 'wrong'}`}</p>
        <p className="General-feedback">{`General Feedback: ${item.GeneralFeedback}`}</p>
        <p className="Specific-feedback">{`Specific Feedback: ${item.givenAnswer}`}</p>
      </div>
    ));
  };
  

  const gameEnvironment = () => {
    
  }

  let correctAnswerCount = answers.filter(e => e.answer == true).length;
  let wrongAnswerCount = answers.filter(e => e.answer == false).length;

  return (
    <div className='text-center'>
      <div className="quiz-container">
        <h1>Result</h1>
        <p>
          Total Questions: <span>{reviewedQuestions.length}</span>
        </p>
        <p>
          Total Score: <span>{correctAnswerCount * 10}</span>
        </p>
        <p>
          Correct Answers: <span>{correctAnswerCount}</span>
        </p>
        <p>
          Wrong Answers: <span>{wrongAnswerCount}</span>
        </p>
        <div className="review-section">
          <h2>Review Answers:</h2>
          {renderReviewItems()}
        </div>
        <button onClick={gameEnvironment}>Go to Game Environment</button>
      </div>
    </div>
  );
};

export default ReviewComponent;
