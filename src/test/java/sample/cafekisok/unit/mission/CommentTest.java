package sample.cafekisok.unit.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentTest {


    @BeforeEach
    void setUp() {
        //    1. 사용자1 생성에 필요한 내용 준비
        //    2. 사용자1 생성
        //    3. 게시물 생성에 필요한 내용 준비
        //    4. 게시물 생성
    }

    @DisplayName("사용자가 댓글을 작성할 수 있다.")
    @Test
    void writeComment() {

        // given
        //    5. 댓글 생성에 필요한 내용 준비

        // when
        //    6. 댓글 생성


        // then

    }

    @DisplayName("사용자가 댓글을 수정할 수 있다.")
    @Test
    void updateComment() {

        // given
        //    2-5. 댓글 생성에 필요한 내용 준비
        //    2-6. 댓글 생성

        // when
        //    2-7. 댓글 수정

        // then

    }

    @DisplayName("자신이 작성한 댓글이 아니면 수정할 수 없다.")
    @Test
    void cannotUpdateCommentWhenUserIsNotWriter() {

        // given
        //    1. 사용자2 생성에 필요한 내용 준비
        //    2. 사용자2 생성
        //    3. 사용자1의 댓글 생성에 필요한 내용 준비
        //    4. 사용자1의 댓글 생성

        // when
        //3-9. 사용자2가 사용자1의 댓글 수정 시도

        // then

    }
}

