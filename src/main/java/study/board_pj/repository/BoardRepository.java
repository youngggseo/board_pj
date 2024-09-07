package study.board_pj.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board_pj.domain.Board;
import study.board_pj.domain.BoardCategory;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 페이지 요청에 따라 특정 카테고리의 게시글을 조회합니다.
    Page<Board> findAllByCategory(BoardCategory category, PageRequest pageRequest);
    // 제목에 특정 문자열이 포함된 게시글을 페이지 요청에 따라 조회합니다.
    List<Board> findAllByUserLoginId(String loginId);
    // 특정 카테고리의 게시글 수를 계산합니다.
    Long countAllByCategory(BoardCategory category);
}
