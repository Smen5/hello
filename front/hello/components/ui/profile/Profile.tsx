"use client";
import { useUserStore } from '@/store/useUserstore';
import style from './Profile.module.css'
const ProfileSummery = () => {
    const { uuid, name, avatarUrl, logout } = useUserStore();
    if(!uuid || !name || !avatarUrl){
        return <button className={style.login}>로그인</button>;
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