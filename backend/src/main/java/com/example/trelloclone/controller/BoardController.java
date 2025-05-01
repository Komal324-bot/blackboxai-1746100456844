package com.example.trelloclone.controller;

import com.example.trelloclone.model.Board;
import com.example.trelloclone.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardRepository.findById(id)
                .map(board -> ResponseEntity.ok().body(board))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board boardDetails) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(boardDetails.getTitle());
                    Board updatedBoard = boardRepository.save(board);
                    return ResponseEntity.ok().body(updatedBoard);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    boardRepository.delete(board);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
