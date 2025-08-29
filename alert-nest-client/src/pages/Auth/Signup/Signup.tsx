// src/pages/signup

import { Button, Input } from "@/components";
import { useSignupForm } from "@/hooks/auth/useSignupForm";

export const Signup = () => {
  const { register, handleSubmit, onSubmit, errors, isSubmitting } =
    useSignupForm();

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="space-y-4 max-w-md mx-auto mt-10"
    >
      <Input
        placeholder="Name"
        {...register("name", { required: "Name is required" })}
        error={errors.name?.message}
      />

      <Input
        placeholder="Email"
        type="email"
        {...register("email", { required: "Email is required" })}
        error={errors.email?.message}
      />

      <Input
        placeholder="Password"
        type="password"
        {...register("password", { required: "Password is required" })}
        error={errors.password?.message}
      />

      <Input
        placeholder="Company Name"
        {...register("accountName", { required: "Company name is required" })}
        error={errors.accountName?.message}
      />

      <Button type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Signing up..." : "Sign Up"}
      </Button>
    </form>
  );
};
