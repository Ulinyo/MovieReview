package kg.attractor.movie_review.controller;

import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.exception.MovieImageNotFoundException;
import kg.attractor.movie_review.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final FileService fileService;

    @PostMapping
    public HttpStatus upload(MovieImageDto dto) {
        fileService.upload(dto);
        return HttpStatus.OK;
    }

    @GetMapping("{movieId}")
    public ResponseEntity<?> download(@PathVariable Long movieId) throws MovieImageNotFoundException {
        return fileService.download(movieId);
    }
}
