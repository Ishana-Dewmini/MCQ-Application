import React from 'react'
// import { useNavigate } from 'react-router-dom'

const ErrorComponent = () => {

  // const navigate = useNavigate();

  return (
    <div>
        <h1>Update Player Profile first!!</h1>
        
    </div>
  )
  
}

export default ErrorComponent


// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom';
// import { getResponses, isQuizCompleted } from '../../services/ResponseService';

// const ErrorComponent = () => {
//   const navigate = useNavigate(); // Importing useNavigate here
//   const [status, setStatus] = useState(); // Added a new state variable to store the status of the quiz [completed or not
//   const [answers, setAnswers] = useState();

  

//   useEffect(() => {
//     const id = 1;
//     isQuizCompleted(id).then((response) => {
//       setStatus(response.data.questionnaireTaken);
//     });
//     getResponses(id).then((response) => {
//       setAnswers(response.data);
//     }).catch((error) => {
//       console.error('Error:', error);
//     } );
//   }, []);

//   // function getAllAnswers() {
//   //   isQuizCompleted(2).then((response) => {
//   //     setAnswers(response.data.questionnaireTaken);
//   //   });
//   // }

//   return (
//     <div>
//       <h1>You have already completed the quiz.</h1>
//       <textarea value={status} readOnly></textarea>
//       <textarea value={answers} readOnly></textarea>
//       <button onClick={() => navigate('/review')}>Go to Quiz Review Page</button> {/* Fixed typo in the button text */}
//     </div>
//   );
// };

// export default ErrorComponent;
