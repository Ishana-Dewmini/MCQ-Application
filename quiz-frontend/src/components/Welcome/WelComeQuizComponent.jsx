import React, { useEffect,useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";
import Button from '@mui/material/Button';
import WelcomeAnim from '../WelcomeAnimation/Anim';
import { isQuizCompleted, getResponses, tokenAssign } from '../../services/ResponseService';
import './Welcome.scss';


const WelcomeQuizComponent = () => {
  const navigate = useNavigate();
  const [showAnim, setShowAnim] = useState(true);
  const [quizCompleted, setQuizCompleted] = useState(false);
  const [profileCompleted, setProfileCompleted] = useState(true);
  const [reviewData, setReviewData] = useState([]);
  

  const { token, id } = useParams();
 

  async function quizCompletedStatus(id) {
    try {
      const response = await isQuizCompleted(id);
      setQuizCompleted(response.data.questionnaireTaken);
      setProfileCompleted(response.data.profileEdited);
    } catch (error) {
      alert('An error occurred while checking quiz completion. Please try again later.');
      console.error('Error checking quiz completion:', error); // Log the error
    }
  }

  useEffect(() => {
    tokenAssign(token);
    quizCompletedStatus(id);

    if (quizCompleted && profileCompleted) {    
          getResponses(id).then((response) => {
          setReviewData(response.data);
          navigate(`/review/${id}`, { state: { results: reviewData } });
        }).catch((error) => { 
          console.error('Error:', error);
        });
      }

    else if (!profileCompleted)
    {
      alert('Update Player Profile first!!');
      navigate('/errorPage');
    }

    else {

        document.querySelector('body').style.overflow = 'hidden';
        document.querySelector('body').scrollTo(0, 0);

        setTimeout(() => {
          
        document.querySelector('body').style.overflow = 'auto';
        setShowAnim(false);
      
        }, 3000);
    }
  }, [quizCompleted,profileCompleted])



  const handleBegin = () => {
    navigate(`/quiz/${id}`);
  };

  return (
    <>
    <WelcomeAnim showAnim={showAnim}/>

    <div>
      <h1 className='container1'>
        Welcome to <span>Energy Saving Game Questionnaire</span>
      </h1>

      <p className='container2'>
        In this quiz, you'll be presented with 10 multiple-choice questions (MCQs) covering topics such as reducing power consumption, utilizing renewable energy, and participating in demand response programs. Each question is designed to test your knowledge and awareness of these important energy-related concepts.
        <br />
        <br />
        Here's how it works:
        <br />
        1. <strong>Questions:</strong> You'll encounter 10 MCQs one by one. Read each question carefully and select the answer you believe is correct. Remember, there's no penalty for wrong answers, so feel free to give it your best shot!
        <br />
        2. <strong>Answers and Scores:</strong> After answering all the questions, you'll be able to view your results. You'll see which questions you got right and which ones you got wrong, along with the correct answers. For each correct answer, you'll earn 10 marks.
        <br />
        3. <strong>Coins:</strong> The marks you earn in this quiz will be converted into coins in your game environment. These coins can be used for various purposes within the game, helping you progress further and achieve your goals.
        <br />
        4. <strong>One Chance Only:</strong> Keep in mind that you only get one chance to take this questionnaire. Once you've completed it, you won't be able to retake it. So make the most of this opportunity to test your knowledge and earn some coins for your game!
        <br />
        <br />
        Are you ready to put your energy knowledge to the test and earn some coins? Let's get started! Good luck! 🌟
      </p>
    <center>
      <Button variant="contained" color="success" onClick={handleBegin} style={{ marginBottom: '50px' }}>
        Begin Test
      </Button>
    </center>
      
    </div>


    </>
  );
};

export default WelcomeQuizComponent;












  // async function getQuizCompleted(id) {
  //   try {
  //     const response = await isQuizCompleted(id);
  //     console.log(response.data);
  //     setQuizCompleted(response.data.questionnaireTaken);
  //     console.log("Quiz completed? " + response.data.questionnaireTaken); // Log the updated value
  //   } catch (error) {
  //     console.error('Error:', error);
  //   }
  // }

  // useEffect(() => {
  //   const {id}  = 1;
  //   getQuizCompleted(id); // Call the async function
  
  //   if (quizCompleted) {    
  //     getResponses(id).then((data) => {
  //       setReviewData(data);
  //       console.log(data);
  //       navigate(`/review/${id}`, { state: { results: reviewData } }); // Use the 'data' received in the callback
  //     }).catch((error) => { 
  //       console.error('Error:', error);
  //     });
  //   } else {
  //     document.querySelector('body').style.overflow = 'hidden';
  //     document.querySelector('body').scrollTo(0, 0);
  
  //     setTimeout(() => {
  //       document.querySelector('body').style.overflow = 'auto';
  //       setShowAnim(false);
  //     }, 3000);
  //   }
  // }, [id, quizCompleted]); // Add 'id' and 'quizCompleted' to the dependency array
  