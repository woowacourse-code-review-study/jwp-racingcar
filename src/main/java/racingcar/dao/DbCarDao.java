package racingcar.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@Repository
public class DbCarDao implements CarDao{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DbCarDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(List<RacingCarResultDto> racingCarResultDtos) {
        String sql = "INSERT INTO car (name, position, is_win, game_id) VALUES (:name, :position, :isWin, :gameId)";
        jdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(racingCarResultDtos));
    }

    public List<RacingCarResultDto> findCarsById(long gameId) {
        String sql = "SELECT name, position, is_win FROM car WHERE game_id = :game_id";
        Map<String, Long> parameter = Collections.singletonMap("game_id", gameId);
        return jdbcTemplate.query(sql, parameter, (resultSet, count) ->
        {
            String name = resultSet.getString("name");
            int position = resultSet.getInt("position");
            int is_win = resultSet.getInt("is_win");
            return RacingCarResultDto.create(name, position, is_win, gameId);
        });
    }
}
