import { API_URL } from "@/consts";

type ApiResponse<T> = {
  data?: T;
  error?: {
    status: number;
    message: string;
    code: string;
  };
};

export const fetcher = async <T>(
  url: string,
  options?: RequestInit
): Promise<T> => {
  const res = await fetch(`${API_URL}${url}`, {
    ...options,
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
      ...(options?.headers || {}),
    },
  });

  const json: ApiResponse<T> = await res.json();

  if (!res.ok || json.error) {
    const message = json.error?.message || res.statusText;
    const status = json.error?.status || res.status;
    const code = json.error?.code || "UNKNOWN";
    throw new Error(`API error ${status}: ${message} [${code}]`);
  }

  if (json.data === undefined) {
    throw new Error(`Invalid API response: no 'data' field.`);
  }

  return json.data;
};
