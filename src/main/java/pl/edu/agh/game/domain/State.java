package pl.edu.agh.game.domain;

import environment.AbstractTwoPlayerState;
import environment.IEnvironment;
import environment.IState;

public class State extends AbstractTwoPlayerState
{
    private int[][] tracesTable; // 0 - empy, 1 - filled A(learning agent), 2 - filled B(static bot), 11 - headA, 22 - headB

    public State(IEnvironment ct, Location locationA, Location locationB)
    {
        super(ct);
        Board board = (Board) ct;
        tracesTable = new int[board.getxSize()][board.getySize()];

        fillwith0(tracesTable, board.getxSize(), board.getySize());

        tracesTable[locationA.getX()][locationA.getY()] = 1;
        tracesTable[locationB.getY()][locationB.getY()] = 2;
    }

    private void fillwith0(int[][] tracesTable, int xSize, int ySize)
    {
        for (int i = 0; i < xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                tracesTable[i][j] = 0;
            }
        }
    }

    @Override
    public IState copy()
    {
        return this; // TODO ?
    }

    @Override
    public int nnCodingSize()
    {
        return 0;  // TODO
    }

    @Override
    public double[] nnCoding()
    {
        return new double[0];  // wykorzystywane przez agenty, dlatego musi miec wspolny format ? TODO ?
    }

    public int[][] getTracesTable()
    {
        return tracesTable;
    }

    public void setTracesTable(int[][] tracesTable)
    {
        this.tracesTable = tracesTable;
    }
}
