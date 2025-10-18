import { Member } from "@/model/member";
import styles from "./PendingMember.module.css";

interface PendingMemberProps{
    pendingMember: Member;
    approveMember: (uuid: string)=>void;
}
export const PendingMember = ({ pendingMember, approveMember}: PendingMemberProps)=>{
    return(
        <div className={styles.pendingMember}>
            <div className={styles.info}>
                <img src={pendingMember.avatarUrl} alt={`${pendingMember.name}의 아바타`}/>
                <div className={styles.name}>{pendingMember.name}</div>
            </div>
            <button className={styles.approve} onClick={()=>approveMember(pendingMember.uuid)}>승인</button>
        </div>
    );
}