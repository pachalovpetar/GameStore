package com.softuni.gamestore.domain.entities.dtos;

import com.softuni.gamestore.domain.entities.Game;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.softuni.gamestore.Constants.Validations.*;

public class GameDTO {

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_id")
    private String trailerId;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Column(nullable = false)
    private float size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;


    public GameDTO() {
    }

    public GameDTO(String title, BigDecimal price, float size, String trailerId, String imageThumbnail, String description, LocalDate releaseDate) {

        setTitle(title);
        setPrice(price);
        setSize(size);
        setTrailerId(trailerId);
        setImageThumbnail(imageThumbnail);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(Character.isUpperCase(title.charAt(0))
        && title.length() >= 3 && title.length() <= 100) {
            this.title = title;
        } else {
            throw new IllegalArgumentException(NOT_VALID_TITLE);
        }

    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        if(trailerId.length() == 11) {
            this.trailerId = trailerId;
        } else {
            throw new IllegalArgumentException(NOT_VALID_TRAILER);
        }
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        if (imageThumbnail != null && !imageThumbnail.startsWith("https://")) {
            throw new IllegalArgumentException(NOT_VALID_LINK);
        }
        this.imageThumbnail = imageThumbnail;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if(size > 0.0) {

            this.size = size;
        } else {

            throw new IllegalArgumentException(NOT_VALID_SIZE);
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.longValue() > 0) {
            this.price = price;
        } else {

            throw new IllegalArgumentException(NOT_VALID_PRICE);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.length() >= 20) {
            this.description = description;
        } else {

            throw new IllegalArgumentException(NOT_VALID_DESCRIPTION);
        }
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Game toGame() {
        return new Game(title,
                trailerId,
                imageThumbnail,
                size,
                price,
                description,
                releaseDate);
    }
}
