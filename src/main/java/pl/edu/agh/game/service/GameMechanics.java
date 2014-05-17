package pl.edu.agh.game.service;

import com.google.common.collect.Collections2;
import org.springframework.stereotype.Service;
import pl.edu.agh.game.domain.Board;
import pl.edu.agh.game.domain.Location;
import pl.edu.agh.game.domain.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pl.edu.agh.game.PlayerPredicates.isAlive;

@Service
public class GameMechanics {

    private static final Logger logger = Logger.getLogger(GameMechanics.class.getName());

    private List<Player> winners = new ArrayList<Player>();

    public void runGame(List<Player> players, Board board) {
        while (moreThanOnePlayersAlive(players)) {
            for (Player p : players) {
                p.movePlayerAndUpdateTrace(players, board);
            }

            for (Player player : players) {
                if (player.isAlive()) {
                    if (player.getLocation().getX() < 0 || player.getLocation().getY() < 0 || player.getLocation().getX() >= board.getxSize()
                            || player.getLocation().getY() >= board.getySize()) {
                        player.setAlive(false);
                        winners.add(player);
                    } else {
                        for (Player player2 : players) {
                            if (player2.getId() != player.getId() && player.getLocation().getX() == player2.getTrace().get(player2.getTrace().size() - 1).getX()
                                    && player.getLocation().getY() == player2.getTrace().get(player2.getTrace().size() - 1).getY()) {
                                player.setAlive(false);
                                winners.add(player);
                                break;
                            }
                            for (Location k : player2.getTrace()) {
                                if (player.getId() == player2.getId()) {
                                    if (k.getX() == player.getLocation().getX() && k.getY() == player.getLocation().getY()
                                            && player2.getTrace().indexOf(k) != player2.getTrace().size() - 1) {
                                        player.setAlive(false);
                                        winners.add(player);
                                        break;
                                    }
                                } else {
                                    if (k.getX() == player.getLocation().getX() && k.getY() == player.getLocation().getY()) {
                                        player.setAlive(false);
                                        winners.add(player);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            logger.log(Level.INFO, getPlayers(players));
        }
        winners.addAll(getAlivePlayers(players));
        logger.log(Level.INFO, getWinners(winners));
    }

    private String getPlayers(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder.append(player.getId()).append(" - ").append(player.getLocation().getX()).append(":").append(player.getLocation().getY()).append("\n");
        }
        return stringBuilder.toString();
    }

    private String getWinners(List<Player> winners) {
        Collections.reverse(winners);
        StringBuilder stringBuilder = new StringBuilder();
        for (Player winner : winners) {
            stringBuilder.append(winner.getId()).append("::");
        }
        return stringBuilder.toString();
    }

    private Collection<? extends Player> getAlivePlayers(List<Player> players) {
        return Collections2.filter(players, isAlive());

    }

    private boolean moreThanOnePlayersAlive(List<Player> players) {
        int playersAlive = 0;
        for (Player p : players) {
            if (p.isAlive()) {
                playersAlive++;
            }
        }

        return playersAlive > 1;
    }


}
