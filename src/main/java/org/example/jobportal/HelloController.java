package org.example.jobportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.*;
import java.util.*;
import org.example.jobportal.JobDialog;


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

    // Job panel fields
    @FXML
    private VBox helloVBox; // Панель с приветствием
    @FXML
    private Label helloLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private ListView<String> jobListView;
    @FXML
    private Button addJobButton;
    @FXML
    private Button editJobButton;
    @FXML
    private Button deleteJobButton;

    private static final String USERS_FILE = "users.txt";
    private static final String JOBS_FILE = "jobs.txt";
    private static final Map<String, String> users = new HashMap<>();
    private static final List<Job> jobs = new ArrayList<>();
    private String currentUser;

    @FXML
    public void initialize() {
        loadUsers();
        loadJobs();
        updateJobListView();
    }

    @FXML
    protected void onLoginButtonClick() {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loginMessageLabel.setText("Login successful!");
            loginMessageLabel.setStyle("-fx-text-fill: green;");
            currentUser = username;
            showHelloScreen();
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
            saveUsers();
            registerMessageLabel.setText("Registration successful!");
            registerMessageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    protected void onLogoutButtonClick() {
        currentUser = null;
        helloVBox.setVisible(false);
        loginUsernameField.clear();
        loginPasswordField.clear();
        loginMessageLabel.setText("");
    }

    private void showHelloScreen() {
        helloVBox.setVisible(true);
        helloLabel.setText("Hello, " + currentUser + "!");
        updateJobListView();
    }

    @FXML
    protected void onAddJobButtonClick() {
        Job job = JobDialog.show(null);
        if (job != null) {
            jobs.add(job);
            saveJobs();
            updateJobListView();
        }
    }

    @FXML
    protected void onEditJobButtonClick() {
        int selectedIndex = jobListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Job job = JobDialog.show(jobs.get(selectedIndex));
            if (job != null) {
                jobs.set(selectedIndex, job);
                saveJobs();
                updateJobListView();
            }
        }
    }

    @FXML
    protected void onDeleteJobButtonClick() {
        int selectedIndex = jobListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.remove(selectedIndex);
            saveJobs();
            updateJobListView();
        }
    }

    private void updateJobListView() {
        jobListView.getItems().clear();
        for (Job job : jobs) {
            jobListView.getItems().add(job.toString());
        }
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

    private void saveJobs() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOBS_FILE))) {
            for (Job job : jobs) {
                writer.write(job.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving jobs: " + e.getMessage());
        }
    }

    private void loadJobs() {
        File file = new File(JOBS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jobs.add(Job.deserialize(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading jobs: " + e.getMessage());
        }
    }
}
