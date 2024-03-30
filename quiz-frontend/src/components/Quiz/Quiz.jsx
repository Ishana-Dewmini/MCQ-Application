import React, { useEffect } from 'react'
import { useState} from 'react'
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import { quizCompleted, saveResponses, setQuizScore } from '../../services/ResponseService';
import { useParams } from 'react-router-dom';

import './Quiz.scss'


const Quiz = ({ questions }) => {
  const [currentQuestion, setCurrentQuestion] = useState(1)
  const [answerIdx, setAnswerIdx] = useState(null)
  const [reviewData, setReviewData] = useState([])
  const [score, setScore] = useState(0)

  const {id} = useParams();
  const navigate = useNavigate();

  const { question, choices, correctAnswer } = questions[currentQuestion-1]

  useEffect(() => {
    console.log("Review Data:(in useEffect) ", reviewData);
    const newScore = reviewData.reduce((acc, answer) => {
      return answer === correctAnswer ? acc + 10 : acc;
    }, 0);
    console.log("New Score: ", newScore);
    setScore(prevScore => prevScore + newScore);
}, [correctAnswer]);


  const onAnswerClick = (selectedIndex) => {
    setAnswerIdx(selectedIndex)
  }


  const onClickNext = () => {
    const selectedAnswer = answerIdx + 1;
    setAnswerIdx(null);
    reviewData.push(selectedAnswer);
  
    setReviewData(reviewData);
    
    if (currentQuestion < questions.length) {
      setCurrentQuestion((prev) => prev + 1);
    } else {
      setCurrentQuestion(1);
      saveResponses(id, reviewData).then(() => {
        // Upon successful saving of responses
        quizCompleted(id).then(() => {
          console.log("Final Score:(outside) ", score);
          setQuizScore(id, score).then(() => {
            navigate(`/review/${id}`, { state: { results: reviewData } });
          }).catch(error => {
            console.error('Error setting quiz score:', error);
          });
        }).catch(error => {
          console.error('Error marking quiz as completed:', error);
        });
      }).catch(error => {
        console.error('Error saving responses:', error);
      });
    }
  };
  



  return (
    
      <div className='quiz-container'>
        <span className='active-question-no'>{currentQuestion }</span>
        <span className='total-question'>/{ questions.length }</span>
        <h2>{ question }</h2>
        <ul>
        { 
          choices.map((choice, index) => (
                <li
                  onClick={() => onAnswerClick(index)}
                  key={choice}
                  className={answerIdx === index ? "selected-answer" : null}
                >
                  {choice}
                </li>
              ))}
        </ul>

        <center>
          <Button variant="contained" color="primary" onClick={onClickNext} disabled = {answerIdx === null}>
              {currentQuestion == questions.length ? "Finish" : "Next"}
          </Button> 
        </center>

      </div>  
      
  )
}

export default Quiz
