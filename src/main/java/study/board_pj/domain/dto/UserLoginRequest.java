package study.board_pj.domain.dto;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String loginId;
    private String password;
}
