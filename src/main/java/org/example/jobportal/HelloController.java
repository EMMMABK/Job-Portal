package org.example.jobportal;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

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

    private ObservableList<String> jobs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        jobList.setItems(jobs);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if (!loginUsername.getText().isEmpty() && !loginPassword.getText().isEmpty()) {
            loginPane.setVisible(false);
            jobPane.setVisible(true);
        } else {
            showAlert("Ошибка", "Заполните все поля");
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        if (!registerUsername.getText().isEmpty() && !registerPassword.getText().isEmpty()) {
            showAlert("Успех", "Регистрация успешна!");
            switchToLogin();
        } else {
            showAlert("Ошибка", "Заполните все поля");
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
            showAlert("Ошибка", "Название работы не может быть пустым");
        }
    }

    @FXML
    private void editJob() {
        int selectedIndex = jobList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.set(selectedIndex, jobTitle.getText());
            clearJobFields();
        } else {
            showAlert("Ошибка", "Выберите вакансию для редактирования");
        }
    }

    @FXML
    private void deleteJob() {
        int selectedIndex = jobList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            jobs.remove(selectedIndex);
        } else {
            showAlert("Ошибка", "Выберите вакансию для удаления");
        }
    }

    private void clearJobFields() {
        jobTitle.clear();
        jobResponsibilities.clear();
        jobCertificates.clear();
        jobSchedule.clear();
        jobSalary.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
