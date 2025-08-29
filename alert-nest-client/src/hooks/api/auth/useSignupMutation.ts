// src/hooks/api/auth/useSignup.ts
import { AuthResponse, SignUpRequest } from "@/types";
import { useApiMutation } from "@/hooks";

export const useSignupMutation = () =>
  useApiMutation<AuthResponse, SignUpRequest>("/api/v1/auth/signup");
