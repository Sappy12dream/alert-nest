// src/types/auth

export type SignUpRequest = {
  name: string;
  email: string;
  password: string;
  accountName: string;
};

export type SignInRequest = {
  email: string;
  password: string;
};

export type AuthResponse = string;

export type MeResponse = {
  id: string;
  email: string;
  name: string;
};
