package org.example.jobportal;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;
    @FXML
    private VBox loginVBox; // Панель для логина
    @FXML
    private VBox registerVBox; // Панель для регистрации

    private static final Map<String, String> users = new HashMap<>(); // Хранилище пользователей

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Invalid username or password!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (users.containsKey(username)) {
            messageLabel.setText("User already exists!");
            messageLabel.setStyle("-fx-text-fill: red;");
        } else if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Fields cannot be empty!");
            messageLabel.setStyle("-fx-text-fill: red;");
        } else {
            users.put(username, password);
            messageLabel.setText("Registration successful!");
            messageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    protected void onSwitchToRegister() {
        loginVBox.setVisible(false);  // Скрыть панель логина
        registerVBox.setVisible(true); // Показать панель регистрации
    }

    @FXML
    protected void onSwitchToLogin() {
        registerVBox.setVisible(false); // Скрыть панель регистрации
        loginVBox.setVisible(true); // Показать панель логина
    }
}
