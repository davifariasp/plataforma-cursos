import axios from "axios";
import { useAuth } from "./auth";

const authApi = axios.create({
  baseURL: "http://localhost:8081",
});

const courseApi = axios.create({
  baseURL: "http://localhost:8082",
});

// Add request interceptor to include auth token
courseApi.interceptors.request.use((config) => {
  const { token } = useAuth.getState();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const api = {
  auth: {
    login: async (credentials: { login: string; senha: string }) => {
      const response = await authApi.post("/pessoa/login", credentials);
      return response.data;
    },
    register: async (userData: any) => {
      const response = await authApi.post("/pessoa", userData);
      return response.data;
    },
  },
  courses: {
    list: async () => {
      const response = await courseApi.get("/curso");
      return response.data;
    },
    listInscricoes: async (cpf: string) => {
      const response = await courseApi.get(`/inscricao/${cpf}`);
      return response.data;
    },
    enroll: async (credentials: {idCurso: string, cpf: string}) => {
      const response = await courseApi.post(`/inscricao`, credentials);
      return response.data;
    },
  },
};
