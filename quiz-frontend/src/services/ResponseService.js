import axios from "axios";

const REST_API_RESPONSE_URL = "http://localhost:8080/api/responses";
const REST_API_USER_URL = "http://localhost:8080/api/user";

export const saveResponses = (userId, responses) => {
    return axios.post(REST_API_RESPONSE_URL + '/'+ userId,responses);
}


export const getResponses = (userId) => {
    return axios.get(REST_API_RESPONSE_URL + '/'+ userId);
}

export const isQuizCompleted = (userId) => {
    return axios.get(REST_API_USER_URL + '/'+ userId);
}


