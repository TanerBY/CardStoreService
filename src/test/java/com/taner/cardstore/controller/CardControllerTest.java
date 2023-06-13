package com.taner.cardstore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.taner.cardstore.model.Card;
import com.taner.cardstore.service.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    public void shouldCreateCard() throws Exception {

        this.mockMvc.perform(post("/api/cards").contentType(MediaType.APPLICATION_JSON).content("{ \n" +
                        "   \"cardHolder\": \"taner\",  \n" +
                        "   \"cardNumber\": \"1234123412341234\",  \n" +
                        "   \"cvv\": \"123\",  \n" +
                        "   \"expirationDate\": \"10/10\"  \n" +
                        " }"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetValidationErrorForCardNumber() throws Exception {

        this.mockMvc.perform(post("/api/cards").contentType(MediaType.APPLICATION_JSON).content("{ \n" +
                        "   \"cardHolder\": \"taner\",  \n" +
                        "   \"cardNumber\": \"1234123412334\",  \n" +
                        "   \"cvv\": \"123\",  \n" +
                        "   \"expirationDate\": \"10/10\"  \n" +
                        " }"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldReturnCard() throws Exception {
        Card card = Card.builder().cardHolder("Taner").cardNumber("1234123412341234").cvv("123").expirationDate("10/10").build();
                when(cardService.getCardDetails(1L)).thenReturn(card);
        this.mockMvc.perform(get("/api/cards/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("cardHolder").value("Taner"))
                .andExpect(jsonPath("cardNumber").value("1234123412341234"))
                .andExpect(jsonPath("cvv").value("123"))
                .andExpect(jsonPath("expirationDate").value("10/10"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        when(cardService.getCardDetails(1L)).thenThrow(new ResponseStatusException(NOT_FOUND, "Card not found"));
        this.mockMvc.perform(get("/api/cards/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }


}