// src/router/AppRouter.tsx
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { Dashboard, Home, Signin, Signup } from "@/pages";
import { ProtectedRoute, RedirectIfSignedIn } from "@/components";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/signup",
    element: (
      <RedirectIfSignedIn>
        <Signup />
      </RedirectIfSignedIn>
    ),
  },
  {
    path: "/signin",
    element: (
      <RedirectIfSignedIn>
        <Signin />
      </RedirectIfSignedIn>
    ),
  },
  {
    path: "/dashboard",
    element: (
      <ProtectedRoute>
        <Dashboard />
      </ProtectedRoute>
    ),
  },
]);

export const AppRouter = () => <RouterProvider router={router} />;
