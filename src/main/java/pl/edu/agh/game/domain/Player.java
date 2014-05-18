package pl.edu.agh.game.domain;

import pl.edu.agh.game.bot.Bot;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.game.domain.Orientation.*;

public class Player {

    private final List<Location> trace = new ArrayList<Location>();
    private final int id;
    private final Bot bot;

    private boolean isAlive = true;
    private Location location;
    private Orientation orientation;

    public Player(int id, Bot bot) {
        this.id = id;
        this.bot = bot;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void movePlayerAndUpdateTrace(List<Player> players, Board board) {
        location = new Location(location.getX(), location.getY());
        Move move = getNextMove(players, board);
        switch (move) {
            case LEFT:
                switch (orientation) {
                    case LEFT:
                        orientation = DOWN;
                        location.setY(location.getY() - 1);
                        break;
                    case RIGHT:
                        orientation = UP;
                        location.setY(location.getY() + 1);
                        break;
                    case UP:
                        orientation = LEFT;
                        location.setX(location.getX() - 1);
                        break;
                    case DOWN:
                        orientation = RIGHT;
                        location.setX(location.getX() + 1);
                        break;
                }
                break;
            case RIGHT:
                switch (orientation) {
                    case LEFT:
                        orientation = UP;
                        location.setY(location.getY() + 1);
                        break;
                    case RIGHT:
                        orientation = DOWN;
                        location.setY(location.getY() - 1);
                        break;
                    case UP:
                        orientation = RIGHT;
                        location.setX(location.getX() + 1);
                        break;
                    case DOWN:
                        orientation = LEFT;
                        location.setX(location.getX() - 1);
                        break;
                }
                break;
            case STRAIGHT:
                switch (orientation) {
                    case LEFT:
                        location.setX(location.getX() - 1);
                        break;
                    case RIGHT:
                        location.setX(location.getX() + 1);
                        break;
                    case UP:
                        location.setY(location.getY() + 1);
                        break;
                    case DOWN:
                        location.setY(location.getY() - 1);
                        break;
                }
                break;
        }

        trace.add(location);

    }

    private Move getNextMove(List<Player> players, Board board) {
        return bot.run(board, players);
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getTrace() {
        return trace;
    }

    public void addToTrace(Location location) {
        trace.add(location);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
