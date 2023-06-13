package com.taner.cardstore.repository;

import com.taner.cardstore.repository.entity.CardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureTestDatabase
public class CardRepositoryTest {
    @Autowired
    CardRepository cardRepository;

    @Test
    public void shouldSaveCardDetails () {
        CardEntity entity = new CardEntity();
        entity.setCardNumber("1234");
        entity.setCardHolder("Taner");
        entity.setCvv("123");
        entity.setExpirationDate("10/10");
        cardRepository.save(entity);

        CardEntity entityDB = cardRepository.findById(1L).get();

        assertEquals(entity,entityDB);
    }

}
