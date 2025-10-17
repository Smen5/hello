import { createFeed } from "@/service/feedService";
import { useUserStore } from "@/store/useUserstore";
import { useState } from "react";

export function useFeedViewModel() {
    const { token } = useUserStore();
    const [text, setText] = useState("");
    const submitFeed = () =>{
        if(!token)return;
        createFeed(text,token)
        .then(()=>{
            setText("");
        }).catch((error)=>{
            console.error("Feed submission failed:", error);
        });
    }
    return {submitFeed, text, setText};
}
