package com.softuni.gamestore.domain.services;


import com.softuni.gamestore.domain.entities.Game;

public interface GameService {
    String addGame(String[] args);
    String editGame(String[] args) throws NoSuchFieldException;

    String deleteGame(Long id);
}
