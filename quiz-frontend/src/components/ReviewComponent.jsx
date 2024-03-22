import React, { useState } from 'react'
import { useLocation } from 'react-router-dom'
import './Quiz/Quiz.scss'

const ReviewComponent = () => {
  const location = useLocation(); 
  const [answers, setAnswers] = useState(location.state.results);

  const gameEnvironment = () => {
    
  }

  let correctAnswerCount = answers.filter(e => e.answer == true).length;
  let wrongAnswerCount = answers.filter(e => e.answer == false).length;

  console.log(answers);
  return (
    <div className='text-center'>
      <div className="quiz-container">

          <h1>Result</h1>
          <p>
            Total Questions: <span>{answers.length}</span>
          </p>
          <p>
            Total Score: <span>{correctAnswerCount*10}</span>
          </p>
          <p>
            Correct Answers: <span>{correctAnswerCount}</span>
          </p>
          <p>
            Wrong Answers: <span>{wrongAnswerCount}</span>
          </p>
          <button onClick={gameEnvironment}>Goto Game Environmet</button>
        </div>
    </div>
  )
}

export default ReviewComponent