import { TextareaAutosize } from "@mui/material";
import SubmitBtn from "../submitBtn/SubmitBtn";
import styles from './TextField.module.css'
const TextField=()=>{
    return(
    <div className={styles.textSection}>
        <TextareaAutosize
            minRows={3}
            placeholder="내용을 입력해 주세요"
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
        <div id={styles.submitBtn}>
            <SubmitBtn/>
        </div>
    </div>
    )
}
export default TextField;