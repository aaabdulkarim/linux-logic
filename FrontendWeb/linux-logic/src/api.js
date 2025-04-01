import axios from 'axios';

const api = axios.create({
  baseURL: 'http://10.0.107.50:8000',  
  withCredentials: true             
});

export default api;  
