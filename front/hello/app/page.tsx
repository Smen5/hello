"use client";
import styles from "./page.module.css";
import TextareaAutosize from "@mui/material/TextareaAutosize";
import SubmitBtn from "@/components/ui/submitBtn/SubmitBtn";
import Feed from "@/components/ui/feed/Feed";
import { useUserStore } from '@/store/useUserstore';
import { useFeedViewModel } from "@/viewmodel/useFeedViewModel";
export default function Home() {
  const { role } = useUserStore();
  const{text, setText, submitFeed}=useFeedViewModel();
  return (
    <>{(role == 'ROLE_MEMBER') && <div className={styles.textSection}>
        <TextareaAutosize
          value={text}
          onChange={(e) => setText(e.target.value)}
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
        <div id={styles.submitBtn} onClick={submitFeed}>
          <SubmitBtn/>
        </div>
      </div>}
    </>
  );
}