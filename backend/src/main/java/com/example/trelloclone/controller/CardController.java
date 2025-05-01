package com.example.trelloclone.controller;

import com.example.trelloclone.model.Card;
import com.example.trelloclone.model.ListEntity;
import com.example.trelloclone.repository.CardRepository;
import com.example.trelloclone.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ListRepository listRepository;

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        if (card.getList() != null) {
            Long listId = card.getList().getId();
            ListEntity list = listRepository.findById(listId).orElse(null);
            if (list == null) {
                return ResponseEntity.badRequest().build();
            }
            card.setList(list);
        }
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return cardRepository.findById(id)
                .map(card -> ResponseEntity.ok().body(card))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card cardDetails) {
        return cardRepository.findById(id)
                .map(card -> {
                    card.setTitle(cardDetails.getTitle());
                    card.setDescription(cardDetails.getDescription());
                    Card updatedCard = cardRepository.save(card);
                    return ResponseEntity.ok().body(updatedCard);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        return cardRepository.findById(id)
                .map(card -> {
                    cardRepository.delete(card);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
