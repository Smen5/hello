"use client";
import { useUserStore } from "@/store/useUserstore";
import useManageViewModel from "@/viewmodel/useManageViewModel";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { PendingMember } from "./PendingMember";
import style from './page.module.css'
const ManagePage = () => {
    const router = useRouter();
    const { role } = useUserStore();
    useEffect(()=>{
        if(role == "ROLE_PENDING"){
            router.replace("/");
            return;
        }
    },[]);
    const { pendingMember, approveMember } = useManageViewModel();
    return(
    <div>
        <h2 id={style.title}>관리 페이지</h2>
        <h3>승인 대기 멤버 : {pendingMember.length}</h3>
        <div className={style.memberList}>
            {
                pendingMember.map((member, idx)=>(<PendingMember key={idx} approveMember={approveMember} pendingMember={member}/>))
            }
        </div>
    </div>);
}
export default ManagePage;