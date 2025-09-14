package com.example.minor_1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import com.example.minor_1.models.Author;
import com.example.minor_1.models.Book;
import com.example.minor_1.models.Genre;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;
    @NotNull
    private Author author;

    @Positive
    private int cost;
    @NotNull
    private Genre genre;

    public Book to()
    {
        return Book.builder()
                .cost(this.cost)
                .name(this.name)
                .author(this.author)
                .genre(this.genre)
                .build();
    }
}
