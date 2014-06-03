package pl.edu.agh.game.domain;

import environment.IAction;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 20.05.14
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class GameAction implements IAction
{
    private int direction; // -1, 0 , 1 , lewo prosto prawo

    public GameAction(int direction)
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
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double[] nnCoding()
    {
        return new double[0];  //To change body of implemented methods use File | Settings | File Templates.
    }
}
