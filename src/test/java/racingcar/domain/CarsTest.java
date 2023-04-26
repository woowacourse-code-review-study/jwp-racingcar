package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {
    private final Car boxster = new Car("박스터");
    private final Car sonata = new Car("소나타");
    private final Car benz = new Car("벤츠");

    private final List<Car> dummy = List.of(
            boxster,
            sonata,
            benz
    );


    @Test
    @DisplayName("Position이 가장 큰 Car를 여러개 반환한다.")
    void getFirstCarsTest() {
        Cars cars = new Cars(dummy);

        int moveNumber = 4;
        boxster.move(moveNumber);
        sonata.move(moveNumber);

        List<Car> firstCars = cars.findWinners();

        assertThat(firstCars).containsOnly(boxster, sonata);
    }

    @Test
    @DisplayName("Position이 가장 큰 Car 하나를 반환한다.")
    void getFirstPositionTest() {
        Cars cars = new Cars(dummy);

        int moveNumber = 4;
        boxster.move(moveNumber);

        List<Car> firstCars = cars.findWinners();

        assertThat(firstCars).containsOnly(boxster);
    }

    @Test
    @DisplayName("중복된 이름이면 예외가 발생한다")
    void duplicateNameEx() {
        List<Car> duplicateNameCars = List.of(new Car("박스터"), new Car("박스터"));

        assertThatThrownBy(() -> new Cars(duplicateNameCars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다");

    }

    @Test
    @DisplayName("car의 수가 2보다 작으면 예외를 발생한다")
    void carSizeEx() {
        List<Car> cars = List.of(new Car("박스터"));

        assertThatThrownBy(() -> new Cars(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 2대의 자동차가 있어야 합니다.");
    }
}
