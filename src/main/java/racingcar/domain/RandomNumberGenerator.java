package racingcar.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    private static final int NUMBER_UPPER_BOUND = 10;

    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(NUMBER_UPPER_BOUND);
    }
}
