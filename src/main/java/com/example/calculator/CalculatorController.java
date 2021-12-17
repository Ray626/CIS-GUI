package com.example.calculator;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField display;
    private String expression = "";

    @FXML
    public void buttonOnClickBasic(Event event) {
        Button button = (Button) event.getTarget();
        expression += button.getText();
        display.setText(expression);
    }
}