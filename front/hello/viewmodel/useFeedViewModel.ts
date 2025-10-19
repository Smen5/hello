import { createFeed, getFeedList } from "@/service/feedService";
import { useUserStore } from "@/store/useUserstore";
import { useCallback, useEffect, useRef, useState } from "react";

export function useFeedViewModel() {
    const { token } = useUserStore();
    const [text, setText] = useState("");
    const submitFeed = () =>{
        if(!token)return;
        createFeed(text,token)
        .then(()=>{
            setText("");
            window.location.reload();
        }).catch((error)=>{
            console.error("Feed submission failed:", error);
        });
    }
    return {submitFeed, text, setText};
}
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
}

export function useFeedList(size: number) {
    const [feeds, setFeeds] = useState<FeedData[]>([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(true);
    const [loading, setLoading] = useState(false);
    const observerRef = useRef<HTMLDivElement | null>(null);

    const loadMoreFeeds = useCallback(async () => {
        if (loading || !hasMore) return;

        setLoading(true);
        try {
        const newFeeds = await getFeedList(page, size);

        if (newFeeds.length === 0) {
            setHasMore(false);
        } else {
            setFeeds((prev) => [...prev, ...newFeeds]);
            setPage((prev) => prev + 1);
        }
        } catch (error) {
            console.error("Failed to load feed list:", error);
        } finally {
            setLoading(false);
        }
    }, [page, hasMore, loading, size]);
    useEffect(() => {
        if (!observerRef.current || !hasMore) return;

        const observer = new IntersectionObserver((entries) => {
        const target = entries[0];
        if (target.isIntersecting) {
            loadMoreFeeds();
        }
        });

        observer.observe(observerRef.current);
        return () => observer.disconnect();
    }, [loadMoreFeeds, hasMore]);

    return { feeds, loadMoreFeeds, hasMore, loading , observerRef };
}