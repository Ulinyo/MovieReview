package kg.attractor.movie_review.dao;

import kg.attractor.movie_review.dao.mapper.UserMapper;
import lombok.AllArgsConstructor;
import kg.attractor.movie_review.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "select * from usr;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> findById(int id) {
        String sql = "select * from usr where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), id)
                )
        );
    }

    public Optional<User> findByIdName(int id) {
        String sql = "select * from usr where id = :id";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        namedParameterJdbcTemplate.query(sql,
                                new MapSqlParameterSource()
                                        .addValue("id", id),
                                new UserMapper()
                        )
                )
        );
    }
}
