import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8081/",
  timeout: 5000, // 필요하면 타임아웃 설정
});

// 요청 인터셉터: 로컬스토리지 토큰 자동 추가
axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    if (!config.headers) {
      config.headers = {} as unknown as import("axios").AxiosRequestHeaders;
    }
    (config.headers as Record<string, string>)["Authorization"] = token;
  }
  return config;
});

export default axiosInstance;