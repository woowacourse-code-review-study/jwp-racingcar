package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;
import racingcar.repositoryImpl.RacingGameMapper;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private int gameId;
    private CarDao carDao;

    @BeforeEach
    void setUp() {
        final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));

        gameId = RepositoryFactory.gamesDao(jdbcTemplate).insert(gameEntity).getGameId().getValue();
        carDao = RepositoryFactory.carDao(jdbcTemplate);
    }

    @Test
    void 자동차_전체_저장() {
        final List<CarEntity> result = carDao.insertAll(List.of(new CarEntity(3, "토미", 9, gameId)));

        assertAll(
                () -> Assertions.assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getName()).isEqualTo("토미"),
                () -> assertThat(result.get(0).getPosition()).isSameAs(9),
                () -> assertThat(result.get(0).getCarId().getValue()).isNotNull()
        );
    }

    @Nested
    class SearchTest {

        @BeforeEach
        void setUp() {
            carDao.insertAll(List.of(new CarEntity("토미", 9, gameId),
                    new CarEntity("브리", 9, gameId)));
        }

        @Test
        void 자동차_전체_조회() {
            final List<CarEntity> result = carDao.findAll();

            assertAll(
                    () -> assertThat(result).hasSize(2),
                    () -> assertThat(result.get(0).getName()).isEqualTo("토미"),
                    () -> assertThat(result.get(0).getPosition()).isSameAs(9),
                    () -> assertThat(result.get(0).getCarId().getValue()).isNotNull()
            );
        }
    }
}
