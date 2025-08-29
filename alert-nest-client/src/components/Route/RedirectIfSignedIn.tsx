// src/components/RedirectIfSignedIn.tsx
import { Navigate } from "react-router-dom";
import { useAuth } from "@/hooks";
import { Spinner } from "@/components";

export const RedirectIfSignedIn = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const { data, isLoading } = useAuth();

  if (isLoading)
    return (
      <div className="flex items-center justify-center min-h-[100px]">
        <Spinner />
      </div>
    );

  if (data) return <Navigate to="/dashboard" replace />;

  return <>{children}</>;
};
