"use client";
import { useUserStore } from "@/store/useUserstore";
import axiosInstance from "@/util/axiosInstance";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

interface UserResponse {
  uuid: string;
  name: string;
  role: string;
  avatarUrl: string;
}

const OauthRedirectPage = () => {
  const router = useRouter();
  const setUser = useUserStore((state) => state.setUser);

  useEffect(() => {
    const handleOAuthRedirect = async () => {
      const params = new URLSearchParams(window.location.search);
      const tokenParam = params.get("token");

      if (!tokenParam) {
        router.push("/oauth2/invalid");
        return;
      }

      // 토큰 저장
      localStorage.setItem("token", tokenParam);

      try {
        // 서버에서 유저 정보 가져오기
        axios.get("/api/member/me",{
            headers: {
                Authorization: tokenParam,
            },
            withCredentials: true,
        })
        .then((response) => {
            const user: UserResponse = response.data;
            setUser(tokenParam, user.name, user.avatarUrl, user.uuid, user.role);
        })
        .catch((error) => {console.log(error);});
        
        router.push("/");
      } catch (err) {
        console.error(err);
        router.push("/oauth2/invalid");
      }
    };

    handleOAuthRedirect(); // 즉시 실행
  }, [router, setUser]);

  return <div>OAuth2 redirect processing...</div>;
};

export default OauthRedirectPage;