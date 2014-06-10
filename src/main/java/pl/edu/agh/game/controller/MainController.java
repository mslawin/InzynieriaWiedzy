package pl.edu.agh.game.controller;

import agents.IAgent;
import agents.LoneAgent;
import algorithms.QLearningSelector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.game.domain.Board;
import referees.OnePlayerReferee;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "startgame")
    public String startGame(HttpServletRequest request) {

        String[] boardSize = request.getParameter("board").split(" ");
        String[] location = request.getParameter("location").split(" ");
        double epsilon = Double.parseDouble(request.getParameter("epsilon"));
        double alpha = Double.parseDouble(request.getParameter("alpha"));
        int maxIter = Integer.parseInt(request.getParameter("maxIter"));

        QLearningSelector sql1 = new QLearningSelector();
        Board board = new Board(Integer.parseInt(boardSize[0]), Integer.parseInt(boardSize[1]), Integer.parseInt(location[0]),
                Integer.parseInt(location[1]));

        sql1.setEpsilon(epsilon);
        sql1.setAlphaDecayPower(alpha);

        IAgent agent = new LoneAgent(board, sql1);

        OnePlayerReferee referee = new OnePlayerReferee(agent);
        referee.setMaxIter(maxIter);

        for (int episode = 0; episode < maxIter; episode++)
        {
            int value = referee.episode(board.defaultInitialState());  // run the game

            double reward = referee.getRewardForEpisode();
            epsilon *= 0.999999;
            sql1.setEpsilon(epsilon);
        }

        request.setAttribute("tailsTable", board.getTailsTable());
        request.setAttribute("criticalX", board.getCriticalX());
        request.setAttribute("criticalY", board.getCriticalY());

        return "results";
    }
}