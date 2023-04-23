package racingcar.infrastructure.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@SpringBootTest
@DisplayName("RacingGameDao 는")
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate template;

    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(template);
    }

    @Test
    void RacingGame_을_저장한다() {
        // given
        final RacingGameEntity racingGameEntity = makeGameEntity();

        // when
        final Long id = racingGameDao.save(racingGameEntity);

        // then
        assertThat(racingGameDao.findById(id)).isNotEmpty();
    }

    private RacingGameEntity makeGameEntity() {
        final Cars cars = new Cars(Stream.of("브리", "토미", "브라운")
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime gameTime = new GameTime("10");
        final RacingGame racingGame = new RacingGame(cars, gameTime);
        return new RacingGameEntity(racingGame);
    }

    @Test
    void RacingGame_을_모두_저장한다() {
        // given
        IntStream.range(0, 5).forEach(it -> racingGameDao.save(makeGameEntity()));

        // when
        final List<RacingGameEntity> all = racingGameDao.findAll();

        // then
        assertThat(all.size()).isEqualTo(5);
    }
}
