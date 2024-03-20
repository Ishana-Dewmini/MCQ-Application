import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import WelComeQuizComponent from './components/WelComeQuizComponent'
import Quiz from './components/Quiz'
import { quizQuestions } from './questions/Questions'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'

function App() {

  return (
    <>
      <BrowserRouter>
      <HeaderComponent />
          <Routes>
            // http://localhost:3000/
            <Route path="/" element={<Quiz questions={quizQuestions.questions} />} />
          </Routes>   
      <FooterComponent />       
      </BrowserRouter>
    </>
      
  )
}

export default App
