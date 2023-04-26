package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.RacingGameDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.dto.RacingGameResponse;

public class RacingGameFindServiceTest {
    RacingGameFindService racingGameFindService;
    RacingGameDao racingGameDao;
    CarDao carDao;

    @BeforeEach
    void setUp() {
        carDao = Mockito.mock(CarDao.class);
        racingGameDao = Mockito.mock(RacingGameDao.class);
        racingGameFindService = new RacingGameFindService(carDao, racingGameDao);
    }

    @Test
    @DisplayName("전체 결과를 조회하여 결과를 반환한다")
    void findHistory() {
        given(racingGameDao.findAllByCreatedTimeAsc())
                .willReturn(List.of(new RacingGameEntity(10)));
        given(carDao.findByRacingGameId(any()))
                .willReturn(List.of(new CarEntity("박스터", 7, false, 1L),
                        new CarEntity("현구막", 10, true, 1L)));

        List<RacingGameResponse> history = racingGameFindService.findHistory();

        assertAll(
                () -> assertThat(history).hasSize(1),
                () -> assertThat(history.get(0).getRacingCars()).hasSize(2),
                () -> assertThat(history.get(0).getWinners()).containsExactly("현구막")
        );
    }
}
