package org.example.jobportal;

public class Job {
    private String company;
    private String position;
    private String salary;
    private String location;
    private String responsibilities;
    private String experience;
    private String certificates;
    private String phone;

    public Job(String company, String position, String salary, String location,
               String responsibilities, String experience, String certificates, String phone) {
        this.company = company;
        this.position = position;
        this.salary = salary;
        this.location = location;
        this.responsibilities = responsibilities;
        this.experience = experience;
        this.certificates = certificates;
        this.phone = phone;
    }

    public String getCompany() { return company; }
    public String getPosition() { return position; }
    public String getSalary() { return salary; }
    public String getLocation() { return location; }
    public String getResponsibilities() { return responsibilities; }
    public String getExperience() { return experience; }
    public String getCertificates() { return certificates; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return company + " - " + position + " (" + salary + ")";
    }

    // Метод для сохранения вакансии в строку
    public String serialize() {
        return company + ";" + position + ";" + salary + ";" + location + ";" +
                responsibilities + ";" + experience + ";" + certificates + ";" + phone;
    }

    // Метод для загрузки вакансии из строки
    public static Job deserialize(String line) {
        String[] parts = line.split(";");
        if (parts.length == 8) {
            return new Job(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
        }
        return null; // Если формат повреждён
    }
}
