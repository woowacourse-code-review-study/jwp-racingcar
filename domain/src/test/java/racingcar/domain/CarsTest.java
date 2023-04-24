package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class CarsTest {

    private MockRandomPicker mockRandomPicker;
    private Cars cars;

    @BeforeEach
    void setUp() {
        mockRandomPicker = new MockRandomPicker(List.of(4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3));
        cars = Cars.from(List.of("judy", "nunu", "pobi"));
    }

    @Test
    void move_함수를_호출했을때_진행이_되는_것을_확인() {
        cars.moveCars(mockRandomPicker);

        final List<Car> carPositionDtos = cars.getCars();
        assertAll(() -> assertThat(carPositionDtos.get(0).getPosition()).isEqualTo(1),
                () -> assertThat(carPositionDtos.get(0).getCarName()).isEqualTo("judy"),
                () -> assertThat(carPositionDtos.get(1).getPosition()).isZero(),
                () -> assertThat(carPositionDtos.get(1).getCarName()).isEqualTo("nunu"),
                () -> assertThat(carPositionDtos.get(2).getPosition()).isEqualTo(1),
                () -> assertThat(carPositionDtos.get(2).getCarName()).isEqualTo("pobi")
        );
    }

    @Test
    void findWinnerName_함수는_아무것도_하지_않는다면_모두가_우승자가_됨() {
        final List<String> result = cars.findWinner()
                .stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());

        assertThat(result).containsExactly("judy", "nunu", "pobi");
    }

    @Test
    void findWinnerName_함수로_우승자들이_반환됨() {
        cars.moveCars(mockRandomPicker);
        final List<String> result = cars.findWinner()
                .stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());

        assertThat(result).containsExactly("judy", "pobi");
    }

    @Test
    void 생성시_중복된_이름이_있으면_예외발생() {
        final List<String> names = List.of("judy", "judy");
        assertThatThrownBy(() -> Cars.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }
}
