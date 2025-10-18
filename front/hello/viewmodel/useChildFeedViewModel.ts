import { createChildFeed, createFeed } from "@/service/feedService";
import { useUserStore } from "@/store/useUserstore";
import { useState } from "react";
export function useChildFeedViewModel(feedNo: string) {
    const { token } = useUserStore();
    const [text, setText] = useState("");
    const submitChildFeed = () =>{
        if(!token)return;
        createChildFeed(feedNo,text,token)
        .then(()=>{
            setText("");
            window.location.reload();
        }).catch((error)=>{
            console.error("Feed submission failed:", error);
        });
    }
    return { submitChildFeed, text, setText };
}
