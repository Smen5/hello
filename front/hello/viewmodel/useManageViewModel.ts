import { Member } from "@/model/member";
import { patchApproveMember, getPendingMember } from "@/service/manageService";
import { useUserStore } from "@/store/useUserstore";
import { get } from "http";
import { useEffect, useState } from "react";



const useManageViewModel = () => {
    const { token } = useUserStore();
    const [pendingMember, setPendingMember] = useState<Member[]>([]);
    useEffect(()=>{
        getPendingMemberList();
    },[]);
    const getPendingMemberList = async ()=>{
        if(!token) return;
        try{
            const pendingMemberList = await getPendingMember(token);
            setPendingMember(pendingMemberList);
        }catch(error){
            console.error("Failed to get pending members:", error);
        }
    }
    const approveMember = async (uuid: string)=>{
        if(!token) return;
        try{
            await patchApproveMember(token,uuid);
            getPendingMemberList();
        }catch(error){
            console.error("Failed to approve member:", error);
        }
    };

    return { pendingMember, getPendingMemberList, approveMember};
};
export default useManageViewModel;