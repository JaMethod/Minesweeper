package objects;

import javafx.scene.control.Label;

public class Tile extends Label{

    private double size;
    private boolean isBomb;
    private boolean isOpened;
    private boolean isFlag;
    private int countBomb;

    public Tile(double size) {
        this.size = size;
        isBomb = false;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public double getSize() {
        return size;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public int getCountBomb() {
        return countBomb;
    }

    public void setCountBomb(int countBomb) {
        this.countBomb = countBomb;
    }

    public boolean isOpened() {
        return isOpened;
    }


    public void setOpened(boolean opened) {
        isOpened = opened;
    }

}
