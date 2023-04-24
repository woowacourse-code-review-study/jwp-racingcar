package racingcar.domain;

public class Car {

    private static final int GO = 4;

    private final CarName carName;
    private final CarId carId;
    private Position position;

    public Car(final int carId, final String carName) {
        this(carId, carName, 0);
    }

    public Car(final int carId, final String carName, final int position) {
        this.carName = new CarName(carName);
        this.position = new Position(position);
        this.carId = new CarId(carId);
    }

    public void move(final int power) {
        if (power >= GO) {
            position = position.next();
        }
    }

    public int getPosition() {
        return position.getMoveCount();
    }

    public boolean matchPosition(final int position) {
        return this.position.isMatchMoveCount(position);
    }

    public String getCarName() {
        return carName.getName();
    }

    public CarId getCarId() {
        return carId;
    }
}
