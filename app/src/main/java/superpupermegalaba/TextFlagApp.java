package superpupermegalaba;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TextFlagApp extends Application {

    private final ToggleGroup topGroup = new ToggleGroup();
    private final ToggleGroup middleGroup = new ToggleGroup();
    private final ToggleGroup bottomGroup = new ToggleGroup();
    private final Label resultLabel = new Label();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");

        
        Label title = new Label("Конструктор флага");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

        root.getChildren().addAll(
                title,
                createRow("Верхняя полоса", topGroup),
                createRow("Средняя полоса", middleGroup),
                createRow("Нижняя полоса", bottomGroup)
        );

        Button drawButton = new Button("Нарисовать флаг");
        drawButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 16;");
        drawButton.setOnAction(e -> drawFlag());
        
        
        resultLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-border-color: #ddd; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");
        resultLabel.setMaxWidth(Double.MAX_VALUE);
        resultLabel.setPrefHeight(40);
        
        root.getChildren().addAll(drawButton, resultLabel);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Текстовый флаг");
        stage.setResizable(false);
        stage.show();
    }

    private VBox createRow(String label, ToggleGroup group) {
        VBox row = new VBox(8);
        row.setStyle("-fx-border-color: #ddd; -fx-border-width: 1; -fx-background-color: white; -fx-padding: 10;");
        
        Label rowLabel = new Label(label);
        rowLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #333;");
        
        HBox buttonsBox = new HBox(10);
        RadioButton red = new RadioButton("Красный");
        RadioButton green = new RadioButton("Зелёный");
        RadioButton blue = new RadioButton("Синий");
        RadioButton white = new RadioButton("Белый");
        
        
        red.setStyle("-fx-text-fill: #ff4444; -fx-font-weight: bold;");
        green.setStyle("-fx-text-fill: #44ff44; -fx-font-weight: bold;");
        blue.setStyle("-fx-text-fill: #4444ff; -fx-font-weight: bold;");
        white.setStyle("-fx-text-fill: #888888; -fx-font-weight: bold;");
        
        red.setToggleGroup(group);
        green.setToggleGroup(group);
        blue.setToggleGroup(group);
        white.setToggleGroup(group);
        
        buttonsBox.getChildren().addAll(red, green, blue, white);
        row.getChildren().addAll(rowLabel, buttonsBox);
        return row;
    }

    private void drawFlag() {
        String top = getSelected(topGroup);
        String mid = getSelected(middleGroup);
        String bot = getSelected(bottomGroup);

        if (top == null || mid == null || bot == null) {
            resultLabel.setText("Выберите цвета для всех полос!");
            resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-border-color: #ddd; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");
            return;
        }

        resultLabel.setText("Флаг создан: " + top + ", " + mid + ", " + bot);
        resultLabel.setStyle("-fx-text-fill: #27ae60; -fx-border-color: #ddd; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");
    }

    private String getSelected(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected == null ? null : selected.getText();
    }

    public static void main(String[] args) {
        launch(args);
    }
}