package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    private final NumberGenerator numberGenerator = new NumberGenerator() {
        private int count = 0;

        @Override
        public int generateNumber() {
            if (count++ % 3 == 0) {
                return 9;
            }
            return 1;
        }
    };

    @Test
    void playGame() {
        Cars cars = new Cars(Stream.of("브리", "토미", "브라운")
                .map(Car::new)
                .collect(Collectors.toList()));
        GameTime gameTime = new GameTime("10");
        final RacingGame racingGame = new RacingGame(cars, gameTime);

        racingGame.play(numberGenerator);

        final Winners winners = racingGame.winners();

        assertThat(winners.getWinners())
                .extracting(Winner::getName)
                .containsExactlyInAnyOrder("브리");
    }
}
