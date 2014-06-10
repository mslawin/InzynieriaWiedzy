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

        double epsilon = 0.5;
        sql1.setEpsilon(epsilon);
        sql1.setAlphaDecayPower(0.5);

        IAgent agent = new LoneAgent(board, sql1);

        OnePlayerReferee referee = new OnePlayerReferee(agent);
        referee.setMaxIter(10000);
//        referee.setVerbosity();

        for (int episode = 0; episode < 10000; episode++)
        {
            int value = referee.episode(board.defaultInitialState());  // run the game

            double reward = referee.getRewardForEpisode();
            epsilon *= 0.999999;
            sql1.setEpsilon(epsilon);

            System.out.println(reward);
            //board.printTailsTable();
        }
        board.printTailsTable();
    }

    public static void main(String[] args)
    {
        GameTest gameTest = new GameTest();
        gameTest.play();
    }
}
