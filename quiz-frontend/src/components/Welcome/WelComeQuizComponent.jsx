import React from 'react'
import { useNavigate } from "react-router-dom";

const WelComeQuizComponent = () => {
  const navigate = useNavigate();

  const handleBegin = () => {
    navigate("/quiz")
  };

  return (
    <div>
      <h1>
        Welcome to <span>Bert Quiz</span>
      </h1>

      <button
        onClick={handleBegin}
      >
        Begin Test
      </button>
    </div>
  );
}

export default WelComeQuizComponent