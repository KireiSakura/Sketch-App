package sketch.it;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AskSave extends Stage {
    AskSave(StackPane canvasPane, Model model) {
        this.setX(100);
        this.setY(100);
        this.setWidth(300);
        this.setHeight(200);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        Label question = new Label("Do you wish to save before continuing?");
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        Button yes = new Button("YES");
        Button no = new Button("NO");
        yes.setOnAction(e -> {
            if (model.exit == 1) {
                model.saveShapes(canvasPane);
                System.exit(0);
            } else if (model.newCanvas == 1) {
                this.close();
                model.saveShapes(canvasPane);
                model.newCanvas();
            } else if (model.load == 1) {
                this.close();
                model.saveShapes(canvasPane);
                model.loadData(canvasPane);
            }
        });
        no.setOnAction(e -> {
            if (model.exit == 1) {
                System.exit(0);
            } else if (model.newCanvas == 1) {
                this.close();
                model.newCanvas();
            } else if (model.load == 1) {
                this.close();
                model.loadData(canvasPane);
            }
        });
        buttons.getChildren().addAll(yes, no);
        root.getChildren().addAll(question, buttons);
        this.setScene(new Scene(root));
        this.setTitle("Save");
        this.show();
    }
}
