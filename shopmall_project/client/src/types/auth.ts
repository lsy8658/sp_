export interface LoginRequest {
  email: string;
  password: string;
}

export type LoginResponse = string;

export interface SignUpRequest {
  email: string;
  password: string;
  nickname: string;
}

export type SignUpResponse = number;
