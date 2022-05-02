package sketch.it;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class About extends Stage {
    About() {
        this.setX(200);
        this.setY(200);
        this.setWidth(400);
        this.setHeight(400);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        Label program, name, watid;
        program = new Label("Program: Computer Science");
        program.setTextFill(Color.LIGHTGREEN);
        program.setFont(new Font("Verdana", 25));
        name = new Label("Name: Shruti Vishrant");
        name.setTextFill(Color.CYAN);
        name.setFont(new Font("Tahoma", 25));
        watid = new Label("userid: svishran");
        watid.setTextFill(Color.PINK);
        watid.setFont(new Font("Tahoma", 25));
        Button done = new Button("Done");
        done.setTextFill(Color.BLUE);
        done.setOnAction(e -> {
            this.close();
        });
        root.getChildren().addAll(program, name, watid, done);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setScene(new Scene(root));
        this.setTitle("About");
        this.show();
    }
}
