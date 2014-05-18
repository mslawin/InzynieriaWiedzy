package pl.edu.agh.game.util;

import pl.edu.agh.game.domain.Location;

import java.util.List;

public class GameUtil {

    public static int getLocation(List<Location> trace, int x, int y) {
        for (Location location : trace) {
            if (location.getX() == x && location.getY() == y) {
                return trace.indexOf(location);
            }
        }
        return -1;
    }
}
