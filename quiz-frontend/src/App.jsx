import {BrowserRouter, Routes, Route} from 'react-router-dom'
import { quizQuestions } from './questions/Questions'
import ReviewComponent from './components/Review/ReviewComponent'
import ErrorComponent from './components/ErrorPage/ErrorComponent'
import WelComeQuizComponent from './components/Welcome/WelComeQuizComponent'
import Quiz from './components/Quiz/Quiz'


function App() {


  return (
    <>
      <BrowserRouter>
          <Routes>
            {/* // http://localhost:3000/ */}
            <Route path="/:token/:id" element={<WelComeQuizComponent />} />
            {/* // http://localhost:3000/quiz/1 */}
            <Route path="/quiz/:id" element={<Quiz questions={quizQuestions.questions} />} />
            {/* // http://localhost:3000/review/1 */}
            <Route path="/review/:id" element={<ReviewComponent />} />
            {/* // http://localhost:3000/errorPage */}
            <Route path="/errorPage" element={<ErrorComponent />} /> 

            {/* // http://localhost:3000/ */}
            {/* <Route path="/" element={<ErrorComponent />} /> */}
           
          </Routes>         
      </BrowserRouter>
    </>
  )
}

export default App
