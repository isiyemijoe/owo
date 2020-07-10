package models;

public class Donation {

    String name;
    String project_name;
    String project_id;
    double amount;

    public Donation(String name, String project_name, String project_id, double amount) {
        this.name = name;
        this.project_name = project_name;
        this.project_id = project_id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}