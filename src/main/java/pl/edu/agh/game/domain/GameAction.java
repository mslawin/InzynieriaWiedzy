package pl.edu.agh.game.domain;

import environment.IAction;

public class GameAction implements IAction
{
    Move direction;

    public GameAction(Move direction)
    {
        this.direction = direction;
    }

    @Override
    public Object copy()
    {
        return new GameAction(direction);
    }

    @Override
    public int nnCodingSize()
    {
        return 1;
    }

    @Override
    public double[] nnCoding()
    {
        double[] doubles = new double[1];
        doubles[0] = direction.hashCode(); // to na pewno zroznicuje?
        return doubles;
    }

    public Move getDirection()
    {
        return direction;
    }

    public void setDirection(Move direction)
    {
        this.direction = direction;
    }
}
