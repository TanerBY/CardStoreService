package com.taner.cardstore.repository;

import com.taner.cardstore.repository.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
}
