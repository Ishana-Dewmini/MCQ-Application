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

    const reviewed = answers.map((item,index) => {
      const questionIndex = index; // Adjust index to match array indexing
      const question = quizQuestions.questions[questionIndex].question;
      const choices = quizQuestions.questions[questionIndex].choices;
      const correctAnswer = quizQuestions.questions[questionIndex].correctAnswer
      const correctAnswerStr = choices[correctAnswer -1];
      return {
        question,
        choices,
        givenAnswer: choices[item-1],
        correctAnswer: correctAnswerStr,
        answerStatus: item === correctAnswer
      };
    });

    setReviewedQuestions(reviewed);
  }, [answers]);
  
  const renderReviewItems = () => {
    console.log(reviewedQuestions);
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
      </div>
    ));
  };
  

  const gameEnvironment = () => {
    
  }

  let correctAnswerCount = reviewedQuestions.filter(e => e.answerStatus == true).length;
  let wrongAnswerCount = reviewedQuestions.filter(e => e.answerStatus == false).length;

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
