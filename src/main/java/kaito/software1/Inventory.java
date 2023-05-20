package kaito.software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Inventory extends Application{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static int partIdCounter = 1;
    public static int productIdCounter = 1;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inventory.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1079, 476);
        stage.setTitle("Inventory Management System | Ronnie Kaito Imagawa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Product product1 = new Product(1, "Mountain Bike", 10, 499.99, 3, 2);
        Product product2 = new Product(2, "Road Bike", 5, 799.99, 6, 2);
        Product product3 = new Product(3, "Hybrid Bike", 20, 349.99, 100, 4);
        Product product4 = new Product(4, "Electric Bike", 15, 1299.99, 3, 1);
        Product product5 = new Product(5, "Kids Bike", 8, 199.99, 4, 1);

        Inventory.getAllProducts().add(product1);
        Inventory.getAllProducts().add(product2);
        Inventory.getAllProducts().add(product3);
        Inventory.getAllProducts().add(product4);
        Inventory.getAllProducts().add(product5);

        Part handlebar = new InHouse(1, "Handlebar", 30.99, 10, 3,5,112);
        Part pedal = new Outsourced(2, "Pedal", 19.99, 16, 1, 6, "Kaitoz");
        Part tire = new Outsourced(3, "Tire", 15.99, 2, 1,4,"TIRES!!");
        Part saddle = new InHouse(4, "Saddle", 49.99, 20, 4,8,234);
        Part chain = new InHouse(5, "Chain",  14.99, 50, 20,30,435);

        Inventory.getAllParts().add(handlebar);
        Inventory.getAllParts().add(pedal);
        Inventory.getAllParts().add(tire);
        Inventory.getAllParts().add(saddle);
        Inventory.getAllParts().add(chain);
        launch();
    }

    public static void addPart(Part newPart) {
        getAllParts().add(newPart);
    }

    public static void addProduct(Product newProduct) {
        getAllProducts().add(newProduct);
    }
    public static Part lookupPart(int partId) {
        for (Part p : getAllParts()) {
            if (p.getId() == partId) {
                return p;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId) {
        for (Product p : getAllProducts()) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }
    //searches the lsit of parts by name
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();

        for (Part p : getAllParts()) {
            if (p.getName().equals(partName)) {
                parts.add(p);
            }
        }
        return parts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Product p : getAllProducts()) {
            if (p.getName().equals(productName)) {
                products.add(p);
            }
        }
        return products;
    }

    public static void updatePart(int index, Part selectedPart) {
        getAllParts().set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct) {
        getAllProducts().set(index, selectedProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        if (getAllParts().contains(selectedPart)) {
            getAllParts().remove(selectedPart);
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (getAllProducts().contains(selectedProduct)) {
            getAllProducts().remove(selectedProduct);
            return true;
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Inventory.class.getResource("main-form.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}