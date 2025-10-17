import style from './Feed.module.css'

interface Author {
    uuid: string;
    name: string;
    avatarUrl: string;
}

interface FeedData {
    author: Author;
    no: number;
    createdAt: string;
    text: string;
}

const Feed =({ feedData }: { feedData: FeedData })=>{
    const { author, text, createdAt } = feedData;
    const formattedDate = new Date(createdAt).toLocaleDateString();
    return(
        <div className={style.feed}>
            <div className={style.header}>
                <div className={style.profile}>
                    <img src={author?.avatarUrl} alt={author?.name}/>
                    <span style={{"fontSize":"1.2rem"}}>
                        {author?.name}
                    </span>
                </div>
                <div className={style.date}>
                    {formattedDate}
                </div>
            </div>
            <div className={style?.text}>
                {text}
            </div>
        </div>
    );
}
export default Feed;