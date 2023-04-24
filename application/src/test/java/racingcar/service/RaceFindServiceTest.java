package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import racingcar.domain.RacingGame;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class RaceFindServiceTest {

    private RaceFindService raceFindService;

    @BeforeEach
    void setUp() {
        final StubRacingGameRepository racingGameRepository = new StubRacingGameRepository();
        racingGameRepository.setGameIdToRacingGame(Map.of(
                1, new RacingGame(List.of("브리", "토미", "브라운"), 10)
        ));
        raceFindService = new RaceFindService(racingGameRepository);
    }

    @Test
    void 레이스_조회_테스트() {
        final List<RacingGame> allRace = raceFindService.findAllRace();

        assertAll(
                () -> assertThat(allRace).hasSize(1),
                () -> assertThat(allRace.get(0).findWinner().getCars()).hasSize(3)
        );
    }
}
