import Feed from "@/components/ui/feed/Feed";
import Phase from "@/components/ui/feed/Phase";
import { TextareaAutosize, TextField } from "@mui/material";
import style from './page.module.css'
import SubmitBtn from "@/components/ui/submitBtn/SubmitBtn";
const FeedPage =()=>{
    return(
    <div>
        <Feed/>
        <div className={style.fieldContainer}>
            <TextareaAutosize
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

        <div id={style.submitBtn}>
            <SubmitBtn/>
        </div>
        </div>
        <Phase/>
        <Phase/>
    </div>
    );
}
export default FeedPage;