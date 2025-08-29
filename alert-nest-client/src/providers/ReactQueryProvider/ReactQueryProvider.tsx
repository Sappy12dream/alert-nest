import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import type { ChildrenProps } from "@/types";

const queryClient = new QueryClient({
  defaultOptions: { queries: { refetchOnWindowFocus: false } },
});

export const ReactQueryProvider = ({ children }: ChildrenProps) => {
  return (
    <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
  );
};
