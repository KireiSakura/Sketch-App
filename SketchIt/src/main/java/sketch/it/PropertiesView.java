package sketch.it;

import javax.swing.border.StrokeBorder;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

class PropertiesView implements IView {
    private Model model;
    ColorPicker lineColor, fillColor;
    VBox propertiesBox;
    ToggleGroup group1, group2;
    ToggleButton thick1, thick2, thick3, style1, style2, style3;
    TilePane tilepane1, tilepane2;

    PropertiesView(Model model) {
        this.model = model;
        propertiesBox = new VBox();
        lineColor = new ColorPicker();
        fillColor = new ColorPicker();
        lineColor.setValue(Color.BLUE);
        fillColor.setValue(Color.PURPLE);
        lineColor.setPrefSize(205, 50);
        lineColor.setMinHeight(30);
        lineColor.setMaxHeight(70);
        fillColor.setPrefSize(205, 50);
        fillColor.setMinHeight(30);
        fillColor.setMaxHeight(70);
        Label line = new Label("Line Color");
        Label fill = new Label("Fill Color");
        Label lineWidth = new Label("Line Thickness");
        Label lineStyle = new Label("Line Style");
        line.setPadding(new Insets(0, 0, 0, 10));
        fill.setPadding(new Insets(0, 0, 0, 10));
        lineWidth.setPadding(new Insets(0, 0, 0, 10));
        lineStyle.setPadding(new Insets(0, 0, 0, 10));
        group1 = new ToggleGroup();
        group2 = new ToggleGroup();
        Image thickness1 = new Image("thinline.png", 50, 50, true, true);
        thick1 = new ToggleButton("", new ImageView(thickness1));
        thick1.setPrefSize(66.667, 66);

        Image thickness2 = new Image("thickline.png", 50, 50, true, true);
        thick2 = new ToggleButton("", new ImageView(thickness2));
        thick2.setPrefSize(66.667, 66);

        Image thickness3 = new Image("verythickline.png", 50, 50, true, true);
        thick3 = new ToggleButton("", new ImageView(thickness3));
        thick3.setPrefSize(66.667, 66);

        thick1.setToggleGroup(group1);
        thick2.setToggleGroup(group1);
        thick3.setToggleGroup(group1);

        tilepane1 = new TilePane();
        tilepane1.setPrefColumns(3);
        tilepane1.setPrefRows(1);
        tilepane1.getChildren().addAll(thick1, thick2, thick3);

        tilepane2 = new TilePane();
        // Line Dotted by Hea Poh Lin from the Noun Project
        Image dashedLine = new Image("dashed.png", 50, 50, true, true);

        // dotted line by Rflor from the Noun Project
        Image dottedLine = new Image("dotted.png", 50, 50, true, true);

        style1 = new ToggleButton("", new ImageView(thickness1));
        style1.setPrefSize(66.667, 66);

        style2 = new ToggleButton("", new ImageView(dashedLine));
        style2.setPrefSize(66.667, 66);

        style3 = new ToggleButton("", new ImageView(dottedLine));
        style3.setPrefSize(66.667, 66);

        style1.setToggleGroup(group2);
        style2.setToggleGroup(group2);
        style3.setToggleGroup(group2);

        tilepane2.setPrefColumns(3);
        tilepane2.setPrefRows(1);
        tilepane2.getChildren().addAll(style1, style2, style3);

        tilepane1.setMinHeight(50);
        tilepane2.setMinHeight(50);

        thick1.setMinHeight(50);
        thick2.setMinHeight(50);
        thick3.setMinHeight(50);
        style1.setMinHeight(50);
        style2.setMinHeight(50);
        style3.setMinHeight(50);

        propertiesBox.getChildren().addAll(line, lineColor, fill, fillColor, lineWidth, tilepane1, lineStyle,
                tilepane2);
        propertiesBox.setSpacing(5);
        propertiesBox.setPadding(new Insets(10, 0, 0, 0));

        thick1.setOnMouseClicked(e -> {
            if (thick1.isSelected()) {
                model.thick1 = 1;
                model.thick2 = 0;
                model.thick3 = 0;
                if (model.currentShape != null) {
                    model.currentShape.setStroke(model.lineColor);
                    model.currentShape.setStrokeWidth(3);
                }
                model.setLineWidth();
            } else {
                model.thick1 = 0;
                model.lineWidth = 0;
                if (model.currentShape != null) {
                    if (!style1.isSelected() && !style2.isSelected() && !style3.isSelected()) {
                        model.currentShape.setStroke(null);
                    }
                }
            }
        });

        thick2.setOnMouseClicked(e -> {
            if (thick2.isSelected()) {
                model.thick2 = 1;
                model.thick3 = 0;
                model.thick1 = 0;
                if (model.currentShape != null) {
                    model.currentShape.setStroke(model.lineColor);
                    model.currentShape.setStrokeWidth(6);
                }
                model.setLineWidth();
            } else {
                model.thick2 = 0;
                model.lineWidth = 0;
                if (model.currentShape != null) {
                    if (!style1.isSelected() && !style2.isSelected() && !style3.isSelected()) {
                        model.currentShape.setStroke(null);
                    } else {
                        model.currentShape.setStrokeWidth(3);
                    }
                }
            }
        });

        thick3.setOnMouseClicked(e -> {
            if (thick3.isSelected()) {
                model.thick3 = 1;
                model.thick2 = 0;
                model.thick1 = 0;
                if (model.currentShape != null) {
                    model.currentShape.setStroke(model.lineColor);
                    model.currentShape.setStrokeWidth(9);
                }
                model.setLineWidth();
            } else {
                model.thick3 = 0;
                model.lineWidth = 0;
                if (model.currentShape != null) {
                    if (!style1.isSelected() && !style2.isSelected() && !style3.isSelected()) {
                        model.currentShape.setStroke(null);
                    } else {
                        model.currentShape.setStrokeWidth(3);
                    }
                }
            }
        });

        style1.setOnMouseClicked(e ->

        {
            if (style1.isSelected()) {
                if (model.currentShape != null) {
                    model.currentShape.getStrokeDashArray().clear();
                    model.currentShape.setStrokeLineCap(null);
                    model.currentShape.setStroke(model.lineColor);
                    model.currentShape.setStrokeType(StrokeType.CENTERED);
                    if (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected()) {
                        model.currentShape.setStrokeWidth(3);
                    }
                }
                model.style1 = 1;
                model.style2 = 0;
                model.style3 = 0;
                model.setLineStyle();
            } else {
                model.style1 = 0;
                model.lineStyle = 0;
                if (model.currentShape != null) {
                    if (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected()) {
                        model.currentShape.setStroke(null);
                        model.currentShape.setStrokeType(null);
                    }
                }
            }
        });

        style2.setOnMouseClicked(e -> {
            if (style2.isSelected()) {
                if (model.currentShape != null) {
                    model.currentShape.getStrokeDashArray().clear();
                    model.currentShape.setStrokeLineCap(StrokeLineCap.SQUARE);
                    model.currentShape.setStrokeType(StrokeType.OUTSIDE);
                    model.currentShape.setStroke(model.lineColor);
                    if (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected()) {
                        model.currentShape.setStrokeWidth(3);
                    }
                    if (thick1.isSelected() || (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected())) {
                        model.currentShape.getStrokeDashArray().addAll(5d, 10d);
                    } else {
                        model.currentShape.getStrokeDashArray().addAll(5d, 30d);
                    }
                }
                model.style2 = 1;
                model.style3 = 0;
                model.style1 = 0;
                model.setLineStyle();
            } else {
                model.style2 = 0;
                model.lineStyle = 0;
                if (model.currentShape != null) {
                    model.currentShape.setStroke(null);
                    model.currentShape.setStrokeType(null);
                    model.currentShape.getStrokeDashArray().clear();
                    if (thick1.isSelected() || thick2.isSelected() || thick3.isSelected()) {
                        model.currentShape.setStrokeType(StrokeType.CENTERED);
                        model.currentShape.setStroke(model.lineColor);
                        if (thick1.isSelected()) {
                            model.currentShape.setStrokeWidth(3);
                        } else if (thick2.isSelected()) {
                            model.currentShape.setStrokeWidth(6);
                        } else {
                            model.currentShape.setStrokeWidth(9);
                        }
                    }
                }
            }
        });

        style3.setOnMouseClicked(e -> {
            if (style3.isSelected()) {
                if (model.currentShape != null) {
                    model.currentShape.getStrokeDashArray().clear();
                    model.currentShape.setStrokeLineCap(StrokeLineCap.ROUND);
                    model.currentShape.setStrokeType(StrokeType.CENTERED);
                    model.currentShape.setStroke(model.lineColor);
                    if (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected()) {
                        model.currentShape.setStrokeWidth(3);
                    }
                    if (thick1.isSelected() || (!thick1.isSelected() && !thick2.isSelected() && !thick3.isSelected())) {
                        model.currentShape.getStrokeDashArray().addAll(0.5d, 10d);
                    } else {
                        model.currentShape.getStrokeDashArray().addAll(0.5d, 20d);
                    }
                }
                model.style3 = 1;
                model.style2 = 0;
                model.style1 = 0;
                model.setLineStyle();
            } else {
                model.style3 = 0;
                model.lineStyle = 0;
                if (model.currentShape != null) {
                    model.currentShape.setStroke(null);
                    model.currentShape.setStrokeType(null);
                    // if (thick1.isSelected() || (!thick1.isSelected() && !thick2.isSelected() &&
                    // !thick3.isSelected())) {
                    // model.currentShape.getStrokeDashArray().removeAll(0.5d, 10d);
                    // } else {
                    // model.currentShape.getStrokeDashArray().removeAll(0.5d, 20d);
                    // }
                    model.currentShape.getStrokeDashArray().clear();
                    if (thick1.isSelected() || thick2.isSelected() || thick3.isSelected()) {
                        model.currentShape.setStrokeType(StrokeType.CENTERED);
                        model.currentShape.setStroke(model.lineColor);
                        if (thick1.isSelected()) {
                            model.currentShape.setStrokeWidth(3);
                        } else if (thick2.isSelected()) {
                            model.currentShape.setStrokeWidth(6);
                        } else {
                            model.currentShape.setStrokeWidth(9);
                        }
                    }
                }
            }
        });

        fillColor.setOnAction(e -> {
            if (model.currentShape != null) {
                model.currentShape.setFill(fillColor.getValue());
            }
            model.fillColor = fillColor.getValue();
        });

        lineColor.setOnAction(e -> {
            if (model.currentShape != null) {
                if (model.currentShape.getStroke() != null) {
                    model.currentShape.setStroke(lineColor.getValue());
                } else {
                    thick1.setDisable(false);
                    thick2.setDisable(false);
                    thick3.setDisable(false);
                    style1.setDisable(false);
                    style2.setDisable(false);
                    style3.setDisable(false);
                    thick1.setSelected(false);
                    thick2.setSelected(false);
                    thick3.setSelected(false);
                    style1.setSelected(false);
                    style2.setSelected(false);
                    style3.setSelected(false);
                }
            }
            model.lineColor = lineColor.getValue();
        });

        model.addView(this);
    }

    @Override
    public void updateView() {
        if (model.drawShape == 1) {
            thick1.setDisable(false);
            thick2.setDisable(false);
            thick3.setDisable(false);
            style1.setDisable(false);
            style2.setDisable(false);
            style3.setDisable(false);
            lineColor.setDisable(false);
            lineColor.setValue(model.lineColor);
            if (model.drawLine == 1) {
                fillColor.setDisable(true);
            } else {
                fillColor.setDisable(false);
            }
        }
        if (model.eraseShape == 1) {
            fillColor.setDisable(true);
            lineColor.setDisable(true);
            thick1.setDisable(true);
            thick2.setDisable(true);
            thick3.setDisable(true);
            style1.setDisable(true);
            style2.setDisable(true);
            style3.setDisable(true);
        }
        if (model.fillShape == 1) {
            fillColor.setDisable(false);
            lineColor.setDisable(false);
            thick1.setDisable(true);
            thick2.setDisable(true);
            thick3.setDisable(true);
            style1.setDisable(true);
            style2.setDisable(true);
            style3.setDisable(true);
            lineColor.setValue(model.lineColor);
        }
        if (model.selectShape == 1) {
            if (model.currentShape == null) {
                fillColor.setDisable(true);
                lineColor.setDisable(true);
                thick1.setDisable(true);
                thick2.setDisable(true);
                thick3.setDisable(true);
                style1.setDisable(true);
                style2.setDisable(true);
                style3.setDisable(true);
            } else {
                fillColor.setDisable(false);
                lineColor.setDisable(false);
                thick1.setDisable(false);
                thick2.setDisable(false);
                thick3.setDisable(false);
                style1.setDisable(false);
                style2.setDisable(false);
                style3.setDisable(false);
                if (model.currentShape.getFill() != null) {
                    fillColor.setValue((Color) model.currentShape.getFill());
                    model.fillColor = fillColor.getValue();
                } else {
                    fillColor.setDisable(true);
                }
                lineColor.setValue((Color) model.currentShape.getStroke());
                if (lineColor.getValue() != null) {
                    model.lineColor = lineColor.getValue();
                }
                if (model.currentShape.getStroke() != null) {
                    if (model.currentShape.getStrokeWidth() == 3) {
                        thick1.setSelected(true);
                        model.thick1 = 1;
                        model.thick2 = 0;
                        model.thick3 = 0;
                        model.lineWidth = 1;
                    } else if (model.currentShape.getStrokeWidth() == 6) {
                        thick2.setSelected(true);
                        model.thick1 = 0;
                        model.thick2 = 1;
                        model.thick3 = 0;
                        model.lineWidth = 1;
                    } else if (model.currentShape.getStrokeWidth() == 9) {
                        thick3.setSelected(true);
                        model.thick1 = 0;
                        model.thick2 = 0;
                        model.thick3 = 1;
                        model.lineWidth = 1;
                    }
                    if (model.currentShape.getStrokeLineCap() == StrokeLineCap.ROUND) {
                        style3.setSelected(true);
                        model.style1 = 0;
                        model.style2 = 0;
                        model.style3 = 1;
                        model.lineStyle = 1;
                    } else if (model.currentShape.getStrokeType() == StrokeType.OUTSIDE) {
                        style2.setSelected(true);
                        model.style1 = 0;
                        model.style2 = 1;
                        model.style3 = 0;
                        model.lineStyle = 1;
                    } else {
                        style1.setSelected(true);
                        model.style1 = 0;
                        model.style2 = 0;
                        model.style3 = 1;
                        model.lineStyle = 1;
                    }
                } else {
                    thick1.setDisable(true);
                    thick2.setDisable(true);
                    thick3.setDisable(true);
                    style1.setDisable(true);
                    style2.setDisable(true);
                    style3.setDisable(true);
                }
            }
        }
    }
}
