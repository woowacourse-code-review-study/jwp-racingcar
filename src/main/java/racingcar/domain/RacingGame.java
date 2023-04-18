package racingcar.domain;

import java.util.List;

public class RacingGame {

    private final Cars cars;
    private final Count count;
    private Integer gameId;

    public RacingGame(final List<String> carsName, final int count) {
        cars = new Cars(carsName);
        this.count = new Count(count);
    }

    public RacingGame(final Integer gameId, final RacingGame racingGame) {
        this.gameId = gameId;
        cars = racingGame.cars;
        count = racingGame.count;
    }

    public void race(final NumberPicker numberPicker) {
        cars.race(count, numberPicker);
    }

    public List<Car> findWinner() {
        return cars.findWinner();
    }

    public List<Car> findResult() {
        return cars.getCars();
    }

    public Count getCount() {
        return count;
    }

    public Integer getGameId() {
        return gameId;
    }
}
