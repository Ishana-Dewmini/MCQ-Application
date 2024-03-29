import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { isQuizCompleted } from '../../services/ResponseService';

const ErrorComponent = () => {
  const navigate = useNavigate(); // Importing useNavigate here

  const [answers, setAnswers] = useState();
  useEffect(() => {
    isQuizCompleted(2).then((response) => {
      setAnswers(response.data.questionnaireTaken);
    });
  }, []);

  // function getAllAnswers() {
  //   isQuizCompleted(2).then((response) => {
  //     setAnswers(response.data.questionnaireTaken);
  //   });
  // }

  return (
    <div>
      <h1>You have already completed the quiz.</h1>
      <textarea value={answers} readOnly></textarea>
      <button onClick={() => navigate('/review')}>Go to Quiz Review Page</button> {/* Fixed typo in the button text */}
    </div>
  );
};

export default ErrorComponent;


// import React from 'react'
// import { useNavigate } from 'react-router-dom'

// const ErrorComponent = () => {

//   const navigate = useNavigate();

  


//   return (
//     <div>
//         <h1>You have already completed the quiz.</h1>
//         {/* <button onClick={() => navigate('/review')}>Goto Quiz Review Page</button> */}
//     </div>
//   )
  
// }

// export default ErrorComponent