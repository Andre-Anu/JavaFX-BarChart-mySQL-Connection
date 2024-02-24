package com.example.assignment_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class Controller {
    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Game> tableView;
    @FXML
    private TableColumn<Game, String> titleColumn;
    @FXML
    private TableColumn<Game, Integer> salesColumn;
    @FXML
    private TableColumn<Game, String> publisherColumn;
    Connector connector = new Connector();
    static int i;

    @FXML
    public void initialize() {
        if (i == 0) {
            try (Connection connection = connector.connect();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT Publisher, SUM(Sales) AS TotalSales FROM `table` GROUP BY Publisher ORDER BY TotalSales DESC")) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();

                while (resultSet.next()) {
                    String publisher = resultSet.getString("Publisher");
                    int totalSales = resultSet.getInt("TotalSales");
                    series.getData().add(new XYChart.Data<>(publisher, totalSales));
                }

                try {
                    barchart.getData().add(series);

                    CategoryAxis xAxis = (CategoryAxis) barchart.getXAxis();
                    xAxis.setLabel("Publisher");

                    NumberAxis yAxis = (NumberAxis) barchart.getYAxis();
                    yAxis.setLabel("TotalSales");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("first and " + i);
        }
        if (i==1){
            titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
            salesColumn.setCellValueFactory(cellData -> cellData.getValue().salesProperty().asObject());
            publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());

            try (Connection connection = connector.connect();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM `table`")) {
                ObservableList<Game> games = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    int sales = resultSet.getInt("Sales");
                    String publisher = resultSet.getString("Publisher");
                    games.add(new Game(title, sales, publisher));
                }
                tableView.setItems(games);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("second and " + i);
        }
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        i=0;
        System.out.println("switchToScene1 " + i);
        root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToScene2(ActionEvent event) throws IOException {
        i=1;
        System.out.println("switchToScene2 " + i);
        root = FXMLLoader.load(getClass().getResource("tableUI.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}