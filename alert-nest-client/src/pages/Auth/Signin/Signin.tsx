// src/pages/signin.tsx

import { Button, Input } from "@/components";
import { useSigninForm } from "@/hooks";

export const Signin = () => {
  const { register, handleSubmit, onSubmit, errors, isSubmitting } =
    useSigninForm();

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="space-y-4 max-w-md mx-auto mt-10"
    >
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

      <Button type="submit" disabled={isSubmitting}>
        {isSubmitting ? "Signing in..." : "Sign In"}
      </Button>
    </form>
  );
};
