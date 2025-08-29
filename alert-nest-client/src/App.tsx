import { ReactQueryProvider } from "@/providers";
import { AppRouter } from "@/router";
import { Layout } from "@/components";

const App = () => {
  return (
    <ReactQueryProvider>
      <Layout>
        <AppRouter />
      </Layout>
    </ReactQueryProvider>
  );
};

export default App;
