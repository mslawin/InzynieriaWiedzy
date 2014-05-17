package pl.edu.agh.game.sample;

import pl.edu.agh.game.bot.RandomBot;
import pl.edu.agh.game.domain.Board;
import pl.edu.agh.game.domain.Location;
import pl.edu.agh.game.domain.Player;
import pl.edu.agh.game.service.GameMechanics;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.game.domain.Orientation.DOWN;

public class SampleGame {

    public static void main(String args[]) {
        List<Player> players = new ArrayList<Player>();

        Player player = new Player(0, new RandomBot());
        player.setLocation(new Location(3, 5));
        player.setOrientation(DOWN);
        players.add(player);

        player = new Player(1, new RandomBot());
        player.setLocation(new Location(7, 3));
        player.setOrientation(DOWN);
        players.add(player);

        GameMechanics gameMechanics = new GameMechanics();
        gameMechanics.runGame(players, new Board(10, 10));
    }
}
