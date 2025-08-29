// src/hooks/useAuth.ts
import { useApiQuery } from "@/hooks/api/useApiQuery";
import { MeResponse } from "@/types";

export const useAuth = () =>
  useApiQuery<MeResponse>(["auth", "me"], "/api/v1/me", {
    staleTime: 5 * 60 * 1000, // 5 mins
    retry: false,
  });
