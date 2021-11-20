package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("작성자가 맞는지 확인")
    @Test
    void validateOwner_success() {
        Q1.validateOwner(UserTest.JAVAJIGI);
    }

    @DisplayName("작성자가 아닐 때 에러")
    @Test
    void validateOwner_fail() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> {
                Q1.validateOwner(UserTest.SANJIGI);
            })
            .withMessage("질문을 삭제할 권한이 없습니다.");
    }
}
