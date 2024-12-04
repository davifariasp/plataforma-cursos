import { api } from "@/services/api";
import Cookies from "js-cookie";

export interface LoginData {
  login: string;
  senha: string;
}

export interface LoginResponse {
  user: {
    nome: string;
    cpf: string;
  };
  token: string;
  expiresIn: number;
}

export const KEY_CACHE_TOKEN = "authToken";
export const KEY_CACHE_USER = "user";

export async function login(data: LoginData): Promise<LoginResponse> {
  try {
    const response = await api.auth.login(data);
    return {
      user: response.user,
      token: response.accessToken,
      expiresIn: response.expiresIn,
    };
  } catch (error: any) {
    throw new Error("Erro ao fazer login");
  }
}

export function setToken(accessToken: string) {
  Cookies.set(KEY_CACHE_TOKEN, accessToken, {
    secure: true,
    sameSite: "strict",
  });
}

export function setUser(user: LoginResponse["user"]) {
  Cookies.set(KEY_CACHE_USER, JSON.stringify(user), {
    secure: true,
    sameSite: "lax",
  });
}

export function getUser() {
  const user = Cookies.get(KEY_CACHE_USER);

  if (user) {
    return JSON.parse(user);
  }

  return null;
}

export function getToken(name: string) {
  return Cookies.get(name);
}

export function clearToken() {
  Cookies.remove(KEY_CACHE_TOKEN);
  Cookies.remove(KEY_CACHE_USER);
}
