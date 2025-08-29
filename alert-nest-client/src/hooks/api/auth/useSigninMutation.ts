// src/api/auth/useSignin.ts
import { AuthResponse, SignInRequest } from "@/types";
import { useApiMutation } from "@/hooks";

export const useSigninMutation = () =>
  useApiMutation<AuthResponse, SignInRequest>("/api/v1/auth/signin");
