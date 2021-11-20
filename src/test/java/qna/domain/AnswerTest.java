package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("작성자 일치")
    @Test
    void validateOwner_success() {
        A1.validateOwner(UserTest.JAVAJIGI);
    }

    @DisplayName("작성자 불일치시 에러")
    @Test
    void validateOwner_fail() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.validateOwner(UserTest.SANJIGI))
            .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
