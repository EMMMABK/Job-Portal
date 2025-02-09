package org.example.jobportal;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField loginUsername, registerUsername, jobTitle, jobSalary;

    @FXML
    private PasswordField loginPassword, registerPassword;

    @FXML
    private TextArea jobResponsibilities, jobCertificates, jobSchedule;

    @FXML
    private ListView<String> jobList;

    @FXML
    private VBox loginPane, registerPane, jobPane;

    private final ObservableList<String> jobs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        jobList.setItems(jobs);
    }

    @FXML
    private void handleLogin() {
        if (!loginUsername.getText().isEmpty() && !loginPassword.getText().isEmpty()) {
            loginPane.setVisible(false);
            jobPane.setVisible(true);
        } else {
            showAlert("Error", "Please fill in all fields");
        }
    }

    @FXML
    private void handleRegister() {
        if (!registerUsername.getText().isEmpty() && !registerPassword.getText().isEmpty()) {
            showAlert("Success", "Registration successful!");

            registerPane.setVisible(false);

            jobPane.setVisible(true);

            clearRegisterFields();
        } else {
            showAlert("Error", "Please fill in all fields");
        }
    }

    @FXML
    private void handleLogout() {
        // Hide the job portal view
        jobPane.setVisible(false);

        // Show the login pane again
        loginPane.setVisible(true);

        // Clear any previously entered data
        loginUsername.clear();
        loginPassword.clear();

        // Optionally, reset other fields like job fields if needed
        clearJobFields();
    }

    @FXML
    private void switchToRegister() {
        loginPane.setVisible(false);
        registerPane.setVisible(true);
    }

    @FXML
    private void switchToLogin() {
        registerPane.setVisible(false);
        loginPane.setVisible(true);
    }

    @FXML
    private void createJob() {
        String title = jobTitle.getText();
        if (!title.isEmpty()) {
            jobs.add(title);
            clearJobFields();
            // Scroll to the top of the list after adding the job
            jobList.scrollTo(0);
        } else {
            showAlert("Error", "Job title cannot be empty");
        }
    }


    @FXML
    private void editJob() {
        int selectedIndex = jobList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.set(selectedIndex, jobTitle.getText());
            clearJobFields();
            // Scroll to the top of the list after editing the job
            jobList.scrollTo(0);
        } else {
            showAlert("Error", "Please select a job to edit");
        }
    }


    @FXML
    private void deleteJob() {
        int selectedIndex = jobList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.remove(selectedIndex);
            // Scroll to the top of the list after deleting a job
            jobList.scrollTo(0);
        } else {
            showAlert("Error", "Please select a job to delete");
        }
    }


    private void clearJobFields() {
        jobTitle.clear();
        jobResponsibilities.clear();
        jobCertificates.clear();
        jobSchedule.clear();
        jobSalary.clear();
    }

    private void clearRegisterFields() {
        registerUsername.clear();
        registerPassword.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
