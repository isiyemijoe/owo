package models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Project  implements Serializable {
    String Id;
    String description;
    String owner;
    String title;
    String Goal;
    String Category;
    String startDate;
    String endDate;
    Bitmap featureImage;
    String totalAmount;
    String balance;
    String currentBal;

    public Project() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {

        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Project(String id, String description, String goal, String category, String startDate,
                   String endDate,  String totalAmount,
                   String balance, String currentBal, String title, String owner) {

       this.owner  = owner;
        this.title = title;
        Id = id;
        this.description = description;
        Goal = goal;
        Category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.featureImage = featureImage;
        this.totalAmount = totalAmount;
        this.balance = balance;
        this.currentBal = currentBal;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Bitmap getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(Bitmap featureImage) {
        this.featureImage = featureImage;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(String currentBal) {
        this.currentBal = currentBal;
    }
}
