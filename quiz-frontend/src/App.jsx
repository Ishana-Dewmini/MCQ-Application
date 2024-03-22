import {BrowserRouter, Routes, Route} from 'react-router-dom'
import WelComeQuizComponent from './components/WelComeQuizComponent'
import Quiz from './components/Quiz/Quiz'
import { quizQuestions } from './questions/Questions'
import ReviewComponent from './components/ReviewComponent'

function App() {

  return (
    <>
      <BrowserRouter>
          <Routes>
            {/* // http://localhost:3000/ */}
            <Route path="/" element={<Quiz questions={quizQuestions.questions} />} />
            {/* // http://localhost:3000/review */}
            <Route path="/review" element={<ReviewComponent />} />
          </Routes>         
      </BrowserRouter>
    </>
  )
}

export default App
