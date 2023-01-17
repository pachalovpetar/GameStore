package com.softuni.gamestore.domain.services;

import com.softuni.gamestore.domain.entities.Game;
import com.softuni.gamestore.domain.entities.dtos.GameDTO;
import com.softuni.gamestore.domain.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.softuni.gamestore.Constants.Validations.*;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final UserService userService;



    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;

    }


    @Override
    public String addGame(String[] args) {

        if(this.userService.getLoggedUser().getIsAdmin()
                && this.userService.getLoggedUser() != null) {


            final String title = args[1];
            final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(args[2]));
            final float size = Float.parseFloat(args[3]);
            final String trailer = args[4];
            final String url = args[5];
            final String description = args[6];
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            final LocalDate releaseDate = LocalDate.parse(args[7], formatter);


            final GameDTO gameDTO = new GameDTO(title, price, size, trailer, url , description, releaseDate);

            final Game game = gameDTO.toGame();

            this.gameRepository.save(game);

            return GAME_ADDED + title;
        }

        return CANNOT_ADD_GAME;
    }

    @Transactional
    @Override
    public String editGame(String[] args) throws NoSuchFieldException {
        final long id = Long.parseLong(args[1]);

        if(userService.getLoggedUser().getIsAdmin()) {

            try {
                if(this.gameRepository.existsById(id)) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    Game game = this.gameRepository.getReferenceById(id);

                    GameDTO gameDTO = new GameDTO(game.getTitle(), game.getPrice(), game.getSize(),
                            game.getTrailerId(), game.getImageThumbnail(),
                            game.getDescription(), game.getReleaseDate());

                    for (int i = 2; i < args.length; i++) {
                        String[] keyValue = args[i].split("=");

                        switch (keyValue[0]) {
                            case "title" -> gameDTO.setTitle(keyValue[1]);
                            case "price" -> gameDTO.setPrice(BigDecimal.valueOf(Double.parseDouble(keyValue[1])));
                            case "size" -> gameDTO.setSize(Float.parseFloat(keyValue[1]));
                            case "thumbnail" -> gameDTO.setImageThumbnail(keyValue[1]);
                            case "trailer" -> gameDTO.setTrailerId(keyValue[1]);
                            case "description" -> gameDTO.setDescription(keyValue[1]);
                            case "date" -> gameDTO.setReleaseDate(LocalDate.parse(keyValue[1], formatter));
                            default -> throw new NoSuchFieldException();
                        }
                    }

                    final Game editedGame = gameDTO.toGame();
                    this.gameRepository.saveAndFlush(editedGame);

                    return GAME_EDITED + editedGame.getTitle();
                }

            } catch (IllegalArgumentException exception) {
                return exception.getMessage();
            }
        }

        return CANNOT_EDIT_GAME;
    }

    @Override
    public String deleteGame(Long id) {
        if(this.userService.getLoggedUser().getIsAdmin()) {
            if (this.gameRepository.existsById(id)) {
                this.gameRepository.deleteById(id);
            } else {
                return GAME_DOES_NOT_EXIST + id + ID;
            }
        }

        return NO_PERMISSION;
    }
}
