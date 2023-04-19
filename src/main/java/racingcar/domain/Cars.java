package racingcar.domain;

import racingcar.RandomNumberGenerator;
import racingcar.api.dto.request.CarGameRequest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> cars) {
        validateCars(cars);
        return new Cars(cars.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    public static Cars from(final CarGameRequest request) {
        List<Car> cars = request.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    private static void validateCars(final List<String> cars) {
        validateEmpty(cars);
        validateDuplicatedName(cars);
    }

    private static void validateEmpty(final List<String> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("최소 하나 이상의 Car 객체가 존재해야합니다.");
        }
    }

    private static void validateDuplicatedName(final List<String> cars) {
        Set<String> refined = new HashSet<>(cars);

        if (refined.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public void moveAll(final RandomNumberGenerator generator) {
        for (Car car : cars) {
            int power = generator.generate();
            car.move(power);
        }
    }

    public List<Car> decideWinners() {
        int maxPosition = this.calculateMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toUnmodifiableList());
    }

    private int calculateMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(Car.MIN_POSITION);
    }

    public List<Car> getUnmodifiableCars() {
        return Collections.unmodifiableList(cars);
    }
}
