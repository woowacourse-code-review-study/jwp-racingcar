package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final String DUPLICATE_NAME_EXCEPTION = "중복된 이름이 존재합니다.";
    private static final String NO_CAR_EXCEPTION = "차가 존재하지 않습니다";
    private static final String NO_MAX_VALUE_EXCEPTION = "최대값이 존재하지 않습니다";

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        validate(carNames);
        final List<Car> cars = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            cars.add(new Car(i, carNames.get(i)));
        }
        return new Cars(cars);
    }

    private static void validate(final List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new IllegalArgumentException(NO_CAR_EXCEPTION);
        }
        if (new HashSet<>(carNames).size() != carNames.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_EXCEPTION);
        }
    }

    public void race(final Count tryCount, final NumberPicker numberPicker) {
        while (!tryCount.isFinished()) {
            moveCars(numberPicker);
            tryCount.next();
        }
    }

    public void moveCars(final NumberPicker numberPicker) {
        for (final Car car : cars) {
            move(car, numberPicker);
        }
    }

    private void move(final Car car, final NumberPicker numberPicker) {
        final int power = numberPicker.pickNumber();
        car.move(power);
    }

    public List<Car> findWinner() {
        final int max = findMaxPosition();
        return cars.stream()
                .filter(car -> car.matchPosition(max))
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(() -> new IllegalArgumentException(NO_MAX_VALUE_EXCEPTION));
    }

    public List<Car> getCars() {
        return cars;
    }
}
