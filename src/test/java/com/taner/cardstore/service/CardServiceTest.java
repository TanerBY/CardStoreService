package com.taner.cardstore.service;

import com.taner.cardstore.model.Card;
import com.taner.cardstore.repository.CardRepository;
import com.taner.cardstore.repository.entity.CardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    CardRepository cardRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    CardService cardService;

    @Test
    public void shouldSaveCardDetails () {

        Card card = Card.builder().cardHolder("Taner").cardNumber("123").cvv("1234").expirationDate("10/10").build();
        CardEntity cardEntity = new CardEntity();
        when(modelMapper.map(card, CardEntity.class)).thenReturn(cardEntity);

        cardService.saveCardDetails(card);

        Mockito.verify(cardRepository).save(cardEntity);
    }

    @Test
    public void shouldGetCardDetails () {

        Card card = Card.builder().cardHolder("Taner").cardNumber("123").cvv("1234").expirationDate("10/10").build();
        CardEntity cardEntity = new CardEntity();
        when(cardRepository.findById(1L)).thenReturn(Optional.of(cardEntity));
        when(modelMapper.map(cardEntity, Card.class)).thenReturn(card);

        Card cardToVerify = cardService.getCardDetails(1L);
        assertEquals(cardToVerify,card);
    }

    @Test
    public void shouldGetExceptionIfCannotFindCard () {

        when(cardRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,
                ()->{cardService.getCardDetails(1L);} );

    }


}
