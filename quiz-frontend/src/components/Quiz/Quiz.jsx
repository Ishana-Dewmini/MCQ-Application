import React from 'react'
import { useState} from 'react'
import Button from '@mui/material/Button';
import { resultInitalState } from '../../questions/Questions';
import { useNavigate } from 'react-router-dom';
import './Quiz.scss'


const Quiz = ({ questions }) => {
  const [currentQuestion, setCurrentQuestion] = useState(1)
  const [answerIdx, setAnswerIdx] = useState(null)
  const [answer, setAnswer] = useState()
  const [result, setResult] = useState(resultInitalState)
  const [reviewData, setReviewData] = useState([])

  const navigate = useNavigate();

  const { question, choices, correctAnswer } = questions[currentQuestion-1]

  const onAnswerClick = (answer, index) => {
    setAnswerIdx(index)
    if (answer === correctAnswer) {
      setAnswer(true);
    } else {
      setAnswer(false);
    }
  }

  const onClickNext = () => {

    setAnswerIdx(null);
    setResult((prev) => 
      answer
      ? { 
        ...prev, 
        correctAnswers: prev.correctAnswers + 1
      } : {
        ...prev,
        wrongAnswers: prev.wrongAnswers + 1
      }
      );

    reviewData.push({
      question : currentQuestion ,
      answer : answer
    })
  
    setReviewData(reviewData);
      
    if (currentQuestion < questions.length) {
      setCurrentQuestion((prev) => prev + 1);
    } 
    else {
      setCurrentQuestion(1);
      navigate('/review', { state: { results: reviewData } });
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
                  onClick={() => onAnswerClick(choice, index)}
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
