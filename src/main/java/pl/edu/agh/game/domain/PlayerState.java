package pl.edu.agh.game.domain;

import environment.AbstractState;
import environment.IEnvironment;
import environment.IState;

public class PlayerState extends AbstractState
{
    public final int AHEAD_INDEX = 0;
    public final int LEFT_INDEX = 1;
    public final int RIGHT_INDEX = 2;
    
    private int[] freeAreaRange = new int[3]; // ineksy: 0 - na wprost, 1 - lewo, 2 - prawo

    Location location;
    Orientation orientation;
    boolean alive = true;

    public PlayerState(IEnvironment ct, int[] freeArea, Location loc, Orientation or)
    {
        super(ct);
        Board board = (Board) ct;

        location = loc;
        orientation = or;

        freeAreaRange = freeArea;
    }

    @Override
    public IState copy()
    {
        return new PlayerState(myEnvironment,freeAreaRange, location, orientation);
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

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Orientation getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
}
