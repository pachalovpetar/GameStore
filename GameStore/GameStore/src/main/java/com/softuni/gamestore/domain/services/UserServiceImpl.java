package com.softuni.gamestore.domain.services;


import com.softuni.gamestore.domain.entities.User;
import com.softuni.gamestore.domain.entities.dtos.UserLoginDTO;
import com.softuni.gamestore.domain.entities.dtos.UserRegisterDto;
import com.softuni.gamestore.domain.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.softuni.gamestore.Constants.Validations.*;

@Service
public class UserServiceImpl implements UserService{

    private User loggedUser;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {
        String email = args[1];
        String password = args[2];
        String confirmPassword = args[3];
        String fullName = args[4];

        UserRegisterDto userRegisterDto;

        try {
            userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        User user = this.modelMapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        }

        boolean isEmailFound = this.userRepository.findByEmail(userRegisterDto.getEmail()).isPresent();

        if(isEmailFound) {
            throw new IllegalArgumentException(EMAIL_EXISTS);
        }

        this.userRepository.save(user);

        return userRegisterDto.successfulRegister();
    }

    @Override
    public String loginUser(String[] args) {
        final String email = args[1];
        final String password = args[2];

        final UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);

        Optional<User> user = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if (user.isPresent() &&
                this.loggedUser == null &&
                user.get().getPassword().equals(userLoginDTO.getPassword())) {
            this.loggedUser = this.userRepository.findByEmail(userLoginDTO.getEmail()).get();
            return SUCCESSFUL_LOGGED + this.loggedUser.getFullName();
        }
        return NOT_VALID_USERNAME_OR_PASSWORD;
    }

    @Override
    public String logoutUser() {
        if(this.loggedUser == null) {
            return NO_USER_TO_LOG_OUT;
        }
        String output = USER + this.loggedUser.getFullName() + USER_LOG_OUT;

        this.loggedUser = null;

        return output;
    }

    @Override
    public User getLoggedUser() {
        return this.loggedUser;
    }
}
