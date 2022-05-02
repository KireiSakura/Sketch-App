package sketch.it;

import javafx.scene.control.MenuBar;
import javafx.scene.control.*;

class ToolbarView implements IView {
    private Model model;
    MenuBar menuBar;

    ToolbarView(Model model) {
        this.model = model;
        menuBar = new MenuBar();
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
        about.setOnAction(e -> {
            new About();
        });
        model.addView(this);
    }

    public void updateView() {

    }
}
