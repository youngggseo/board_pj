package study.board_pj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board_pj.domain.Board;
import study.board_pj.domain.BoardCategory;
import study.board_pj.domain.User;
import study.board_pj.domain.dto.BoardCntDto;
import study.board_pj.domain.dto.BoardCreateRequest;
import study.board_pj.domain.dto.BoardDto;
import study.board_pj.repository.BoardRepository;
import study.board_pj.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 특정 카테고리의 게시글 목록을 페이지 요청에 따라 가져옵니다.
    public Page<Board> getBoardList(BoardCategory category, PageRequest pageRequest) {
        return boardRepository.findAllByCategory(category, pageRequest);
    }

    // 특정 ID와 카테고리에 해당하는 게시글을 가져옵니다.
    public BoardDto getBoard(Long boardId, String category) {
        Optional<Board> optBoard = boardRepository.findById(boardId);

        // ID에 해당하는 게시글이 없거나 카테고리가 일치하지 않으면 null을 반환합니다.
        if (optBoard.isEmpty() || !optBoard.get().getCategory().toString().equalsIgnoreCase(category)) {
            return null;
        }

        return BoardDto.of(optBoard.get());
    }

    // 새로운 게시글을 작성합니다.
    @Transactional
    public Long writeBoard(BoardCreateRequest req, BoardCategory category, String loginId, Authentication auth) throws IOException {
        User loginUser = userRepository.findByLoginId(loginId).get();

        Board savedBoard = boardRepository.save(req.toEntity(category, loginUser));

        return savedBoard.getId();
    }

    // 게시글의 카테고리를 가져옵니다.
    public String getCategory(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        return board.getCategory().toString().toLowerCase();
    }

    public List<Board> findMyBoard(String category, String loginId) {
        if (category.equals("board")) {
            return boardRepository.findAllByUserLoginId(loginId);
        }
        return null;
    }
}
