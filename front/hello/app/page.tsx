import styles from "./page.module.css";
import TextareaAutosize from "@mui/material/TextareaAutosize";
import SubmitBtn from "@/components/ui/submitBtn/SubmitBtn";

export default function Home() {
  return (
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
        }}
      />
      <div id={styles.submitBtn}>
        <SubmitBtn/>
      </div>
    </div>
  );
}