package com.taner.cardstore.service;

import com.taner.cardstore.repository.CardRepository;
import com.taner.cardstore.repository.entity.CardEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taner.cardstore.model.Card;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void saveCardDetails(Card card) {

        CardEntity cardEntity = modelMapper.map(card, CardEntity.class);

        cardRepository.save(cardEntity);

    }

    public Card getCardDetails(Long id) {

        return cardRepository.findById(id).map(i -> modelMapper.map(i, Card.class)).orElseThrow(() ->new ResponseStatusException(NOT_FOUND, "Card not found"));
    }




}