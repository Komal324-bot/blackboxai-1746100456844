package com.example.trelloclone.controller;

import com.example.trelloclone.model.ListEntity;
import com.example.trelloclone.model.Board;
import com.example.trelloclone.repository.ListRepository;
import com.example.trelloclone.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@CrossOrigin(origins = "http://localhost:3000")
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    public List<ListEntity> getAllLists() {
        return listRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity list) {
        if (list.getBoard() != null) {
            Long boardId = list.getBoard().getId();
            Board board = boardRepository.findById(boardId).orElse(null);
            if (board == null) {
                return ResponseEntity.badRequest().build();
            }
            list.setBoard(board);
        }
        ListEntity savedList = listRepository.save(list);
        return ResponseEntity.ok(savedList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Long id) {
        return listRepository.findById(id)
                .map(list -> ResponseEntity.ok().body(list))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@PathVariable Long id, @RequestBody ListEntity listDetails) {
        return listRepository.findById(id)
                .map(list -> {
                    list.setTitle(listDetails.getTitle());
                    ListEntity updatedList = listRepository.save(list);
                    return ResponseEntity.ok().body(updatedList);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id) {
        return listRepository.findById(id)
                .map(list -> {
                    listRepository.delete(list);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
