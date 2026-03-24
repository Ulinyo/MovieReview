package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dto.MovieDto;
import kg.attractor.movie_review.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public List<MovieDto> getMovies() {
        return List.of(
                MovieDto.builder()
                        .releaseYear(2002)
                        .tittle("Harry Potter")
                        .director("James Cameron")
                        .build(),
                MovieDto.builder()
                        .tittle("Terminator")
                        .releaseYear(1985)
                        .director("Mark Goldblatt")
                        .build(),
                MovieDto.builder()
                        .tittle("Matrix")
                        .releaseYear(2003)
                        .director("Zack Stanberg")
                        .build()
        );
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        System.out.println(movieDto);
    }
}
