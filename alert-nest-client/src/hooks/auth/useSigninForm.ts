// src/hooks/auth/useSigninForm.ts
import { useForm } from "react-hook-form";
import { SignInRequest } from "@/types/auth";
import { useSigninMutation } from "@/hooks";

export const useSigninForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<SignInRequest>();

  const mutation = useSigninMutation();

  const onSubmit = async (data: SignInRequest) => {
    try {
      await mutation.mutateAsync(data);
      alert("Signed in successfully");
      // Optional: redirect to dashboard
    } catch (err: any) {
      alert(err.message);
    }
  };

  return {
    register,
    handleSubmit,
    errors,
    isSubmitting,
    onSubmit,
  };
};
