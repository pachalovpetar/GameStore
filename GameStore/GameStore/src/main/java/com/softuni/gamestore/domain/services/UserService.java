package com.softuni.gamestore.domain.services;


import com.softuni.gamestore.domain.entities.User;

public interface UserService {

    String registerUser(String[] args);

    String loginUser(String[] args);

    String logoutUser();

    User getLoggedUser();
}