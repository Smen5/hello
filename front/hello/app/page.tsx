import Image from "next/image";
import styles from "./page.module.css";
import TextareaAutosize from "@mui/material/TextareaAutosize";

export default function Home() {
  return (
    <div className={styles.textSection}>
    <TextareaAutosize
          minRows={3}
          placeholder="내용을 입력해 주세요.."
          style={{
            padding: "5px",
            border: "1px solid #ccc",
            borderRadius: "4px",
            fontSize: "16px",
            outline: "none",
            resize: "none",
          }}
        />
    </div>
  );
}