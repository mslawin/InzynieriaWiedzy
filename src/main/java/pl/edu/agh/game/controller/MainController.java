package pl.edu.agh.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.game.bot.RandomBot;
import pl.edu.agh.game.domain.Board;
import pl.edu.agh.game.domain.Location;
import pl.edu.agh.game.domain.Player;
import pl.edu.agh.game.service.GameMechanics;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.game.domain.Orientation.DOWN;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private GameMechanics gameMechanics;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "startgame")
    public String startGame(HttpServletRequest request) {

        List<Player> players = new ArrayList<Player>();

        String[] location1 = request.getParameter("algo1").split(" ");
        String[] location2 = request.getParameter("algo2").split(" ");
        String[] boardSize = request.getParameter("board").split(" ");

        Player player1 = new Player(0, new RandomBot());
        Location loc1 = new Location(Integer.parseInt(location1[0]), Integer.parseInt(location1[1]));
        player1.addToTrace(loc1);
        player1.setLocation(loc1);
        player1.setOrientation(DOWN);
        players.add(player1);

        Player player2 = new Player(1, new RandomBot());
        Location loc2 = new Location(Integer.parseInt(location2[0]), Integer.parseInt(location2[1]));
        player2.addToTrace(loc2);
        player2.setLocation(loc2);
        player2.setOrientation(DOWN);
        players.add(player2);

        Board board = new Board(Integer.parseInt(boardSize[0]), Integer.parseInt(boardSize[1]));
        Player winner = gameMechanics.runGame(players, board);

        request.setAttribute("trace1", player1.getTrace());
        request.setAttribute("trace2", player2.getTrace());
        request.setAttribute("winner", winner.getId());
        request.setAttribute("board", board);

        return "results";
    }
}