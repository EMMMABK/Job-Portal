package org.example.jobportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.*;
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

    // Panels
    @FXML
    private VBox loginVBox;
    @FXML
    private VBox registerVBox;
    @FXML
    private VBox helloVBox; // Панель для "Hello World"
    @FXML
    private Label helloLabel; // Надпись "Hello World"

    private static final String USERS_FILE = "users.txt"; // Файл для хранения пользователей
    private static final Map<String, String> users = new HashMap<>();

    @FXML
    public void initialize() {
        loadUsers(); // Загружаем пользователей при запуске
    }

    @FXML
    protected void onLoginButtonClick() {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loginMessageLabel.setText("Login successful!");
            loginMessageLabel.setStyle("-fx-text-fill: green;");
            showHelloScreen(username); // Показать "Hello World"
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
            saveUsers(); // Сохранить в файл
            registerMessageLabel.setText("Registration successful!");
            registerMessageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    protected void onSwitchToRegister() {
        loginVBox.setVisible(false);
        registerVBox.setVisible(true);
    }

    @FXML
    protected void onSwitchToLogin() {
        registerVBox.setVisible(false);
        loginVBox.setVisible(true);
    }

    private void showHelloScreen(String username) {
        loginVBox.setVisible(false);
        registerVBox.setVisible(false);
        helloVBox.setVisible(true);
        helloLabel.setText("Hello, " + username + "!");
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }
}
