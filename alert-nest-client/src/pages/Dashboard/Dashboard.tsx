// src/pages/dashboard/DashboardPage.tsx
import { Card, CardContent } from "@/components";

export const Dashboard = () => {
  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <Card className="max-w-3xl mx-auto shadow-md">
        <CardContent className="space-y-4">
          <h1 className="text-2xl font-bold">Welcome,!</h1>
          <p className="text-gray-600">Here's your personalized dashboard.</p>
          {/* Add real components for CSV uploads, queries, stats, etc. */}
        </CardContent>
      </Card>
    </div>
  );
};
