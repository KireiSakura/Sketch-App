package sketch.it;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Model {
    private ArrayList<IView> views = new ArrayList<>();
    ArrayList<Rectangle> shapeList = new ArrayList<>();
    ArrayList<Circle> shapeList2 = new ArrayList<>();
    ArrayList<Line> shapeList3 = new ArrayList<>();
    Shape currentShape = null;
    int selectShape = 0, noShape = 1, drawShape = 0, fillShape = 0, eraseShape = 0, drawCircle = 0, drawRectangle = 0,
            drawLine = 0, newShape = 0, newCanvas = 0, exit = 0, load = 0;
    Color fillColor = Color.PURPLE, lineColor = Color.BLUE;
    String name, loadName;
    int thick1 = 0, thick2 = 0, thick3 = 0, style1 = 0, style2 = 0, style3 = 0, lineWidth = 0, lineStyle = 0;

    public void addView(IView view) {
        views.add(view);
        view.updateView();
    }

    public void drawShape() {
        drawShape = 1;
        eraseShape = 0;
        selectShape = 0;
        fillShape = 0;
        notifyObservers();
    }

    public void newCanvas() {
        shapeList.clear();
        shapeList2.clear();
        shapeList3.clear();
        currentShape = null;
        fillColor = Color.PURPLE;
        lineColor = Color.BLUE;
        selectShape = 0;
        drawShape = 0;
        fillShape = 0;
        eraseShape = 0;
        drawCircle = 0;
        drawRectangle = 0;
        drawLine = 0;
        newShape = 0;
        exit = 0;
        thick1 = 0;
        thick2 = 0;
        thick3 = 0;
        style1 = 0;
        style2 = 0;
        style3 = 0;
        lineWidth = 0;
        lineStyle = 0;
        load = 0;
        notifyObservers();
        noShape = 1;
        newCanvas = 0;
        notifyObservers();
    }

    public void selectShape() {
        drawShape = 0;
        eraseShape = 0;
        selectShape = 1;
        fillShape = 0;
        notifyObservers();
    }

    public void canvasListener(MouseEvent e) {
        if (drawShape == 1) {
            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                if (drawRectangle == 1) {
                    newShape = 1;
                    Rectangle newRectangle = new Rectangle(0, 0, 0, 0);
                    newRectangle.setFill(fillColor);
                    newRectangle.setVisible(false);
                    if (lineWidth == 1) {
                        if (thick1 == 1) {
                            newRectangle.setStrokeWidth(3);
                        } else if (thick2 == 1) {
                            newRectangle.setStrokeWidth(6);
                        } else if (thick3 == 1) {
                            newRectangle.setStrokeWidth(9);
                        }
                    }

                    if (lineStyle == 1) {
                        if (lineWidth == 0) {
                            newRectangle.setStrokeWidth(3);
                        }

                        if (style2 == 1) {
                            newRectangle.setStrokeLineCap(StrokeLineCap.SQUARE);
                            if (newRectangle.getStrokeWidth() == 3 || lineWidth == 0) {
                                newRectangle.getStrokeDashArray().addAll(5d, 10d);
                            } else {
                                newRectangle.getStrokeDashArray().addAll(5d, 30d);
                            }
                            newRectangle.setStrokeType(StrokeType.OUTSIDE);
                        } else if (style3 == 1) {
                            newRectangle.setStrokeLineCap(StrokeLineCap.ROUND);
                            if (newRectangle.getStrokeWidth() == 3 || lineWidth == 0) {
                                newRectangle.getStrokeDashArray().addAll(0.5d, 10d);
                            } else {
                                newRectangle.getStrokeDashArray().addAll(0.5d, 20d);
                            }
                        }
                    }
                    if (lineWidth == 1 || lineStyle == 1) {
                        newRectangle.setStroke(lineColor);
                    }
                    setDragListeners(newRectangle);
                    shapeList.add(newRectangle);
                    notifyObservers();
                    shapeList.get(shapeList.size() - 1).setTranslateX(e.getX());
                    shapeList.get(shapeList.size() - 1).setTranslateY(e.getY());
                    shapeList.get(shapeList.size() - 1).setVisible(true);
                } else if (drawCircle == 1) {
                    newShape = 1;
                    Circle newCircle = new Circle(0, 0, 0);
                    newCircle.setFill(fillColor);
                    newCircle.setVisible(false);
                    if (lineWidth == 1) {
                        if (thick1 == 1) {
                            newCircle.setStrokeWidth(3);
                        } else if (thick2 == 1) {
                            newCircle.setStrokeWidth(6);
                        } else if (thick3 == 1) {
                            newCircle.setStrokeWidth(9);
                        }
                    }

                    if (lineStyle == 1) {
                        if (lineWidth == 0) {
                            newCircle.setStrokeWidth(3);
                        }

                        if (style2 == 1) {
                            newCircle.setStrokeLineCap(StrokeLineCap.SQUARE);
                            if (newCircle.getStrokeWidth() == 3 || lineWidth == 0) {
                                newCircle.getStrokeDashArray().addAll(5d, 10d);
                            } else {
                                newCircle.getStrokeDashArray().addAll(5d, 30d);
                            }
                            newCircle.setStrokeType(StrokeType.OUTSIDE);
                        } else if (style3 == 1) {
                            newCircle.setStrokeLineCap(StrokeLineCap.ROUND);
                            if (newCircle.getStrokeWidth() == 3 || lineWidth == 0) {
                                newCircle.getStrokeDashArray().addAll(0.5d, 10d);
                            } else {
                                newCircle.getStrokeDashArray().addAll(0.5d, 20d);
                            }
                        }
                    }
                    if (lineWidth == 1 || lineStyle == 1) {
                        newCircle.setStroke(lineColor);
                    }
                    setDragListeners(newCircle);
                    shapeList2.add(newCircle);
                    notifyObservers();
                    shapeList2.get(shapeList2.size() - 1).setTranslateX(e.getX());
                    shapeList2.get(shapeList2.size() - 1).setTranslateY(e.getY());
                    shapeList2.get(shapeList2.size() - 1).setCenterX(e.getX());
                    shapeList2.get(shapeList2.size() - 1).setCenterY(e.getY());
                    shapeList2.get(shapeList2.size() - 1).setVisible(true);
                } else if (drawLine == 1) {
                    newShape = 1;
                    Line newLine = new Line(0, 0, 0, 0);
                    newLine.setStrokeWidth(3);
                    newLine.setVisible(false);
                    if (lineWidth == 1) {
                        if (thick1 == 1) {
                            newLine.setStrokeWidth(3);
                        } else if (thick2 == 1) {
                            newLine.setStrokeWidth(6);
                        } else if (thick3 == 1) {
                            newLine.setStrokeWidth(9);
                        }
                    }

                    if (lineStyle == 1) {
                        if (lineWidth == 0) {
                            newLine.setStrokeWidth(3);
                        }

                        if (style2 == 1) {
                            newLine.setStrokeLineCap(StrokeLineCap.SQUARE);
                            if (newLine.getStrokeWidth() == 3 || lineWidth == 0) {
                                newLine.getStrokeDashArray().addAll(5d, 10d);
                            } else {
                                newLine.getStrokeDashArray().addAll(5d, 30d);
                            }
                        } else if (style3 == 1) {
                            newLine.setStrokeLineCap(StrokeLineCap.ROUND);
                            if (newLine.getStrokeWidth() == 3 || lineWidth == 0) {
                                newLine.getStrokeDashArray().addAll(0.5d, 10d);
                            } else {
                                newLine.getStrokeDashArray().addAll(0.5d, 20d);
                            }
                        }
                    }
                    newLine.setStroke(lineColor);
                    setDragListeners(newLine);
                    shapeList3.add(newLine);
                    notifyObservers();
                    shapeList3.get(shapeList3.size() - 1).setTranslateX(e.getX());
                    shapeList3.get(shapeList3.size() - 1).setTranslateY(e.getY());
                    shapeList3.get(shapeList3.size() - 1).setVisible(true);
                }
            }
            if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if (drawRectangle == 1 && shapeList.get(shapeList.size() - 1).isVisible()) {
                    // if (e.getX() < shapeList.get(shapeList.size() - 1).getTranslateX()) {
                    // shapeList.get(shapeList.size() - 1).setX(e.getX());
                    // } else {
                    // shapeList.get(shapeList.size() - 1).setX(shapeList.get(shapeList.size() -
                    // 1).getTranslateX());
                    // }
                    // if (e.getY() < shapeList.get(shapeList.size() - 1).getTranslateY()) {
                    // shapeList.get(shapeList.size() - 1).setY(e.getY());
                    // } else {
                    // shapeList.get(shapeList.size() - 1).setY(shapeList.get(shapeList.size() -
                    // 1).getTranslateY());
                    // }
                    shapeList.get(shapeList.size() - 1)
                            .setWidth(Math.abs(e.getX() - shapeList.get(shapeList.size() - 1).getTranslateX()));
                    shapeList.get(shapeList.size() - 1)
                            .setHeight(Math.abs(e.getY() - shapeList.get(shapeList.size() - 1).getTranslateY()));
                } else if (drawCircle == 1 && shapeList2.get(shapeList2.size() - 1).isVisible()) {
                    double distance = Math
                            .sqrt(Math.pow(e.getX() - shapeList2.get(shapeList2.size() - 1).getCenterX(), 2)
                                    + Math.pow(e.getY() - shapeList2.get(shapeList2.size() - 1).getCenterY(), 2));
                    shapeList2.get(shapeList2.size() - 1).setRadius(distance / 2);
                    // shapeList2.get(shapeList2.size() - 1)
                    // .setRadiusX((e.getX() - shapeList2.get(shapeList2.size() -
                    // 1).getTranslateX()) / 2);
                    // shapeList2.get(shapeList2.size() - 1)
                    // .setRadiusY((e.getY() - shapeList2.get(shapeList2.size() -
                    // 1).getTranslateY()) / 2);
                    // shapeList2.get(shapeList2.size() -
                    // 1).setCenterX(shapeList2.get(shapeList2.size() - 1).getCenterX()
                    // + (e.getX() - shapeList2.get(shapeList2.size() - 1).getTranslateX()) / 2);
                    // shapeList2.get(shapeList2.size() -
                    // 1).setCenterY(shapeList2.get(shapeList2.size() - 1).getCenterY()
                    // - (e.getY() - shapeList2.get(shapeList2.size() - 1).getTranslateY()) / 2);
                } else if (drawLine == 1 && shapeList3.get(shapeList3.size() - 1).isVisible()) {
                    shapeList3.get(shapeList3.size() - 1)
                            .setEndX(e.getX() - shapeList3.get(shapeList3.size() - 1).getTranslateX());
                    shapeList3.get(shapeList3.size() - 1)
                            .setEndY(e.getY() - shapeList3.get(shapeList3.size() - 1).getTranslateY());
                }
            }
            if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
                noShape = 0;
                newShape = 0;
                notifyObservers();
            }
        }
        if (selectShape == 1) {
            if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
                currentShape.setEffect(null);
                currentShape = null;
                notifyObservers();
            }
        }
    }

    public void fillShape() {
        drawShape = 0;
        eraseShape = 0;
        selectShape = 0;
        fillShape = 1;
        notifyObservers();
    }

    public void eraseShape() {
        drawShape = 0;
        eraseShape = 1;
        selectShape = 0;
        fillShape = 0;
        notifyObservers();
    }

    public void setLineStyle() {
        lineStyle = 1;
        notifyObservers();
    }

    public void setLineWidth() {
        lineWidth = 1;
        notifyObservers();
    }

    // public void setShapeListeners(Shape shape) {
    // if (eraseShape == 1) {
    // shape.setOnMouseClicked(e -> {
    // ((StackPane) shape.getParent()).getChildren().remove(shape);
    // });
    // }
    // }
    public void setDragListeners(final Shape block) {
        final Delta dragDelta = new Delta();

        block.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                if (selectShape == 1) {
                    if (currentShape != null) {
                        currentShape.setEffect(null);
                    }
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(5);
                    dropShadow.setHeight(70);
                    dropShadow.setWidth(70);
                    dropShadow.setColor(Color.BLACK);
                    block.setEffect(dropShadow);
                    dragDelta.x = block.getTranslateX() - mouseEvent.getX();
                    dragDelta.y = block.getTranslateY() - mouseEvent.getY();
                    block.setCursor(Cursor.NONE);
                    currentShape = block;
                    notifyObservers();
                    mouseEvent.consume();
                } else if (eraseShape == 1) {
                    StackPane canvas = (StackPane) block.getParent();
                    ((StackPane) block.getParent()).getChildren().remove(block);
                    if (canvas.getChildren().isEmpty()) {
                        noShape = 1;
                        notifyObservers();
                    }
                } else if (fillShape == 1) {
                    if (block.getFill() != null) {
                        block.setFill(fillColor);
                    } else {
                        block.setStroke(lineColor);
                    }
                }
            }
        });
        block.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (selectShape == 1) {
                    block.setCursor(Cursor.HAND);
                }
            }
        });
        block.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (selectShape == 1) {
                    block.setTranslateX(mouseEvent.getX() + dragDelta.x);
                    block.setTranslateY(mouseEvent.getY() + dragDelta.y);
                }
            }
        });
    }

    public void notifyObservers() {
        for (IView view : this.views) {
            view.updateView();
        }
    }

    class Delta {
        double x, y;
    }

    public void saveShapes(StackPane stackPane) {
        new SaveDialog(stackPane);
    }

    public void saveData(StackPane stackPane) {
        FileWriter file = null;
        BufferedWriter writer = null;

        try {
            file = new FileWriter(new File(name));
            writer = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // one line is written for each row of data
            for (Rectangle rectangle : shapeList) {
                if (stackPane.getChildren().contains(rectangle)) {
                    if (rectangle.getStroke() != null) {
                        writer.write(rectangle.getTranslateX() + "," + rectangle.getTranslateY() + ","
                                + rectangle.getHeight() + "," + rectangle.getWidth() + ","
                                + rectangle.getFill().toString() + "," + rectangle.getStroke().toString() + ","
                                + rectangle.getStrokeWidth() + "," + rectangle.getStrokeType().toString() + ","
                                + rectangle.getStrokeLineCap().toString() + "," + rectangle.getViewOrder() + "\n");
                    } else {
                        writer.write(rectangle.getTranslateX() + "," + rectangle.getTranslateY() + ","
                                + rectangle.getHeight() + "," + rectangle.getWidth() + ","
                                + rectangle.getFill().toString() + "," + rectangle.getViewOrder() + "\n");
                    }
                }
            }

            writer.write("\n");

            for (Circle circle : shapeList2) {
                if (stackPane.getChildren().contains(circle)) {
                    if (circle.getStroke() != null) {
                        writer.write(circle.getTranslateX() + "," + circle.getTranslateY() + "," + circle.getRadius()
                                + "," + circle.getFill().toString() + "," + circle.getStroke().toString() + ","
                                + circle.getStrokeWidth() + "," + circle.getStrokeType().toString() + ","
                                + circle.getStrokeLineCap().toString() + "," + circle.getViewOrder() + "\n");
                    } else {
                        writer.write(circle.getTranslateX() + "," + circle.getTranslateY() + "," + circle.getRadius()
                                + "," + circle.getFill().toString() + "," + circle.getViewOrder() + "\n");
                    }
                }
            }
            writer.write("\n");
            for (Line line : shapeList3) {
                if (stackPane.getChildren().contains(line)) {
                    writer.write(line.getTranslateX() + "," + line.getTranslateY() + "," + line.getEndX() + ","
                            + line.getEndY() + "," + line.getStroke().toString() + "," + line.getStrokeWidth() + ","
                            + line.getStrokeType().toString() + "," + line.getStrokeLineCap().toString() + ","
                            + line.getViewOrder() + "\n");
                }
            }
            writer.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(StackPane group) {
        FileChooser loadFile = new FileChooser();
        loadFile.setTitle("Select File to Load");
        loadFile.setInitialDirectory(new File("."));
        loadFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        Stage stage = (Stage) group.getScene().getWindow();
        File file = loadFile.showOpenDialog(stage);
        if (file != null) {
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            String[] values;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                String line;
                shapeList.clear();
                shapeList2.clear();
                shapeList3.clear();
                group.getChildren().clear();
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        // DELIMITER separates values on a row
                        values = line.split(",");
                        Rectangle rectangle = new Rectangle(0, 0, 0, 0);
                        group.setAlignment(rectangle, Pos.TOP_LEFT);
                        group.getChildren().add(rectangle);
                        if (values.length == 10) {
                            rectangle.setTranslateX(Double.valueOf(values[0]));
                            rectangle.setTranslateY(Double.valueOf(values[1]));
                            rectangle.setHeight(Double.valueOf(values[2]));
                            rectangle.setWidth(Double.valueOf(values[3]));
                            rectangle.setFill(Color.valueOf(values[4]));
                            rectangle.setStroke(Color.valueOf(values[5]));
                            rectangle.setStrokeWidth(Double.valueOf(values[6]));
                            rectangle.setStrokeType(StrokeType.valueOf(values[7]));
                            rectangle.setStrokeLineCap(StrokeLineCap.valueOf(values[8]));
                            rectangle.setViewOrder(Double.valueOf(values[9]));
                        } else {
                            rectangle.setTranslateX(Double.valueOf(values[0]));
                            rectangle.setTranslateY(Double.valueOf(values[1]));
                            rectangle.setHeight(Double.valueOf(values[2]));
                            rectangle.setWidth(Double.valueOf(values[3]));
                            rectangle.setFill(Color.valueOf(values[4]));
                            rectangle.setViewOrder(Double.valueOf(values[5]));
                        }
                        if (rectangle.getStroke() != null) {
                            if (rectangle.getStrokeType() == StrokeType.OUTSIDE) {
                                if (rectangle.getStrokeWidth() == 3) {
                                    rectangle.getStrokeDashArray().addAll(5d, 10d);
                                } else {
                                    rectangle.getStrokeDashArray().addAll(5d, 30d);
                                }
                            } else if (rectangle.getStrokeLineCap() == StrokeLineCap.ROUND) {
                                if (rectangle.getStrokeWidth() == 3) {
                                    rectangle.getStrokeDashArray().addAll(0.5d, 10d);
                                } else {
                                    rectangle.getStrokeDashArray().addAll(0.5d, 20d);
                                }
                            }
                        }
                        setDragListeners(rectangle);
                        shapeList.add(rectangle);
                    } else {
                        break;
                    }
                }

                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        values = line.split(",");
                        Circle circle = new Circle(0, 0, 0);
                        group.setAlignment(circle, Pos.TOP_LEFT);
                        group.getChildren().add(circle);
                        if (values.length == 9) {
                            circle.setTranslateX(Double.valueOf(values[0]));
                            circle.setTranslateY(Double.valueOf(values[1]));
                            circle.setRadius(Double.valueOf(values[2]));
                            circle.setFill(Color.valueOf(values[3]));
                            circle.setStroke(Color.valueOf(values[4]));
                            circle.setStrokeWidth(Double.valueOf(values[5]));
                            circle.setStrokeType(StrokeType.valueOf(values[6]));
                            circle.setStrokeLineCap(StrokeLineCap.valueOf(values[7]));
                            circle.setViewOrder(Double.valueOf(values[8]));
                        } else {
                            circle.setTranslateX(Double.valueOf(values[0]));
                            circle.setTranslateY(Double.valueOf(values[1]));
                            circle.setRadius(Double.valueOf(values[2]));
                            circle.setFill(Color.valueOf(values[3]));
                            circle.setViewOrder(Double.valueOf(values[4]));
                        }
                        if (circle.getStroke() != null) {
                            if (circle.getStrokeType() == StrokeType.OUTSIDE) {
                                if (circle.getStrokeWidth() == 3) {
                                    circle.getStrokeDashArray().addAll(5d, 10d);
                                } else {
                                    circle.getStrokeDashArray().addAll(5d, 30d);
                                }
                            } else if (circle.getStrokeLineCap() == StrokeLineCap.ROUND) {
                                if (circle.getStrokeWidth() == 3) {
                                    circle.getStrokeDashArray().addAll(0.5d, 10d);
                                } else {
                                    circle.getStrokeDashArray().addAll(0.5d, 20d);
                                }
                            }
                        }
                        setDragListeners(circle);
                        shapeList2.add(circle);
                    } else {
                        break;
                    }
                }
                while ((line = bufferedReader.readLine()) != null) {
                    values = line.split(",");
                    Line getLine = new Line(0, 0, 0, 0);
                    group.setAlignment(getLine, Pos.TOP_LEFT);
                    group.getChildren().add(getLine);
                    getLine.setTranslateX(Double.valueOf(values[0]));
                    getLine.setTranslateY(Double.valueOf(values[1]));
                    getLine.setEndX(Double.valueOf(values[2]));
                    getLine.setEndY(Double.valueOf(values[3]));
                    getLine.setStroke(Color.valueOf(values[4]));
                    getLine.setStrokeWidth(Double.valueOf(values[5]));
                    getLine.setStrokeType(StrokeType.valueOf(values[6]));
                    getLine.setStrokeLineCap(StrokeLineCap.valueOf(values[7]));
                    getLine.setViewOrder(Double.valueOf(values[8]));
                    if (getLine.getStroke() != null) {
                        if (getLine.getStrokeType() == StrokeType.OUTSIDE) {
                            if (getLine.getStrokeWidth() == 3) {
                                getLine.getStrokeDashArray().addAll(5d, 10d);
                            } else {
                                getLine.getStrokeDashArray().addAll(5d, 30d);
                            }
                        } else if (getLine.getStrokeLineCap() == StrokeLineCap.ROUND) {
                            if (getLine.getStrokeWidth() == 3) {
                                getLine.getStrokeDashArray().addAll(0.5d, 10d);
                            } else {
                                getLine.getStrokeDashArray().addAll(0.5d, 20d);
                            }
                        }
                    }
                    setDragListeners(getLine);
                    shapeList3.add(getLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (group.getChildren().isEmpty()) {
                noShape = 1;
            } else {
                noShape = 0;
            }
            exit = 0;
            load = 0;
            newCanvas = 0;
            notifyObservers();
        }
    }

    class SaveDialog extends Stage {
        TextField fileName;

        SaveDialog(StackPane stackpane) {
            this.setX(200);
            this.setY(200);
            this.setWidth(400);
            this.setHeight(300);
            fileName = new TextField();
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            root.setSpacing(10);
            root.setPadding(new Insets(0, 20, 0, 20));
            Button done = new Button("Done");
            done.setTextFill(Color.BLUE);
            done.setOnAction(e -> {
                name = fileName.getText();
                saveData(stackpane);
                this.close();
            });
            root.getChildren().addAll(fileName, done);
            this.setScene(new Scene(root));
            this.setTitle("Enter File Name to Save");
            this.show();
        }
    }

}
