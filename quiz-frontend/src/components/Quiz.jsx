import React from 'react'
import { useState } from 'react'
import Button from '@mui/material/Button';
import { resultInitalState } from '../questions/Questions';
import FooterComponent from './FooterComponent';
import { useNavigate } from 'react-router-dom';

const Quiz = ({ questions }) => {
  const [currentQuestion, setCurrentQuestion] = useState(0)
  const [answerIdx, setAnswerIdx] = useState(null)
  const [answer, setAnswer] = useState(null)
  const [result, setResult] = useState(resultInitalState)
  const [showResult, setShowResult] = useState(false)

  const navigate = useNavigate();
  const { question, choices, correctAnswer } = questions[currentQuestion]

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
        score: prev.score + 5 ,
        correctAnswers: prev.correctAnswers + 1
      } : {
        ...prev,
        wrongAnswers: prev.wrongAnswers + 1
      }
      );

    if (currentQuestion < questions.length - 1) {
      setCurrentQuestion((prev) => prev + 1);
    } 
    else {
      navigate('/review');
      setCorrectQuestion(0);
      setShowResult(true);
    }

  };

  return (
      <div className='quiz-container'>
        <span className='active-question-no'>{currentQuestion + 1 }</span>
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
              {currentQuestion == questions.length - 1 ? "Finish" : "Next"}
          </Button> 
        </center>
      </div>     
  )
}

export default Quiz