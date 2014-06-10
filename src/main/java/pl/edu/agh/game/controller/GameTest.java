package pl.edu.agh.game.controller;

import agents.IAgent;
import agents.LoneAgent;
import algorithms.QLearningSelector;
import pl.edu.agh.game.domain.Board;
import referees.OnePlayerReferee;

public class GameTest
{
    public void play()
    {
        Board board = new Board(20, 20);

        QLearningSelector sql1 = new QLearningSelector();

        IAgent agent = new LoneAgent(board, sql1);

        OnePlayerReferee referee = new OnePlayerReferee(agent);
        referee.setMaxIter(100);
        referee.setVerbosity();
        int value = referee.episode(board.defaultInitialState());  // run the game

        double reward = referee.getRewardForEpisode();

        board.printTailsTable();
    }

    public static void main(String[] args)
    {
        GameTest gameTest = new GameTest();
        gameTest.play();
    }
}
