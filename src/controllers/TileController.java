package controllers;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import objects.Tile;

public class TileController {
    private static final int fontSize = 20;
    private Tile tile;
    static int openedTile;
    static int countFlags;

    public TileController(Tile tile) {
        this.tile = tile;
    }


    public void initTileView() {
        tile.setStyle("-fx-background-color: #dfe1ff");
        tile.setPrefSize(tile.getSize(), tile.getSize());
        tile.setMinWidth(tile.getSize());
        tile.setFont(Font.font(fontSize));

        tile.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                openTile();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (!tile.isFlag()) {
                    setFlag();
                } else {
                    unFlag();
                }
            }
            if (new GameController().isWon()) {
                System.out.println("ПОБЕДА");
            }
        });

        tile.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!tile.isOpened() && !tile.isFlag()) {
                    tile.setStyle("-fx-background-color: red");
                }
            }
        });

        tile.setOnMouseReleased(event -> {
            if (!tile.isOpened()) {
                tile.setStyle("-fx-background-color: #dfe1ff");
            }
        });
    }

    private void openTile() {
        if (tile.isOpened() || tile.isFlag()) {
            return;
        }
        tile.setTextFill(Paint.valueOf("blue"));
        tile.setStyle("-fx-background-color: white");

        if (tile.isBomb()) {
            tile.setText("  X");
            tile.setTextFill(Paint.valueOf("#FF0000"));
            System.out.println("ПОРАЖЕНИЕ");
        } else if (tile.getCountBomb() > 0) {
            openedTile++;
            tile.setText("  " + tile.getCountBomb());
        } else {
            openedTile++;
        }
        tile.setOpened(true);
    }

    private void setFlag() {
        if (!tile.isOpened()) {
            tile.setFlag(true);
            tile.setTextFill(Paint.valueOf("#FF0000"));
            tile.setText("  F");
            countFlags++;
        }
    }

    private void unFlag() {
        tile.setFlag(false);
        tile.setText("");
        countFlags--;
    }
}