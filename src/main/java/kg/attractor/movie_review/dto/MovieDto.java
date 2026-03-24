package kg.attractor.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private int releaseYear;
    private String tittle;
    private String description;
    private String director;

    @Override
    public String toString() {
        return "MovieDto{" +
                "releaseYear=" + releaseYear +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
