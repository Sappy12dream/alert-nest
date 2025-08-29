import { Hello } from "@/types";
import { useApiQuery } from "./useApiQuery";

export const useHealthQuery = () => {
  return useApiQuery<Hello>(["baseUrl"], "/");
};
