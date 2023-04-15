package racingcar.dao;

import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.RacingGameEntity;

@Component
public class RacingGameJdbcDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT into RACING_GAME (trial_count) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, racingGameEntity.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
