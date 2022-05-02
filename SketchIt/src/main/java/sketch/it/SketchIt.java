package sketch.it;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SketchIt extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // executed after init() method
        Model model = new Model();
        CanvasView canvas = new CanvasView(model);
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem load = new MenuItem("Load");
        MenuItem save = new MenuItem("Save");
        MenuItem quit = new MenuItem("Quit");

        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");

        helpMenu.getItems().add(about);
        fileMenu.getItems().addAll(newItem, load, save, quit);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        quit.setOnAction(actionEvent -> {
            model.exit = 1;
            new AskSave(canvas.group, model);
        });
        about.setOnAction(e -> {
            new About();
        });
        newItem.setOnAction(e -> {
            model.newCanvas = 1;
            new AskSave(canvas.group, model);
        });
        load.setOnAction(e -> {
            model.load = 1;
            new AskSave(canvas.group, model);
        });
        save.setOnAction(e -> {
            model.saveShapes(canvas.group);
        });
        ToolView tools = new ToolView(model);
        PropertiesView properties = new PropertiesView(model);
        BorderPane layout = new BorderPane();
        VBox leftPane = new VBox();
        leftPane.setPrefWidth(205);
        leftPane.setMinWidth(105);
        leftPane.setMaxWidth(305);
        leftPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        leftPane.getChildren().add(tools.root);
        leftPane.getChildren().add(properties.propertiesBox);
        layout.setTop(menuBar);
        layout.setCenter(canvas.group);
        layout.setLeft(leftPane);
        layout.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout, 1280, 730);
        scene.setOnKeyPressed(event -> {
            if (model.selectShape == 1 && model.currentShape != null) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    model.currentShape.setEffect(null);
                    model.currentShape = null;
                    model.notifyObservers();
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    StackPane parent = (StackPane) model.currentShape.getParent();
                    ((StackPane) model.currentShape.getParent()).getChildren().remove(model.currentShape);
                    if (parent.getChildren().isEmpty()) {
                        model.noShape = 1;
                    }
                    model.currentShape.setEffect(null);
                    model.currentShape = null;
                    model.notifyObservers();
                }
            }
        });
        stage.setTitle("SketchIt");
        stage.setMaxHeight(830);
        stage.setMaxWidth(1440);
        stage.setMinHeight(650);
        stage.setMinWidth(910);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
}
