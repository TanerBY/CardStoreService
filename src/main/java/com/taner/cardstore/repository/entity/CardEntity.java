package com.taner.cardstore.repository.entity;

import com.taner.cardstore.util.ColumnConverter;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Convert(converter = ColumnConverter.class)
    private String cardNumber;
    @Convert(converter = ColumnConverter.class)
    private String cardHolder;
    @Convert(converter = ColumnConverter.class)
    private String expirationDate;
    @Convert(converter = ColumnConverter.class)
    private String cvv;
}
