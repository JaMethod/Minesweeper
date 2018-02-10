package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import objects.Tile;

import java.util.Random;

public class GameController {
    private int column;
    private int row;
    private static int countBomb;
    private static final int tileSize = 38;
    private static int countTile;
    private boolean isPaused;
    private static Tile[][] tiles;

    @FXML
    private GridPane pane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public TextField height;

    @FXML
    public TextField width;

    @FXML
    public TextField bombs;


    @FXML
    public void initialize() {
        column = Integer.parseInt(height.getText());
        row = Integer.parseInt(width.getText());
        countBomb = Integer.parseInt(bombs.getText());
        countTile = column * row;
        initTile();
        initBomb();
        initNum();
    }

    private void initTile() {
        tiles = new Tile[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                tiles[i][j] = new Tile(tileSize);
                new TileController(tiles[i][j]).initTileView();
                pane.add(tiles[i][j], i, j);
            }
        }
        pane.setGridLinesVisible(true);
    }

    private void initBomb() {
        Random random = new Random();
        for (int i = countBomb; i > 0;) {
            int a = random.nextInt(column);
            int b = random.nextInt(row);
            if (!tiles[a][b].isBomb()) {
                tiles[a][b].setBomb(true);
                i--;
            }
        }
    }

    private void initNum() {
        int countBomb;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                countBomb = 0;
                if (tiles[i][j].isBomb()) {
                    continue;
                }
                if (j+1 < row) {
                    if (tiles[i][j+1].isBomb()) {
                        countBomb++;
                    }
                }
                if (j-1 >= 0) {
                    if (tiles[i][j-1].isBomb()) {
                        countBomb++;
                    }
                }
                if (i+1 < column) {
                    if (tiles[i+1][j].isBomb()) {
                        countBomb++;
                    }
                }
                if (i-1 >= 0) {
                    if (tiles[i-1][j].isBomb()) {
                        countBomb++;
                    }
                }
                if (i+1 < column && j+1 < row) {
                    if (tiles[i+1][j+1].isBomb()) {
                        countBomb++;
                    }
                }
                if (i-1 >= 0 && j+1 < row) {
                    if (tiles[i-1][j+1].isBomb()) {
                        countBomb++;
                    }
                }
                if (i+1 < column && j-1 >= 0) {
                    if (tiles[i+1][j-1].isBomb()) {
                        countBomb++;
                    }
                }
                if (i-1 >= 0 && j-1 >= 0) {
                    if (tiles[i-1][j-1].isBomb()) {
                        countBomb++;
                    }
                }
                tiles[i][j].setCountBomb(countBomb);
            }
        }
    }

    public boolean isWon() {
        boolean result = countTile - TileController.openedTile == countBomb;
        if (result) {
            pane.setVisible(false);
        }
        return result;
    }

    public void openEmpty(Tile tile) {

    }

    @FXML
    private void newGame(MouseEvent mouseEvent) {
        if (isPaused) {
            scrollPane.setDisable(false);
            scrollPane.setVisible(true);
            isPaused = false;
        }
        column = Integer.parseInt(height.getText());
        row = Integer.parseInt(width.getText());
        countBomb = Integer.parseInt(bombs.getText());
        countTile = column * row;
        pane.setGridLinesVisible(false);
        initTile();
        pane.setGridLinesVisible(true);
        initBomb();
        initNum();
    }

    @FXML
    private void pause(MouseEvent mouseEvent) {
        if (scrollPane.isDisabled()) {
            scrollPane.setDisable(false);
            scrollPane.setVisible(true);
            isPaused = false;
        } else {
            scrollPane.setDisable(true);
            scrollPane.setVisible(false);
            isPaused = true;
        }
    }

    @FXML
    private void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @FXML
    private void getScore(MouseEvent mouseEvent) {

    }
}