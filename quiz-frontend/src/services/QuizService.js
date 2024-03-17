import axios from 'axios';

const QUIZ_API_BASE_URL = "http://localhost:8080/energy-quest/quizzes";

export function getQuizzes() {
    return axios.get(QUIZ_API_BASE_URL);
}

