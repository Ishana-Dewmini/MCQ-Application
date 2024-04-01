// import axios from "axios";

// const REST_API_RESPONSE_URL = "http://localhost:8080/energy-quest";
// const REST_API_USER_URL = "http://localhost:8080/energy-quest/user";

// export const saveResponses = (userId, responses) => {
//     return axios.post(REST_API_RESPONSE_URL + '/responses/'+ userId,responses);
// }

// export const getResponses = (userId) => {
//     return axios.get(REST_API_RESPONSE_URL + '/responses/'+ userId);
// }

// export const isQuizCompleted = (userId) => {
//     return axios.get(REST_API_USER_URL + '/id/'+ userId);
// }

// export const quizCompleted = (userId) => {
//     return axios.post(REST_API_USER_URL + '/questionnaire/'+ userId);
// }


// export const setQuizScore = (userId, score) => {
//     return axios.post(REST_API_USER_URL + '/score/'+ userId+'/'+score);
// }

import axios from "axios";

const REST_API_RESPONSE_URL = "http://localhost:8080/energy-quest";
const REST_API_USER_URL = "http://localhost:8080/energy-quest/user";
const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSYWphbmEgS2F2aW5kYSJ9.aVTGnmIStMAqZ9tvqZ-zCsCWVebzqkCT_czs9lugQUmB3lzcZXqD59KRvOwA562Fp7YciGYtg794fHACzcpvQg";

// Function to set the Authorization header with the JWT token
const setAuthToken = () => {
    
    axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    
};

export const saveResponses = (userId, responses) => {
    setAuthToken(token);
    return axios.post(REST_API_RESPONSE_URL + '/responses/'+ userId, responses);
}

export const getResponses = (userId) => {
    setAuthToken(token);
    return axios.get(REST_API_RESPONSE_URL + '/responses/'+ userId);
}

export const isQuizCompleted = (userId) => {
    setAuthToken(token);
    return axios.get(REST_API_USER_URL + '/id/'+ userId);
}

export const quizCompleted = (userId) => {
    setAuthToken(token);
    return axios.post(REST_API_USER_URL + '/questionnaire/'+ userId);
}

export const setQuizScore = (userId, score) => {
    setAuthToken(token);
    return axios.post(REST_API_USER_URL + '/score/'+ userId+'/'+score);
}



