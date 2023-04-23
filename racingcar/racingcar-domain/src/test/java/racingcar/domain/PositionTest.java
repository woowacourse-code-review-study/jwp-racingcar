package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("move 호출 시 position 값이 1 증가한다.")
    void moveTest() {
        Position position = new Position(0);
        position.move();

        assertThat(position)
                .extracting("position")
                .isEqualTo(1);
    }

}
