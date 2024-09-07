package study.board_pj.domain.dto;

import lombok.Data;
import study.board_pj.domain.User;

import java.time.LocalDateTime;

@Data
public class UserJoinRequest {

    //@NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .nickname(nickname)
                .createdAt(LocalDateTime.now())
                .receivedLikeCnt(0)
                .build();
    }
}
