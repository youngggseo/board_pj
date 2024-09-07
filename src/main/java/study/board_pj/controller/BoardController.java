//package study.board_pj.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import study.board_pj.domain.BoardCategory;
//import study.board_pj.domain.dto.BoardCreateRequest;
//import study.board_pj.domain.dto.BoardDto;
//import study.board_pj.service.BoardService;
//
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/boards")
//@RequiredArgsConstructor
//public class BoardController {
//
//    private final BoardService boardService;
//
//    @GetMapping("/{category}")
//    public String boardListPage(@PathVariable String category, Model model,
//                                @RequestParam(required = false, defaultValue = "1") int page,
//                                @RequestParam(required = false) String sortType,
//                                @RequestParam(required = false) String searchType,
//                                @RequestParam(required = false) String keyword) {
//        BoardCategory boardCategory = BoardCategory.of(category);
//        if (boardCategory == null) {
//            model.addAttribute("message", "카테고리가 존재하지 않습니다.");
//            model.addAttribute("nextUrl", "/");
//            return "printMessage";
//        }
//
//        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("id").descending());
//        if (sortType != null) {
//            if (sortType.equals("date")) {
//                pageRequest = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());
//            }
//        }
//
//        model.addAttribute("category", category);
//        model.addAttribute("boards", boardService.getBoardList(boardCategory, pageRequest, searchType, keyword));
//        return "boards/list";
//    }
//
//    @GetMapping("/{category}/write")
//    public String boardWritePage(@PathVariable String category, Model model) {
//        BoardCategory boardCategory = BoardCategory.of(category);
//        if (boardCategory == null) {
//            model.addAttribute("message", "카테고리가 존재하지 않습니다.");
//            model.addAttribute("nextUrl", "/");
//            return "printMessage";
//        }
//
//        model.addAttribute("category", category);
//        model.addAttribute("boardCreateRequest", new BoardCreateRequest());
//        return "boards/write";
//    }
//
//    @PostMapping("/{category}")
//    public String boardWrite(@PathVariable String category, @ModelAttribute BoardCreateRequest req,
//                             Authentication auth, Model model) throws IOException {
//        BoardCategory boardCategory = BoardCategory.of(category);
//        if (boardCategory == null) {
//            model.addAttribute("message", "카테고리가 존재하지 않습니다.");
//            model.addAttribute("nextUrl", "/");
//            return "printMessage";
//        }
//
//        Long savedBoardId = boardService.writeBoard(req, boardCategory, auth.getName(), auth);
//        model.addAttribute("message", savedBoardId + "번 글이 등록되었습니다.");
//        model.addAttribute("nextUrl", "/boards/" + category + "/" + savedBoardId);
//        return "printMessage";
//    }
//
//    @GetMapping("/{category}/{boardId}")
//    public String boardDetailPage(@PathVariable String category, @PathVariable Long boardId, Model model,
//                                  Authentication auth) {
//        if (auth != null) {
//            model.addAttribute("loginUserLoginId", auth.getName());
//        }
//
//        BoardDto boardDto = boardService.getBoard(boardId, category);
//        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않는 경우
//        if (boardDto == null) {
//            model.addAttribute("message", "해당 게시글이 존재하지 않습니다");
//            model.addAttribute("nextUrl", "/boards/" + category);
//            return "printMessage";
//        }
//
//        model.addAttribute("boardDto", boardDto);
//        model.addAttribute("category", category);
//        return "boards/detail";
//    }
//}



package study.board_pj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.board_pj.domain.BoardCategory;
import study.board_pj.domain.dto.BoardCreateRequest;
import study.board_pj.domain.dto.BoardDto;
import study.board_pj.service.BoardService;

import java.io.IOException;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{category}")
    public String boardListPage(@PathVariable String category, Model model,
                                @RequestParam(required = false, defaultValue = "1") int page) {
        BoardCategory boardCategory = BoardCategory.of(category);
        if (boardCategory == null) {
            model.addAttribute("message", "카테고리가 존재하지 않습니다.");
            model.addAttribute("nextUrl", "/");
            return "printMessage";
        }

        //PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("createdAt").descending());


        model.addAttribute("category", category);
        model.addAttribute("boards", boardService.getBoardList(boardCategory, pageRequest));
        return "boards/list";
    }

    @GetMapping("/{category}/write")
    public String boardWritePage(@PathVariable String category, Model model) {
        BoardCategory boardCategory = BoardCategory.of(category);

        model.addAttribute("category", category);
        model.addAttribute("boardCreateRequest", new BoardCreateRequest());
        return "boards/write";
    }

    @PostMapping("/{category}")
    public String boardWrite(@PathVariable String category, @ModelAttribute BoardCreateRequest req,
                             Authentication auth, Model model) throws IOException {
        BoardCategory boardCategory = BoardCategory.of(category);

        Long savedBoardId = boardService.writeBoard(req, boardCategory, auth.getName(), auth);
        model.addAttribute("message", savedBoardId + "번 글이 등록되었습니다.");
        model.addAttribute("nextUrl", "/boards/" + category + "/" + savedBoardId);
        return "printMessage";
    }

    @GetMapping("/{category}/{boardId}")
    public String boardDetailPage(@PathVariable String category, @PathVariable Long boardId, Model model,
                                  Authentication auth) {
        BoardDto boardDto = boardService.getBoard(boardId, category);
        // id에 해당하는 게시글이 없거나 카테고리가 일치하지 않는 경우
        if (boardDto == null) {
            model.addAttribute("message", "해당 게시글이 존재하지 않습니다");
            model.addAttribute("nextUrl", "/boards/" + category);
            return "printMessage";
        }

        model.addAttribute("boardDto", boardDto);
        model.addAttribute("category", category);
        return "boards/detail";
    }
}
