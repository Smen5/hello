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

export async function getFeedList(page: number, size: number){
    try{
        const response = await axios.get("/api/feed",{
            params: {
                page: page,
                size: size,
            }
        });
        return response.data;
    }catch(error){
        console.error("Error fetching feed list:", error);
        throw error;
    }
}
export async function deleteFeed(feedNo: string, token: string){
    try{
        const response = await axios.delete(`/api/feed/${feedNo}`,{
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        })
        return response.data;
    }catch(error){
        console.error("Error fetching feed list:", error);
        throw error;
    }
}
export async function requestDeleteChildFeed(feedNo: string, token: string){
    try{
        const response = await axios.delete(`/api/feed/child/${feedNo}`,{
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        })
        return response.data;
    }catch(error){
        console.error("Error fetching feed list:", error);
        throw error;
    }
}