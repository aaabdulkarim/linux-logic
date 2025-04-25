import axios from 'axios';

const api = axios.create({
  baseURL: 'https://www.linux-logic.com/api',  
  withCredentials: true             
});

export default api;  
