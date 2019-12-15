package com.restaurant.bot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.bot.domain.TypeFood;

import java.util.List;

public interface CpTypefoodRepository extends JpaRepository<TypeFood,Integer> {
    public List<TypeFood> findAll();
}
