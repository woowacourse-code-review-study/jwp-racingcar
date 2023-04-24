package racingcar.domain;

import java.util.Objects;

final class Position {

    private static final int NEXT_MOVE = 1;
    private static final int INIT_NUMBER = 0;

    private final int moveCount;

    public Position(final int moveCount) {
        this.moveCount = moveCount;
    }

    public static Position init() {
        return new Position(INIT_NUMBER);
    }

    public Position next() {
        return new Position(moveCount + NEXT_MOVE);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public boolean isMatchMoveCount(final int moveCount) {
        return this.moveCount == moveCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return moveCount == position.moveCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveCount);
    }
}
