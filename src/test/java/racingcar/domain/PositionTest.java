package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("Position의 초기값은 0을 가진다.")
    void should_initValueIsZero_when_createPosition() {
        final Position position = new Position();

        assertThat(position.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("increase 메서드는 위치 값을 1 증가시킨다.")
    void should_plusOne_when_increase() {
        final Position position = new Position();

        position.increase();

        assertThat(position.getValue()).isEqualTo(1);
    }
}
