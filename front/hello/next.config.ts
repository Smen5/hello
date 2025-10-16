const nextConfig = {
  experimental: {
    appDir: true, // app 디렉토리 활성화
  },
  reactStrictMode: true,
  // 필요하면 rewrites도 추가
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://localhost:8081/api/:path*",
      },
      {
        source: "/oauth2/:path*",
        destination: "http://localhost:8081/oauth2/:path*",
      }
    ];
  },
};

export default nextConfig;
