package pl.edu.agh.game.domain;

import environment.AbstractEnvironmentSingle;
import environment.ActionList;
import environment.IAction;
import environment.IState;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.game.domain.Orientation.*;

public class Board extends AbstractEnvironmentSingle
{

    private int xSize;
    private int ySize;

    private Location startLocation;

    private int[][] tailsTable; // 0 - nic, 1 - player, 2 - bot,

    private final List<Location> trace = new ArrayList<Location>();

    private PlayerState oldState = null;

    public final int AHEAD_INDEX = 0;
    public final int LEFT_INDEX = 1;
    public final int RIGHT_INDEX = 2;

    public Board(int x, int y, int startLocationX, int startLocationY)
    {
        this.xSize = x;
        this.ySize = y;
        this.startLocation = new Location(startLocationX, startLocationY);

        this.tailsTable = new int[xSize][ySize];

        initializeTraces();
    }

    public int getxSize()
    {
        return xSize;
    }

    public void setxSize(int xSize)
    {
        this.xSize = xSize;
    }

    public int getySize()
    {
        return ySize;
    }

    public void setySize(int ySize)
    {
        this.ySize = ySize;
    }

    @Override
    public ActionList getActionList(IState iState)
    {
        ActionList list = new ActionList(iState);
        list.add(new GameAction(Move.LEFT));
        list.add(new GameAction(Move.RIGHT));
        list.add(new GameAction(Move.STRAIGHT));
        return list;
    }

    @Override
    public IState successorState(IState iState, IAction iAction)
    {
        oldState = (PlayerState) iState;
        PlayerState stateAfterAction = calculateState(oldState, iAction);

        return stateAfterAction;
    }

    private PlayerState calculateState(PlayerState oldState, IAction iAction)
    {
        GameAction gameAction = (GameAction) iAction;
        Move direction = gameAction.getDirection();
        Location location = oldState.getLocation();
        Orientation orientation = oldState.getOrientation();

        return getNewState(direction, location, orientation);
    }

    private PlayerState getNewState(Move move, Location location, Orientation orientation)
    {
        location = new Location(location.getX(), location.getY());

        switch (move)
        {
            case LEFT:
                switch (orientation)
                {
                    case LEFT:
                        orientation = DOWN;
                        location.setY(location.getY() - 1);
                        break;
                    case RIGHT:
                        orientation = UP;
                        location.setY(location.getY() + 1);
                        break;
                    case UP:
                        orientation = LEFT;
                        location.setX(location.getX() - 1);
                        break;
                    case DOWN:
                        orientation = RIGHT;
                        location.setX(location.getX() + 1);
                        break;
                }
                break;
            case RIGHT:
                switch (orientation)
                {
                    case LEFT:
                        orientation = UP;
                        location.setY(location.getY() + 1);
                        break;
                    case RIGHT:
                        orientation = DOWN;
                        location.setY(location.getY() - 1);
                        break;
                    case UP:
                        orientation = RIGHT;
                        location.setX(location.getX() + 1);
                        break;
                    case DOWN:
                        orientation = LEFT;
                        location.setX(location.getX() - 1);
                        break;
                }
                break;
            case STRAIGHT:
                switch (orientation)
                {
                    case LEFT:
                        location.setX(location.getX() - 1);
                        break;
                    case RIGHT:
                        location.setX(location.getX() + 1);
                        break;
                    case UP:
                        location.setY(location.getY() + 1);
                        break;
                    case DOWN:
                        location.setY(location.getY() - 1);
                        break;
                }
                break;
        }

        boolean isCorrectMove = true;
        if(location.getX() <0 || location.getY() < 0 || location.getX() +1 >= xSize || location.getY() + 1 >= ySize)
        {
            PlayerState state = new PlayerState(this,null,location,orientation);
            state.setAlive(false);
            return state;
        }

        if (tailsTable[location.getX()][location.getY()] > 0)
        {
            isCorrectMove = false;
        }

        tailsTable[location.getX()][location.getY()] = 1;
        int[] freeArea = calculateFreeArea(location, orientation);

        PlayerState newState = new PlayerState(this, freeArea, location, orientation);
        newState.setAlive(isCorrectMove);

        trace.add(location);

        return newState;
    }

    private int[] calculateFreeArea(Location location, Orientation orientation)
    {
        int[] freeArea = new int[3];

        int upDistance = getUpDistance(location);
        int downDistance = getDownDistance(location);
        int leftDistance = getLeftDistance(location);
        int rightDistance = getRightDistance(location);

        switch (orientation)
        {
            case LEFT:
                freeArea[AHEAD_INDEX] = leftDistance;
                freeArea[LEFT_INDEX] = downDistance;
                freeArea[RIGHT_INDEX] = upDistance;
                break;
            case RIGHT:
                freeArea[AHEAD_INDEX] = rightDistance;

                freeArea[LEFT_INDEX] = upDistance;
                freeArea[RIGHT_INDEX] = downDistance;
                break;
            case UP:
                freeArea[AHEAD_INDEX] = upDistance;
                freeArea[LEFT_INDEX] = leftDistance;
                freeArea[RIGHT_INDEX] = rightDistance;
                break;
            case DOWN:
                freeArea[AHEAD_INDEX] = downDistance;
                freeArea[LEFT_INDEX] = rightDistance;
                freeArea[RIGHT_INDEX] = leftDistance;
                break;
        }

        return freeArea;
    }

    private int getRightDistance(Location location)
    {
        int counter = 0;
        for (int i = location.getX() + 1; i < xSize; i++)
        {
            if (tailsTable[i][location.getY()] == 0)
            {
                counter++;
            } else
            {
                break;
            }
        }
        return counter;
    }

    private int getLeftDistance(Location location)
    {
        int counter = 0;
        for (int i = location.getX() - 1; i > -1; i--)
        {
            if (tailsTable[i][location.getY()] == 0)
            {
                counter++;
            } else
            {
                break;
            }
        }
        return counter;
    }

    private int getDownDistance(Location location)
    {
        int counter = 0;
        for (int i = location.getY() - 1; i > -1; i--)
        {
            if (tailsTable[location.getX()][i] == 0)
            {
                counter++;
            } else
            {
                break;
            }
        }
        return counter;
    }

    private int getUpDistance(Location location)
    {
        int counter = 0;
        for (int i = location.getY() + 1; i < ySize; i++)
        {
            if (tailsTable[location.getX()][i] == 0)
            {
                counter++;
            } else
            {
                break;
            }
        }
        return counter;
    }


    @Override
    public double getReward(IState iState, IState iState2, IAction iAction) // iState - stary iState2 - nowy stan.
    {
        PlayerState newState = (PlayerState) iState2;

        if (newState.isAlive())
        {
            return 1.0;
        } else
        {
            return -20.0;
        }

    }

    @Override
    public boolean isFinal(IState iState) //iState -> wstrzykiwane przez refree oznacza stan agenta albo bota
    {
        PlayerState state = (PlayerState) iState;
        return !state.isAlive();
    }


    /**
     * Who won ?
     * <ul>
     * <li> Two-players game :  </li>
     * <ul>
     * <li> Player One : -1</li>
     * <li> Tie : 0 </li>
     * <li> Player Two : 1 </li>
     * </li>
     * </ul>
     * <li> Single-player game : </li>
     * <ul>
     * <li> Win : -1 </li>
     * <li> Null: 0    </li>
     * <li> Loose: 1 </li>
     * </ul>
     * </ul>
     */

    @Override // 1 - agent przergral, 0 - gramy dalej, -1 - wygrales, dostaniesz nagrode. Wołane przez refree
    public int whoWins(IState iState)
    {
        if (isFinal(iState))
        {
            return 1;
        }

        if (botIsDead())
        {
            return -1;
        }
        return 0;
    }

    private boolean botIsDead()
    {
        return false; // TODO metoda sprawdza czy umarł przeciwnik agenta, mozna tu wcisnac nawet ruch bota i od razu sprawdzanie
    }

    @Override
    public IState defaultInitialState()
    {
        initializeTraces();

        Orientation orientation = Orientation.UP;

        int[] startingFreeDistance = calculateFreeArea(startLocation, orientation);

        return new PlayerState(this, startingFreeDistance, startLocation, orientation);
    }

    private void initializeTraces()
    {
        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                tailsTable[i][j] = 0;
            }
        }
    }

    public void printTailsTable()
    {
        for (int j = 0; j < ySize; j++)
        {
            for (int i = 0; i < xSize; i++)
            {

                System.out.print(tailsTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getTailsTable() {
        return this.tailsTable;
    }

    public List<Location> getTrace()
    {
        return trace;
    }
}
