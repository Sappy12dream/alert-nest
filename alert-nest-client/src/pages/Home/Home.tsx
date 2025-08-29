// src/pages/Home.tsx
import { Button, Card, CardContent } from "@/components";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/hooks";

export const Home = () => {
  const navigate = useNavigate();
  const { data: user, isLoading } = useAuth();

  if (isLoading) return null; // Or spinner

  const handleClick = () => {
    navigate(user ? "/dashboard" : "/signin");
  };

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center p-4">
      <Card className="max-w-md w-full shadow-lg">
        <CardContent className="text-center space-y-4 p-6">
          <h1 className="text-3xl font-bold text-gray-900">InsightCraft</h1>
          <p className="text-gray-600">
            Unlock insights from your CSV data with ease.
          </p>
          <Button className="w-full" onClick={handleClick}>
            {user ? "Go to Dashboard" : "Get Started"}
          </Button>
        </CardContent>
      </Card>
    </div>
  );
};
