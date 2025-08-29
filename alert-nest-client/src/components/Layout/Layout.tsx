import type { PropsWithChildren } from "react";

export const Layout = ({ children }: PropsWithChildren) => (
  <main className="min-h-screen bg-white">{children}</main>
);
