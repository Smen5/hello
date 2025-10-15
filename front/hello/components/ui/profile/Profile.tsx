import style from './Profile.module.css'
const ProfileSummery = () => {
    return(
        <div className={style.profile}>
            <img className={style.profile} src='https://avatars.githubusercontent.com/u/132333588?s=400&u=cdaabb86defe8c1186f8b13bac3f330c83257d66&v=4'/>
            <span style={{"fontSize":"1.2rem"}}>
                김현빈
            </span>
        </div>
    )	
};
export default ProfileSummery;