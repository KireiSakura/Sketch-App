package sketch.it;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.TilePane;

class ToolView implements IView {

    private Model model;
    TilePane root;
    ToggleButton select, rectangle, circle, line, shapeFill, eraser;
    ToggleGroup group;

    ToolView(Model model) {
        root = new TilePane();
        group = new ToggleGroup();
        Image selection = new Image("selection.png", 50, 50, true, true);
        select = new ToggleButton("", new ImageView(selection));
        select.setPrefSize(100, 100);

        Image circleImg = new Image("Circle.png", 50, 50, true, true);
        circle = new ToggleButton("", new ImageView(circleImg));
        circle.setPrefSize(100, 100);

        Image rectangleImg = new Image("rectangle.png", 50, 50, true, true);
        rectangle = new ToggleButton("", new ImageView(rectangleImg));
        rectangle.setGraphic(new ImageView(rectangleImg));
        rectangle.setPrefSize(100, 100);

        Image lineImage = new Image("line.png", 50, 50, true, true);
        line = new ToggleButton();
        line.setGraphic(new ImageView(lineImage));
        line.setPrefSize(100, 100);

        Image shapeFillImg = new Image("fill-icon.jpg", 50, 50, true, true);
        shapeFill = new ToggleButton("", new ImageView(shapeFillImg));
        shapeFill.setPrefSize(100, 100);

        Image eraserIcon = new Image("eraser.png", 50, 50, true, true);
        eraser = new ToggleButton("", new ImageView(eraserIcon));
        eraser.setPrefSize(100, 100);
        select.setToggleGroup(group);
        circle.setToggleGroup(group);
        rectangle.setToggleGroup(group);
        line.setToggleGroup(group);
        shapeFill.setToggleGroup(group);
        eraser.setToggleGroup(group);
        select.setMinWidth(50);
        select.setMinHeight(50);
        select.setMaxWidth(150);
        select.setMaxHeight(150);
        circle.setMinWidth(50);
        circle.setMinHeight(50);
        circle.setMaxWidth(150);
        circle.setMaxHeight(150);
        rectangle.setMinWidth(50);
        rectangle.setMinHeight(50);
        rectangle.setMaxWidth(150);
        rectangle.setMaxHeight(150);
        line.setMinWidth(50);
        line.setMinHeight(50);
        line.setMaxWidth(150);
        line.setMaxHeight(150);
        shapeFill.setMinWidth(50);
        shapeFill.setMinHeight(50);
        shapeFill.setMaxWidth(150);
        shapeFill.setMaxHeight(150);
        eraser.setMinWidth(50);
        eraser.setMinHeight(50);
        eraser.setMaxWidth(150);
        eraser.setMaxHeight(150);
        root.setPrefColumns(2);
        root.setPrefRows(3);
        root.getChildren().add(select);
        root.getChildren().add(circle);
        root.getChildren().add(rectangle);
        root.getChildren().add(line);
        root.getChildren().add(shapeFill);
        root.getChildren().add(eraser);
        root.setPadding(new Insets(0, 0, 0, 2.25));
        circle.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (circle.isSelected()) {
                model.drawCircle = 1;
                model.drawRectangle = 0;
                model.drawLine = 0;
                model.selectShape = 0;
                model.drawShape();
            } else {
                model.drawCircle = 0;
                model.drawShape = 0;
            }
        });
        rectangle.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (rectangle.isSelected()) {
                model.drawRectangle = 1;
                model.drawCircle = 0;
                model.drawLine = 0;
                model.selectShape = 0;
                model.drawShape();
            } else {
                model.drawRectangle = 0;
                model.drawShape = 0;
            }
        });
        line.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (line.isSelected()) {
                model.drawLine = 1;
                model.drawCircle = 0;
                model.drawRectangle = 0;
                model.selectShape = 0;
                model.drawShape();
            } else {
                model.drawLine = 0;
                model.drawShape = 0;
            }
        });
        select.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (select.isSelected()) {
                model.selectShape();
            } else {
                model.selectShape = 0;
                model.notifyObservers();
            }
        });
        shapeFill.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (shapeFill.isSelected()) {
                model.fillShape();
            } else {
                model.fillShape = 0;
            }
        });
        eraser.setOnMouseClicked(mouseEvent -> {
            if (model.currentShape != null) {
                model.currentShape.setEffect(null);
                model.currentShape = null;
            }
            if (eraser.isSelected()) {
                model.eraseShape();
            } else {
                model.eraseShape = 0;
            }
        });
        this.model = model;
        model.addView(this);
    }

    public void updateView() {
        if (model.newCanvas == 1) {
            rectangle.setSelected(false);
            circle.setSelected(false);
            line.setSelected(false);
        }
        if (model.noShape == 1) {
            select.setDisable(true);
            shapeFill.setDisable(true);
            eraser.setDisable(true);
        } else {
            select.setDisable(false);
            shapeFill.setDisable(false);
            eraser.setDisable(false);
        }
    }
}
