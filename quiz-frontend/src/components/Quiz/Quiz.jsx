import React, { useEffect } from 'react'
import { useState} from 'react'
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import { quizCompleted, saveResponses } from '../../services/ResponseService';
import { useParams } from 'react-router-dom';

import './Quiz.scss'


const Quiz = ({ questions }) => {
  const [currentQuestion, setCurrentQuestion] = useState(1)
  const [answerIdx, setAnswerIdx] = useState(null)
  const [reviewData, setReviewData] = useState([])

  const {id} = useParams();

  const navigate = useNavigate();

  const { question, choices, correctAnswer } = questions[currentQuestion-1]


  const onAnswerClick = (selectedIndex) => {
    setAnswerIdx(selectedIndex)
  }

  const onClickNext = () => {
    const selectedAnswer = answerIdx +1;
    setAnswerIdx(null);
    reviewData.push(selectedAnswer);
  
    setReviewData(reviewData);
      
    if (currentQuestion < questions.length) {
      setCurrentQuestion((prev) => prev + 1);
    } 
    else {
      //setCurrentQuestion(1);
      // saveResponses(id,reviewData).then((response) => {
      //   console.log(response.data)
      //   quizCompleted(id)
      //   navigate(`/review/${id}`, { state: { results: reviewData } });
      // }).catch(error => {
      //     console.error(error);
      // })
      navigate(`/review/${id}`, { state: { results: reviewData } });
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
