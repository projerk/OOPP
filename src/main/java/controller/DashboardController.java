package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import app.Projerk;

public class DashboardController {

    private Projerk app = Projerk.getInstance();

    @FXML
    public void initialize() {
    }

    /**
     * Draw chart on the dashboard
     * 
     * TO DO: add api to fetch the process of user, and draw them.
     */
    // private void drawChart() {
    //     NumberAxis xAxis = new NumberAxis();
    //     NumberAxis yAxis = new NumberAxis();
    //     xAxis.setLabel("Timeline");
    //     yAxis.setLabel("Number of Word");

    //     LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    //     lineChart.setTitle("Word Learned");

    //     XYChart.Series<Number, Number> series = new XYChart.Series<>();
    //     series.setName("Data Series");

    //     series.getData().add(new XYChart.Data<>(1, 23));
    //     series.getData().add(new XYChart.Data<>(2, 14));
    //     series.getData().add(new XYChart.Data<>(3, 15));
    //     series.getData().add(new XYChart.Data<>(4, 24));
    //     series.getData().add(new XYChart.Data<>(5, 34));

    //     lineChart.getData().add(series);

    //     chartContainer.getChildren().add(lineChart);
    // }
}
