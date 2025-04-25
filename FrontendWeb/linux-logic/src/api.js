import axios from 'axios';

const api = axios.create({
  baseURL: 'http://5.182.204.159/api',  
  withCredentials: true             
});

export default api;  
