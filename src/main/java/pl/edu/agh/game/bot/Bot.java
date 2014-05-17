package pl.edu.agh.game.bot;

import pl.edu.agh.game.domain.Board;
import pl.edu.agh.game.domain.Move;
import pl.edu.agh.game.domain.Player;

import java.util.List;

public interface Bot {

    Move run(Board board, List<Player> players);
    int getId();
}
