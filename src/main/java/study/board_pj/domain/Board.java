package study.board_pj.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;   // 제목
    private String body;    // 본문

    @Enumerated(EnumType.STRING)
    private BoardCategory category; // 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;      // 작성자
}