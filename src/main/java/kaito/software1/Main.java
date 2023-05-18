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
        Product pr1 = new Product(1, "Deez", 3, 12.23);
        Product pr2 = new Product(2, "Nuts", 5, 23.23);
        Product pr3 = new Product(3, "Ball", 7, 1.32);
        Product pr4 = new Product(4, "Ayo", 9, 123.30);
        Product pr5 = new Product(5, "LMAO", 0, 12.23);

        Product.productList.add(pr1);
        Product.productList.add(pr2);
        Product.productList.add(pr3);
        Product.productList.add(pr4);
        Product.productList.add(pr5);

        Part part1 = new InHouse(1, "Wheel", 3, 10.00,111);

        launch();
    }
}