import style from './SubmitBtn.module.css'
const SubmitBtn = () => {
    return(
        <button className={style.submitBtn}>
            <img className={style.submitBtn} src="/send.svg" alt="Send" width={24} height={24} />
        </button>
    )	
};
export default SubmitBtn;