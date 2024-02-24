package com.example.assignment_1; // Update this package name as per your project structure

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Game {
    private final SimpleStringProperty title;
    private final SimpleIntegerProperty sales;
    private final SimpleStringProperty publisher;

    public Game(String title, int sales, String publisher) {
        this.title = new SimpleStringProperty(title);
        this.sales = new SimpleIntegerProperty(sales);
        this.publisher = new SimpleStringProperty(publisher);
    }

    // Getters for properties
    public String getTitle() {
        return title.get();
    }

    public int getSales() {
        return sales.get();
    }

    public String getPublisher() {
        return publisher.get();
    }

    // Properties for binding
    public SimpleStringProperty titleProperty() {
        return title;
    }

    public SimpleIntegerProperty salesProperty() {
        return sales;
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }
}
