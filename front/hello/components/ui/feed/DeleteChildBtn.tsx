"use client";
import { useUserStore } from "@/store/useUserstore";

import style from './DelteChildBtn.module.css'
import useDelteFeedViewModel from "@/viewmodel/useDelteFeedViewModel";
import { useChildFeedViewModel } from "@/viewmodel/useChildFeedViewModel";

interface DeleteBtnProps{
    feedNo:string;
    feedAuthorUuid: string;
}

const DelteChildBtn = ({feedNo,feedAuthorUuid}:DeleteBtnProps)=>{
    const {uuid ,deleteChildFeed}=useChildFeedViewModel(feedNo);
    if( uuid !== feedAuthorUuid )return null;
    return(
        <button onClick={()=>deleteChildFeed(feedNo)} className={style.delete}>
            <img src="/delete.svg"/>
        </button>
    )
}
export default DelteChildBtn;