package com.taner.cardstore.controller;

import com.taner.cardstore.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taner.cardstore.model.Card;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<String> addCardDetails(@Valid @RequestBody Card card) {

        cardService.saveCardDetails(card);

        return new ResponseEntity<>("Card details added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardDetails(@PathVariable Long id) {

        Card card = cardService.getCardDetails(id);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}