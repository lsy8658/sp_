import {
  LoginRequest,
  LoginResponse,
  SignUpRequest,
  SignUpResponse,
} from "@/src/types/auth";
import { api } from "../axios";

export const login = async (data: LoginRequest): Promise<LoginResponse> => {
  const res = await api.post<LoginResponse>("/api/members/login", data);
  return res.data;
};

export const signUp = async (data: SignUpRequest): Promise<SignUpResponse> => {
  const res = await api.post<SignUpResponse>("/api/members/signup", data);
  return res.data;
};

export const isLoggedIn = (): boolean => {
  if (typeof window === "undefined") return false;
  return !!localStorage.getItem("token");
};

export const getToken = (): string | null => {
  if (typeof window === "undefined") return null;
  return localStorage.getItem("token");
};

export const logout = (): void => {
  localStorage.removeItem("token");
};
