import { useUserStore } from "@/store/useUserstore";
import axios from "axios";

export async function createFeed(text: string , token: string){
    try{
        const response = await axios.post("/api/feed",
        {
            text: text,
        },
        {
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        });
        return response.data;
    }catch(error){
        console.error("Error creating feed:", error);
        throw error;
    }
}

export async function createChildFeed(feedNo: string, text: string , token: string){
    try{
        const response = await axios.post(`/api/feed/${feedNo}`,
        {
            text: text,
        },
        {
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        });
        return response.data;
    }catch(error){
        console.error("Error creating feed:", error);
        throw error;
    }
}