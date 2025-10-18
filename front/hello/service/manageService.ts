import { Member } from "@/model/member";
import axios from "axios";

export async function getPendingMember(token: string): Promise<Member[]> {
    try{
        const response = await axios.get<Member[]>("/api/member/pending",{
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        });
        return response.data;
    }catch(error){
        console.error("Error fetching pending members:", error);
        throw error;
    }
}
export async function patchApproveMember(token: string, uuid: string): Promise<void> {
    try{
        await axios.get(`/api/member/active/${uuid}`,{
            headers: {
                Authorization: token,
            },
            withCredentials: true,
        });
    }catch(error){
        console.error("Error approving member:", error);
        throw error;
    }
}