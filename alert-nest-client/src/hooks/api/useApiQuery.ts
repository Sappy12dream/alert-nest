// src/hooks/api/useApiQuery.ts
import { fetcher } from "@/utils";
import { useQuery, UseQueryOptions, QueryKey } from "@tanstack/react-query";

export const useApiQuery = <T>(
  key: QueryKey,
  url: string,
  options?: Omit<UseQueryOptions<T, Error, T, QueryKey>, "queryKey" | "queryFn">
) =>
  useQuery<T, Error>({
    queryKey: key,
    queryFn: () => fetcher<T>(url),
    refetchOnWindowFocus: false,
    ...options,
  });
