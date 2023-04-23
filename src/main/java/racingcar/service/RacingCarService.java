package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.*;
import racingcar.dto.response.RacingGameResponse;
import racingcar.repository.RacingCarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RacingCarService {

    private final RacingCarRepository racingCarRepository;
    private final NumberGenerator numberGenerator;

    public RacingCarService(final RacingCarRepository racingCarRepository, final NumberGenerator numberGenerator) {
        this.racingCarRepository = racingCarRepository;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingGameResponse play(final String rawNames, final int count) {
        final List<String> names = Arrays.stream(rawNames.split(","))
                .collect(Collectors.toList());
        final RacingGame racingGame = new RacingGame(numberGenerator, new Cars(createCars(names)), new Count(count));
        final int trialCount = racingGame.getCount();
        playGame(racingGame);
        racingCarRepository.save(racingGame, trialCount);
        return RacingGameResponse.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }

    private List<Car> createCars(final List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void playGame(final RacingGame racingGame) {
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
    }

    public List<RacingGameResponse> findPlays() {
        final List<RacingGame> racingGames = racingCarRepository.findAll();
        return racingGames.stream()
                .map(racingGame -> RacingGameResponse.of(racingGame.findWinners(), racingGame.getCurrentResult()))
                .collect(Collectors.toList());
    }
}
