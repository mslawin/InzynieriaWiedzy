package pl.edu.agh.game.domain;

public class Board {

    private int xSize;
    private int ySize;


    public Board(int x, int y) {
        this.xSize = x;
        this.ySize = y;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }
}
