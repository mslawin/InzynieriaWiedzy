package pl.edu.agh.game;

import com.google.common.base.Predicate;
import pl.edu.agh.game.domain.Player;

public class PlayerPredicates {

    public static Predicate<Player> isAlive() {
        return new Predicate<Player>() {
            @Override
            public boolean apply(Player player) {
                return player.isAlive();
            }
        };
    }
}

