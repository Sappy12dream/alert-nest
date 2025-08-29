import { useForm } from "react-hook-form";
import { SignUpRequest } from "@/types/auth";
import { useSignupMutation } from "@/hooks";

export const useSignupForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<SignUpRequest>();

  const signupMutation = useSignupMutation();

  const onSubmit = async (data: SignUpRequest) => {
    try {
      await signupMutation.mutateAsync(data);
      alert("Signed up successfully");
      // Optional: redirect or toast
    } catch (err: any) {
      alert(err.message); // OR use toast.error(err.message);
    }
  };

  return {
    register,
    handleSubmit,
    errors,
    isSubmitting: signupMutation.isPending, // use mutation state
    onSubmit,
  };
};
