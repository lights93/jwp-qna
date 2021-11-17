## 1단계 - 엔티티 매핑
#### 기능 요구사항
**공통사항: `@DataJpaTest` 사용하여 학습테스트**
- [X] 작성된 코드 동작하도록 변경
- [X] answer 엔티티 클래스와 리포지토리 클래스를 작성
- [X] delete_history 엔티티 클래스와 리포지토리 클래스를 작성
- [X] question 엔티티 클래스와 리포지토리 클래스를 작성
- [X] user 엔티티 클래스와 리포지토리 클래스를 작성
- [X] 생성시간, 수정시간 공통부분 작성

#### 코드리뷰 사항
- [X] 줄바꿈을 통해 가독성 향상
- [X] 생성일자 컬럼에 length 추가
- [X] 테스트 코드에 @DisplayName을 활용하여 설명 추가

## 2단계 - 연관 관계 매핑
#### 기능 요구사항
- [X] 답변 테이블에 질문 id로 외래키로 답변 - 질문(N:1) 연관관계 매핑
- [X] 답변 테이블에 작성자 id(유저 id) 외래키로 답변 - 유저(N:1) 연관관계 매핑
- [X] 삭제이력 테이블에 유저 id로 외래키로 삭제이력 - 유저(N:1) 연관관계 매핑
- [X] 질문 테이블에 작성자 id(유저 id) 외래키로 질문 - 유저(N:1) 연관관계 매핑
- [X] 질문 테이블에 질문-답변(1:N) 연관관계 매핑
