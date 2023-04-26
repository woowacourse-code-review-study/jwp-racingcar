package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class CarTest {

    @Test
    @DisplayName("이름이 5글자 초과면 예외")
    void nameLengthEx() {
        assertThatThrownBy(() -> new Car("loooooongName"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("4 이상의 숫자가 들어오면 position은 1 증가한다.")
    void moveTest() {
        Car car = new Car("test");

        car.move(4);

        int position = car.getPosition();
        assertThat(position).isEqualTo(1);
    }

    @Test
    @DisplayName("3 이하의 숫자가 들어오면 position은 증가하지않는다.")
    void notMoveTest() {
        Car boxster = new Car("boxer");
        Car anotherBoxster = boxster;
        boxster.move(4);

        assertThat(anotherBoxster.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("position이 앞에 있는 차를 반환한다")
    void winTest() {
        Car boxster = new Car("박스터");
        Car hyundai = new Car("현대");

        boxster.move(4);

        Car win = boxster.isWin(hyundai);

        assertThat(win).isEqualTo(boxster);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름이 공백이면 예외를 발생한다")
    void nameIsEmptyEx(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }
}
