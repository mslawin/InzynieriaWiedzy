package pl.edu.agh.game.bot;

import pl.edu.agh.game.domain.Board;
import pl.edu.agh.game.domain.Move;
import pl.edu.agh.game.domain.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomBot implements Bot {

    private static final List<Move> VALUES = Collections.unmodifiableList(Arrays.asList(Move.values()));
    private static final Random RANDOM = new Random();

    private int id;

    @Override
    public Move run(Board board, List<Player> players) {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
