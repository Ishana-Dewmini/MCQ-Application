import axios from "axios";

const REST_API_RESPONSE_URL = "http://localhost:8080/energy-quest";
const REST_API_USER_URL = "http://localhost:8080/energy-quest/user";

export const saveResponses = (userId, responses) => {
    return axios.post(REST_API_RESPONSE_URL + '/responses/'+ userId,responses);
}

export const getResponses = (userId) => {
    return axios.get(REST_API_RESPONSE_URL + '/responses/'+ userId);
}

export const isQuizCompleted = (userId) => {
    return axios.get(REST_API_USER_URL + '/id/'+ userId);
}


