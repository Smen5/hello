"use client";
import { useUserStore } from "@/store/useUserstore";

import style from './DelteBtn.module.css'
import useDelteFeedViewModel from "@/viewmodel/useDelteFeedViewModel";

interface DeleteBtnProps{
    feedNo:string;
    feedAuthorUuid: string;
}

const DeleteBtn = ({feedNo,feedAuthorUuid}:DeleteBtnProps)=>{
    const { uuid , submitdeleteFeed} = useDelteFeedViewModel(feedNo);
    if( uuid !== feedAuthorUuid )return null;
    return(
        <button onClick={submitdeleteFeed} className={style.delete}>
            <img src="/delete.svg"/>
        </button>
    )
}
export default DeleteBtn;