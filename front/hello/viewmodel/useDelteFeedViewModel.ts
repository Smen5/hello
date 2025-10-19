import { deleteFeed } from "@/service/feedService";
import { useUserStore } from "@/store/useUserstore";
import { useRouter } from "next/navigation";

const useDelteFeedViewModel = (feedNo:string) => {
    const { uuid ,token } = useUserStore();
    const router = useRouter();
    const submitdeleteFeed = async() =>{
        if(!token)return;
        const isConfirmed = window.confirm("정말로 이 글을 삭제하시겠습니까?\n 하위 글까지 전부 삭제 됩니다.");
        if(!isConfirmed)return;
        deleteFeed(feedNo, token)
        .then(()=>{
            router.push('/');
        }).catch((error)=>{
            console.error("Feed deletion failed:", error);
        });
    }

    return { uuid ,submitdeleteFeed};
}
export default useDelteFeedViewModel;