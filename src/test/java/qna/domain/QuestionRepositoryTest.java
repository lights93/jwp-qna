package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    private User user;
    private LocalDateTime startTime;

    @BeforeEach
    void setUp() {
        startTime = LocalDateTime.now();
        user = new User(1L, "userId", "password", "name", "email");
    }

    @DisplayName("질문 저장")
    @Test
    void save() {
        Question expected = new Question("title", "contents").writeBy(user);

        Question actual = questionRepository.save(expected);

        assertAll(
            () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
            () -> assertThat(actual.getContents()).isEqualTo(expected.getContents()),
            () -> assertThat(actual.getTitle()).isEqualTo(expected.getTitle()),
            () -> assertThat(actual.getWriterId()).isEqualTo(expected.getWriterId()),
            () -> assertThat(actual.getCreatedAt()).isAfter(startTime),
            () -> assertThat(actual.getUpdatedAt()).isAfter(startTime)
        );
    }

    @DisplayName("삭제되지 않은 질문 조회")
    @Test
    void findByDeletedFalse() {
        Question expected1 = new Question("title1", "contents1").writeBy(user);
        Question expected2 = new Question("title2", "contents2").writeBy(user);
        Question deletedQuestion = new Question("title3", "contents3").writeBy(user);
        deletedQuestion.setDeleted(true);
        questionRepository.save(expected1);
        questionRepository.save(expected2);
        questionRepository.save(deletedQuestion);

        List<Question> notDeletedQuestions = questionRepository.findByDeletedFalse();

        assertIterableEquals(notDeletedQuestions, Arrays.asList(expected1, expected2));
    }

    @DisplayName("삭제되지 않은 질문 id로 조회")
    @Test
    void findByIdAndDeletedFalse_exists() {
        Question expected = new Question("title", "contents").writeBy(user);
        questionRepository.save(expected);

        Question actual = questionRepository.findByIdAndDeletedFalse(1L).get();

        assertAll(
            () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
            () -> assertThat(actual.getContents()).isEqualTo(expected.getContents()),
            () -> assertThat(actual.getTitle()).isEqualTo(expected.getTitle()),
            () -> assertThat(actual.getWriterId()).isEqualTo(expected.getWriterId()),
            () -> assertThat(actual.getCreatedAt()).isAfter(startTime),
            () -> assertThat(actual.getUpdatedAt()).isAfter(startTime)
        );
    }

    @DisplayName("삭제되지 않은 질문 id로 조회하여 존재하지 않는 케이스")
    @Test
    void findByIdAndDeletedFalse_notExists() {
        Question question = new Question("title", "contents").writeBy(user);

        questionRepository.save(question);
        question.setDeleted(true);

        Optional<Question> actual = questionRepository.findByIdAndDeletedFalse(1L);

        assertThat(actual.isPresent()).isFalse();
    }
}