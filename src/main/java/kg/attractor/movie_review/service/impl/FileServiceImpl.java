package kg.attractor.movie_review.service.impl;

import kg.attractor.movie_review.dao.MovieImageDao;
import kg.attractor.movie_review.dto.MovieImageDto;
import kg.attractor.movie_review.exception.MovieImageNotFoundException;
import kg.attractor.movie_review.model.MovieImage;
import kg.attractor.movie_review.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private static final String UPLOAD_DIR = "data/";
    private final MovieImageDao movieImageDao;

    @SneakyThrows
    private String saveUploadedFile(MultipartFile file, String subdir) {
        String uuid = UUID.randomUUID().toString();
        String resultFile = uuid + "_" + file.getOriginalFilename();

        Path fileDir = Paths.get(UPLOAD_DIR + subdir);
        if(Files.notExists(fileDir)) Files.createFile(fileDir);

        Path filePath = Paths.get(fileDir + "/" + resultFile);
        if(Files.notExists(filePath)) Files.createFile(filePath);

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(file.getBytes());
        }catch (IOException e) {
            e.printStackTrace();
        }

        return resultFile;
    }

    @Override
    public void upload(MovieImageDto movieImageDto) {
        String resultFileName = saveUploadedFile(movieImageDto.getFile(), "images");
        log.debug("Result filename uploaded images is {}", resultFileName);
        movieImageDao.save(movieImageDto.getMovieId(), resultFileName);
    }

    private byte[] getDownloadedFile(String filename, String subdir) throws IOException {
        return Files.readAllBytes(Paths.get(UPLOAD_DIR + subdir + "/" + filename));
    }

    @Override
    public ResponseEntity<?> download(Long movieId) throws MovieImageNotFoundException{
        MovieImage movieImage = movieImageDao.findByMovieId(movieId)
                .orElseThrow(MovieImageNotFoundException::new);
        try {
            Resource resource = new ByteArrayResource(getDownloadedFile(movieImage.getFileName(), "images"));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + movieImage.getFileName() + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        }catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Image not found");
        }
    }
}
