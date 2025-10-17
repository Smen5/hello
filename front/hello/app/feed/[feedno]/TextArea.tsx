"use client";
import SubmitBtn from "@/components/ui/submitBtn/SubmitBtn";
import { TextareaAutosize } from "@mui/material";
import style from './page.module.css'
import { useUserStore } from "@/store/useUserstore";
import { useChildFeedViewModel } from "@/viewmodel/useChildFeedViewModel";

interface FeedInputProps {
  authorUuid: string;
  feedNo: string;
}

const TextArea = ({authorUuid,feedNo}:FeedInputProps)=>{
    const { uuid } = useUserStore();
    const { submitChildFeed, text, setText }=useChildFeedViewModel(feedNo);
    return(
        <>
        {(authorUuid == uuid) && <div className={style.fieldContainer}>
            <TextareaAutosize
                value={text}
                onChange={(e) => setText(e.target.value)}
                minRows={3}
                placeholder="과정을 입력해 주세요"
                style={{
                padding: "0.5rem",
                border: "1px solid #ccc",
                borderRadius: "4px",
                fontSize: "1rem",
                outline: "none",
                resize: "none",
                width: "100%",
                }}
            />
            <div id={style.submitBtn} onClick={submitChildFeed}>
                <SubmitBtn/>
            </div>
        </div>}
        </>
    )
}
export default TextArea;