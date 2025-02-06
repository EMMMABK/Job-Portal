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

    public Job(String company, String position, String salary, String location, String responsibilities, String experience, String certificates, String phone) {
        this.company = company;
        this.position = position;
        this.salary = salary;
        this.location = location;
        this.responsibilities = responsibilities;
        this.experience = experience;
        this.certificates = certificates;
        this.phone = phone;
    }

    public String serialize() {
        return company + "," + position + "," + salary + "," + location + "," + responsibilities + "," + experience + "," + certificates + "," + phone;
    }

    public static Job deserialize(String line) {
        String[] parts = line.split(",");
        return new Job(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    }

    @Override
    public String toString() {
        return company + " - " + position + " (" + salary + ")";
    }
}

