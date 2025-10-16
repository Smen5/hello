"use client";
import { useRouter } from "next/navigation";
import { useEffect } from "react";

const oauthRedirectPage = ()=>{
    const router = useRouter();

    useEffect(()=>{
        const params = new URLSearchParams(window.location.search);
        const tokenParam = params.get("token");
        if(tokenParam){
            localStorage.setItem("token", tokenParam as string);
            router.push("/");
        }else{
            router.push("/oauth2/invalid");
        }
    },[router])

    return(
        <div>oauth2 redirect page</div>
    )
}
export default oauthRedirectPage;