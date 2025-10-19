import DelteChildBtn from './DeleteChildBtn';
import style from './Phase.module.css'

interface ChildFeedData {
    no: string;
    createdAt: string;
    text: string;
}
interface ChildFeedProps {
    parentAuthorUuid: string;
    feed: ChildFeedData;
}
const ChildFeed =({ feed ,parentAuthorUuid }: ChildFeedProps)=>{
    const formattedDate = new Date(feed.createdAt).toLocaleDateString();
    return(
        <>
            
            <div className={style.date}>{formattedDate}</div>
            <div className={style.text}>
                <DelteChildBtn feedNo={feed.no} feedAuthorUuid={parentAuthorUuid}/>
                {feed.text}
            </div>
        </>
    )
}
export default ChildFeed;