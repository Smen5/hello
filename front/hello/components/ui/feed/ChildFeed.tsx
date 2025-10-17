import style from './Phase.module.css'

interface ChildFeedData {
    createdAt: string;
    text: string;
}
interface ChildFeedProps {
  feed: ChildFeedData;
}
const ChildFeed =({ feed }: ChildFeedProps)=>{
    const formattedDate = new Date(feed.createdAt).toLocaleDateString();
    return(
        <>
            <div className={style.date}>{formattedDate}</div>
            <div className={style.text}>
                {feed.text}
            </div>
        </>
    )
}
export default ChildFeed;