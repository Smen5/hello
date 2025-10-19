import Feed from "@/components/ui/feed/Feed";
import ChildFeed from "@/components/ui/feed/ChildFeed";
import axios from "axios";
import TextArea from "./TextArea";
import DeleteBtn from "@/components/ui/feed/DeleteBtn";


interface Author {
    uuid: string;
    name: string;
    avatarUrl: string;
}

interface FeedData {
    author: Author;
    no: string;
    createdAt: string;
    text: string;
    childFeeds: ChildFeedData[];
}

interface ChildFeedData {
    no: string;
    createdAt: string;
    text: string;
}

interface FeedPageProps {
  params: { feedno: string };
}

async function getFeed(feedno: string): Promise<FeedData> {
    const res = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/api/feed/${feedno}`);
    return res.data;
}
export default async function FeedPage(props: FeedPageProps){
    const params = await props.params;
    const feedData = await getFeed(params.feedno);
    return(
    <div>
        <Feed feedData={feedData}/>
        
        <TextArea authorUuid={feedData.author.uuid} feedNo={params.feedno}/>
        {
            feedData.childFeeds.map((childFeed, index)=>(
                <ChildFeed key={index} feed={childFeed} parentAuthorUuid={feedData.author.uuid}/>
            ))
        }
    </div>
    );
}