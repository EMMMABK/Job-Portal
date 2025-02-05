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
    // Login panel fields
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button switchToRegisterButton;

    // Register panel fields
    @FXML
    private TextField registerUsernameField;
    @FXML
    private PasswordField registerPasswordField;
    @FXML
    private Label registerMessageLabel;
    @FXML
    private Button registerButton;
    @FXML
    private Button switchToLoginButton;

    @FXML
    private VBox loginVBox; // Login panel
    @FXML
    private VBox registerVBox; // Register panel

    private static final Map<String, String> users = new HashMap<>(); // User storage

    @FXML
    protected void onLoginButtonClick() {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loginMessageLabel.setText("Login successful!");
            loginMessageLabel.setStyle("-fx-text-fill: green;");
        } else {
            loginMessageLabel.setText("Invalid username or password!");
            loginMessageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();

        if (users.containsKey(username)) {
            registerMessageLabel.setText("User already exists!");
            registerMessageLabel.setStyle("-fx-text-fill: red;");
        } else if (username.isEmpty() || password.isEmpty()) {
            registerMessageLabel.setText("Fields cannot be empty!");
            registerMessageLabel.setStyle("-fx-text-fill: red;");
        } else {
            users.put(username, password);
            registerMessageLabel.setText("Registration successful!");
            registerMessageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    protected void onSwitchToRegister() {
        loginVBox.setVisible(false);  // Hide login panel
        registerVBox.setVisible(true); // Show register panel
    }

    @FXML
    protected void onSwitchToLogin() {
        registerVBox.setVisible(false); // Hide register panel
        loginVBox.setVisible(true); // Show login panel
    }
}
