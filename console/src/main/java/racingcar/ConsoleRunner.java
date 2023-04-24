package racingcar;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.service.RaceAddService;
import racingcar.service.RaceFindService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

@Configuration
public class ConsoleRunner {

    private final RaceAddService raceAddService;
    private final RaceFindService raceFindService;

    public ConsoleRunner(final RaceAddService raceAddService, final RaceFindService raceFindService) {
        this.raceAddService = raceAddService;
        this.raceFindService = raceFindService;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            final RacingGame racingGame = raceAddService.addRace(InputView.inputCarName(), InputView.inputTryCount());
            final List<String> winnerNames = raceFindService.findWinners(racingGame)
                    .getCars()
                    .stream()
                    .map(Car::getCarName)
                    .collect(Collectors.toList());
            OutputView.printWinner(winnerNames);
            OutputView.printCarsStatus(getRacingGameStatus(racingGame));
        };
    }

    private Map<String, Integer> getRacingGameStatus(final RacingGame racingGame) {
        return racingGame.getCars()
                .stream()
                .collect(Collectors.toMap(Car::getCarName, Car::getPosition));
    }
}
