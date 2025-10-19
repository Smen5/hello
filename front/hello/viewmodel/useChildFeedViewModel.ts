import { createChildFeed, createFeed, requestDeleteChildFeed } from "@/service/feedService";
import { useUserStore } from "@/store/useUserstore";
import { useState } from "react";
export function useChildFeedViewModel(feedNo: string) {
    const { uuid ,token } = useUserStore();
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
    
    const deleteChildFeed = (childFeedNo: string)=>{
        if(!token)return;
        const isConfirmed = window.confirm("정말로 이 글을 삭제하시겠습니까?");
        if(!isConfirmed)return;
        requestDeleteChildFeed(childFeedNo,token)
        .then(()=>{
            window.location.reload();
        }).catch((error)=>{
            console.error("Child Feed deletion failed:", error);
        });
    }
    return { submitChildFeed, text, setText , deleteChildFeed ,uuid};
}
