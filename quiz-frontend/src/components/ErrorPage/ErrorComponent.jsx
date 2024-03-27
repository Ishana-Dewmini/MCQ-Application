import React from 'react'
import { useNavigate } from 'react-router-dom'

const ErrorComponent = () => {

  const navigate = useNavigate();


  return (
    <div>
        <h1>You have already completed the quiz.</h1>
        <button onClick={() => navigate('/review')}>Goto Quiz Review Page</button>
    </div>
  )
  
}

export default ErrorComponent