package pl.edu.agh.game.domain;

public class Board //implements IEnvironmentTwoPlayers
{

    private int xSize;
    private int ySize;

    private State oldState = null;

    public Board(int x, int y)
    {
        this.xSize = x;
        this.ySize = y;
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

  /*  @Override
    public ITwoPlayerState defaultInitialTwoPlayerState()
    {
        return new State(this, new Location(3, 3), new Location(7, 7));
    }

    @Override
    public ActionList getActionList(IState iState) // tu mozna ustawic by bot widzial ze sie nie moze ruszyc jak obok jest ściana, ale wejscie na sciane jest ruchem wiec nie mozna tego zabraniac
    {
        ActionList list = new ActionList(iState);
//        list.add(new GameAction(-1));
//        list.add(new GameAction(0));
//        list.add(new GameAction(1));
        return list;
        // czy akcja dotyczy tylko naszego bota, czy takze innych?
        // jesli takze innych to Stan musi być konkretny dla każdego z botów(wymusza to by plansza z traceami była na Board), ponieważ musi być spójna z akcją.
        // Akcja -> ruch jednego z botow?

    }

    @Override
    public IState successorState(IState iState, IAction iAction)
    {
        oldState = (State) iState;
        State stateAfterAction = calculateState(oldState, iAction);
        return stateAfterAction;

        // czy na pewno to tak ma dzialać?
        // stan powinien byc dla planszy, czy dla uzytkownika?
        // stan powinien zawierać glowy, oraz ich orientacje by dalo sie wyznaczyc nowy stan
        //
    }

    private State calculateState(State oldState, IAction iAction)
    {



        return null;  //To change body of created methods use File | Settings | File Templates.
    }


    @Override
    public double getReward(IState iState, IState iState2, IAction iAction)
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isFinal(IState iState)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int whoWins(IState iState)
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }*/
}
