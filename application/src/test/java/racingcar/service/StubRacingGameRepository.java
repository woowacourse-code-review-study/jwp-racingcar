package racingcar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

public class StubRacingGameRepository implements RacingGameRepository {

    private final Map<Integer, RacingGame> gameIdToRacingGame = new HashMap<>();
    private int maxGameId = 0;

    @Override
    public RacingGame save(final RacingGame racingGame) {
        final RacingGame racingGameWithId = new RacingGame(
                ++maxGameId,
                racingGame.getCars(),
                racingGame.getCount().getTargetCount());
        gameIdToRacingGame.put(maxGameId, racingGameWithId);
        return racingGameWithId;
    }

    public void setGameIdToRacingGame(final Map<Integer, RacingGame> gameIdToRacingGame) {
        this.gameIdToRacingGame.clear();
        this.gameIdToRacingGame.putAll(gameIdToRacingGame);
    }

    @Override
    public List<RacingGame> findAll() {
        return new ArrayList<>(gameIdToRacingGame.values());
    }
}
