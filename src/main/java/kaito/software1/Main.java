package kaito.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
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

        Product.productList.add(product1);
        Product.productList.add(product2);
        Product.productList.add(product3);
        Product.productList.add(product4);
        Product.productList.add(product5);

        Part handlebar = new InHouse(1, "Handlebar", 10, 39.99, 2322);
        Part pedal = new Outsourced(2, "Pedal", 5, 19.99, "Pedalz");
        Part tire = new Outsourced(3, "Tire", 20, 29.99, "KaitoCorp");
        Part saddle = new InHouse(4, "Saddle", 15, 49.99, 42);
        Part chain = new InHouse(5, "Chain", 8, 14.99, 1234);

        Part.partList.add(handlebar);
        Part.partList.add(pedal);
        Part.partList.add(tire);
        Part.partList.add(saddle);
        Part.partList.add(chain);

        launch();
    }
}