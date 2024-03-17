import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import WelComeQuizComponent from './components/WelComeQuizComponent'
function App() {

  return (
    <>
      <BrowserRouter>
          <Routes>
            // http://localhost:3000/
            <Route path="/" element={<WelComeQuizComponent />} />
          </Routes>      
      </BrowserRouter>
    </>
      
  )
}

export default App
