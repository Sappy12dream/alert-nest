// src/hooks/api/useApiMutation.ts
import { fetcher } from "@/utils";
import { useMutation, UseMutationOptions } from "@tanstack/react-query";

export const useApiMutation = <TData, TVariables>(
  url: string,
  options?: UseMutationOptions<TData, Error, TVariables>
) =>
  useMutation<TData, Error, TVariables>({
    mutationFn: async (data: TVariables) =>
      fetcher<TData>(url, {
        method: "POST",
        body: JSON.stringify(data),
      }),
    ...options,
  });
