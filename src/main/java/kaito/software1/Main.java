package kaito.software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    public static ObservableList<Part> partList = FXCollections.observableArrayList();
    public static ObservableList<Product> productList = FXCollections.observableArrayList();
    public static int partIdCounter = 1;
    public static int productIdCounter = 1;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1079, 476);
        stage.setTitle("Inventory Management System | Ronnie Kaito Imagawa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Product product1 = new Product(1, "Mountain Bike", 10, 499.99);
        Product product2 = new Product(2, "Road Bike", 5, 799.99);
        Product product3 = new Product(3, "Hybrid Bike", 20, 349.99);
        Product product4 = new Product(4, "Electric Bike", 15, 1299.99);
        Product product5 = new Product(5, "Kids Bike", 8, 199.99);

        Main.productList.add(product1);
        Main.productList.add(product2);
        Main.productList.add(product3);
        Main.productList.add(product4);
        Main.productList.add(product5);

        Part handlebar = new InHouse(1, "Handlebar", 30.99, 10, 3,5,112);
        Part pedal = new Outsourced(2, "Pedal", 19.99, 16, 1, 6, "Kaitoz");
        Part tire = new Outsourced(3, "Tire", 15.99, 2, 1,4,"TIRES!!");
        Part saddle = new InHouse(4, "Saddle", 49.99, 20, 4,8,234);
        Part chain = new InHouse(5, "Chain",  14.99, 50, 20,30,435);

        Main.partList.add(handlebar);
        Main.partList.add(pedal);
        Main.partList.add(tire);
        Main.partList.add(saddle);
        Main.partList.add(chain);



        launch();
    }
}