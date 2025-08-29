// src/components/ProtectedRoute.tsx
import { Navigate } from "react-router-dom";
import { useAuth } from "@/hooks";
import { Spinner } from "@/components";

export const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const { data, isLoading } = useAuth();

  if (isLoading)
    return (
      <div className="flex items-center justify-center min-h-[100px]">
        <Spinner />
      </div>
    ); // or spinner

  if (!data) return <Navigate to="/signin" replace />;

  return <>{children}</>;
};
