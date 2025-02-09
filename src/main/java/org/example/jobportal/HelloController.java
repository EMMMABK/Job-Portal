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
            // Successful registration
            showAlert("Success", "Registration successful!");

            // Create a new Stage (window) for the job portal
            Stage jobPortalStage = new Stage();

            // Create a new VBox for job portal content
            VBox newJobPane = new VBox();
            newJobPane.getChildren().add(jobPane); // Add jobPane content to new VBox

            // Create a new scene with the job portal content
            Scene jobPortalScene = new Scene(newJobPane, 800, 600); // Set window size

            // Set the scene for the new stage
            jobPortalStage.setScene(jobPortalScene);

            // Show the new job portal window
            jobPortalStage.setTitle("Job Portal");
            jobPortalStage.show();

            // Clear registration fields and hide the register pane
            clearRegisterFields();
            registerPane.setVisible(false);
        } else {
            showAlert("Error", "Please fill in all fields");
        }
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
        } else {
            showAlert("Error", "Please select a vacancy to edit");
        }
    }

    @FXML
    private void deleteJob() {
        int selectedIndex = jobList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.remove(selectedIndex);
        } else {
            showAlert("Error", "Please select a vacancy to delete");
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
