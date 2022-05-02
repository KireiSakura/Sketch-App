package sketch.it;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;

class CanvasView implements IView {

    private Model model;
    StackPane group;

    CanvasView(Model model) {
        this.model = model;
        group = new StackPane();
        group.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.canvasListener(mouseEvent);
            }
        });
        model.addView(this);
    }

    public void updateView() {
        if (model.newCanvas == 1) {
            group.getChildren().clear();
        }
        if (model.newShape == 1) {
            if (model.drawRectangle == 1) {
                Rectangle newRectangle = model.shapeList.get(model.shapeList.size() - 1);
                group.setAlignment(newRectangle, Pos.TOP_LEFT);
                group.getChildren().add(newRectangle);
            } else if (model.drawCircle == 1) {
                Circle newCircle = model.shapeList2.get(model.shapeList2.size() - 1);
                group.setAlignment(newCircle, Pos.TOP_LEFT);
                group.getChildren().add(newCircle);
            } else if (model.drawLine == 1) {
                Line newLine = model.shapeList3.get(model.shapeList3.size() - 1);
                group.setAlignment(newLine, Pos.TOP_LEFT);
                group.getChildren().add(newLine);
            }
        }

    }
}
