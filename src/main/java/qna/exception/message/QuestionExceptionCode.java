package qna.exception.message;

public enum QuestionExceptionCode {

    REQUIRED_WRITER("작성자는 반드시 입력돼야 합니다."),
    REQUIRED_TITLE("제목은 반드시 입력돼야 합니다."),
    ALREADY_DELETED("이미 삭제된 질문입니다."),
    NOT_MATCH_LOGIN_USER("로그인한 사용자와 답변자가 일치하지 않습니다."),
    NOT_FOUND_QUESTION("일치하는 질문 데이터가 없습니다.");

    private static final String TITLE = "[ERROR] ";
    private String message;

    QuestionExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}