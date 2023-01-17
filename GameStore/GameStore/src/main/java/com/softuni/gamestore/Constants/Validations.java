package com.softuni.gamestore.Constants;

public enum Validations {
    ;

    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String NOT_VALID_EMAIL = "Incorrect email.";

    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$";

    public static final String NOT_VALID_USERNAME_OR_PASSWORD = "Incorrect username / password.";

    public static final String PASSWORD_MISS_MATCH = "Password not matching.";

    public static final String NOT_VALID_TITLE = "This title is not valid!";
    public static final String NOT_VALID_TRAILER = "This trailer is not valid!";

    public static final String NOT_VALID_LINK = "The link must begins with https...";
    public static final String NOT_VALID_SIZE = "The size must be a positive number!";

    public static final String NOT_VALID_PRICE = "The price must be a positive number!";

    public static final String NOT_VALID_DESCRIPTION = "Must be at least 20 symbols!";
    public static final String CANNOT_ADD_GAME = "Game cannot be added!";
    public static final String GAME_ADDED = "Added ";
    public static final String GAME_EDITED = "Edited ";
    public static final String CANNOT_EDIT_GAME = "Game cannot be edited. Invalid id!";
    public static final String GAME_DOES_NOT_EXIST = "There isn't game with ";
    public static final String ID = "id.";
    public static final String NO_PERMISSION = "You don't have permission!";
    public static final String EMAIL_EXISTS = "Email already exists.";
    public static final String SUCCESSFUL_LOGGED = "Successfully logged in ";
    public static final String NO_USER_TO_LOG_OUT = "Cannot log out. No user was logged in.";
    public static final String USER = "User ";
    public static final String USER_LOG_OUT = " successfully logged out.";


}