package study.board_pj.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardCntDto {

    private Long totalBoardCnt;
    private Long totalGreetingCnt;
    private Long totalFreeCnt;
}

