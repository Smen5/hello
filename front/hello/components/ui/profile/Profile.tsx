"use client";
import { useUserStore } from '@/store/useUserstore';
import style from './Profile.module.css'
const ProfileSummery = () => {
    const { name, avatarUrl, role, } = useUserStore();
    if(!role || !name || !avatarUrl){
        return(<a href="/oauth2/authorization/github">
                    <button className={style.login}>로그인</button>
                </a>)
    }
    return(
        <div className={style.profile}>
            <img className={style.profile} src={avatarUrl}/>
            <span style={{"fontSize":"1.2rem"}}>
                {name}
            </span>
        </div>
    )
};
export default ProfileSummery;