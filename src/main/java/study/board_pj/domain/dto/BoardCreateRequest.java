package study.board_pj.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import study.board_pj.domain.Board;
import study.board_pj.domain.BoardCategory;
import study.board_pj.domain.User;

@Data
public class BoardCreateRequest {

    private String title;
    private String body;

    public Board toEntity(BoardCategory category, User user) {
        return Board.builder()
                .user(user)
                .category(category)
                .title(title)
                .body(body)
                .build();
    }
}