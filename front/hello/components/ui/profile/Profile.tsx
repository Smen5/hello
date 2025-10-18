"use client";
import { useUserStore } from '@/store/useUserstore';
import style from './Profile.module.css'
import Link from 'next/link';
const ProfileSummery = () => {
    const { name, avatarUrl, role, } = useUserStore();
    if(!role || !name || !avatarUrl){
        return(<a href="/oauth2/authorization/github">
                    <button className={style.login}>로그인</button>
                </a>)
    }
    return(
        <div className={style.profile}>
            {role == "ROLE_MEMBER" && 
                <Link href="/manage" style={{marginRight:"1rem"}}>
                    <button className={style.login}>관리 페이지</button>
                </Link>}
            <img className={style.profile} src={avatarUrl}/>
            <span style={{"fontSize":"1rem" , "marginRight":"0.5rem"}}>
                {role == "ROLE_PENDING" && <span>(승인 대기 중)</span>}
            </span>
            <span style={{"fontSize":"1.2rem"}}>
                {name}
            </span>
        </div>
    )
};
export default ProfileSummery;