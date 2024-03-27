import {BrowserRouter, Routes, Route} from 'react-router-dom'
import WelComeQuizComponent from './components/Welcome/WelComeQuizComponent'
import Quiz from './components/Quiz/Quiz'
import { quizQuestions } from './questions/Questions'
import ReviewComponent from './components/Review/ReviewComponent'
import ErrorComponent from './components/ErrorPage/ErrorComponent'

function App() {

  return (
    <>
      <BrowserRouter>
          <Routes>
            {/* // http://localhost:3000/ */}
            <Route path="/" element={<WelComeQuizComponent />} />
            {/* // http://localhost:3000/quiz */}
            <Route path="/quiz" element={<Quiz questions={quizQuestions.questions} />} />
            {/* // http://localhost:3000/review */}
            <Route path="/review" element={<ReviewComponent />} />
            {/* // http://localhost:3000/errorPage */}
            <Route path="/errorPage" element={<ErrorComponent />} />
          </Routes>         
      </BrowserRouter>
    </>
  )
}

export default App
