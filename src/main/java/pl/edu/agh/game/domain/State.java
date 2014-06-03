package pl.edu.agh.game.domain;

import environment.AbstractTwoPlayerState;
import environment.IEnvironment;
import environment.IState;

public class State extends AbstractTwoPlayerState
{
    private final int AHEAD_INDEX = 0;
    private final int LEFT_INDEX = 1;
    private final int RIGHT_INDEX = 2;
    
    private int[] freeAreaRange = new int[3]; // ineksy: 0 - na wprost, 1 - lewo, 2 - prawo

    public State(IEnvironment ct, int ahead, int left, int right)
    {
        super(ct);
        Board board = (Board) ct;

        freeAreaRange[AHEAD_INDEX] = ahead;
        freeAreaRange[LEFT_INDEX] = left;
        freeAreaRange[RIGHT_INDEX] = right;
    }

    @Override
    public IState copy()
    {
        return new State(myEnvironment,freeAreaRange[AHEAD_INDEX], freeAreaRange[LEFT_INDEX], freeAreaRange[RIGHT_INDEX]);
    }

    @Override
    public int nnCodingSize()
    {
        return 3;
    }

    @Override
    public double[] nnCoding()
    {
        double[] nnCoding = new double[freeAreaRange.length];
        for(int i=0; i<freeAreaRange.length; i++) {
            nnCoding[i] = freeAreaRange[i];
        }

        return nnCoding;
    }

    public int[] getFreeAreaRange()
    {
        return freeAreaRange;
    }

    public void setFreeAreaRange(int[] freeAreaRange)
    {
        this.freeAreaRange = freeAreaRange;
    }
}
