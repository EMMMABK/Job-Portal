package org.example.jobportal;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class JobDialog {
    public static Job show(Job job) {
        Dialog<Job> dialog = new Dialog<>();
        dialog.setTitle(job == null ? "Добавить вакансию" : "Редактировать вакансию");
        dialog.setHeaderText(null);

        ButtonType saveButtonType = new ButtonType("Сохранить", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        TextField companyField = new TextField(job != null ? job.getCompany() : "");
        TextField positionField = new TextField(job != null ? job.getPosition() : "");
        TextField salaryField = new TextField(job != null ? job.getSalary() : "");
        TextField locationField = new TextField(job != null ? job.getLocation() : "");
        TextField responsibilitiesField = new TextField(job != null ? job.getResponsibilities() : "");
        TextField experienceField = new TextField(job != null ? job.getExperience() : "");
        TextField certificatesField = new TextField(job != null ? job.getCertificates() : "");
        TextField phoneField = new TextField(job != null ? job.getPhone() : "");

        grid.add(new Label("Компания:"), 0, 0);
        grid.add(companyField, 1, 0);
        grid.add(new Label("Должность:"), 0, 1);
        grid.add(positionField, 1, 1);
        grid.add(new Label("Зарплата:"), 0, 2);
        grid.add(salaryField, 1, 2);
        grid.add(new Label("Место работы:"), 0, 3);
        grid.add(locationField, 1, 3);
        grid.add(new Label("Обязанности:"), 0, 4);
        grid.add(responsibilitiesField, 1, 4);
        grid.add(new Label("Опыт работы:"), 0, 5);
        grid.add(experienceField, 1, 5);
        grid.add(new Label("Сертификаты и дипломы:"), 0, 6);
        grid.add(certificatesField, 1, 6);
        grid.add(new Label("Телефон:"), 0, 7);
        grid.add(phoneField, 1, 7);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Job(
                        companyField.getText(), positionField.getText(), salaryField.getText(),
                        locationField.getText(), responsibilitiesField.getText(), experienceField.getText(),
                        certificatesField.getText(), phoneField.getText()
                );
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
