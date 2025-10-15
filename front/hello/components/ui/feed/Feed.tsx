import style from './Feed.module.css'
const Feed =()=>{
    return(
        <div className={style.feed}>
            <div className={style.header}>
                <div className={style.profile}>
                    <img src='https://avatars.githubusercontent.com/u/132333588?s=400&u=cdaabb86defe8c1186f8b13bac3f330c83257d66&v=4'/>
                    <span style={{"fontSize":"1.2rem"}}>
                        김현빈
                    </span>
                </div>
                <div className={style.date}>
                    2025.10.4
                </div>
            </div>
            <div className={style.text}>
                content sectioncontent section
                content section
                content section
                content sectioncontent section
            </div>
        </div>
    );
}
export default Feed;