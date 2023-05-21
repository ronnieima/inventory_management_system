package kaito.software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *  Contains all the static methods used throughout the program.
 *  FUTURE ENHANCEMENT: I could consolidate all the input validation functions into one static method to implement to all the controllers that require input validation.
 */
public class Inventory extends Application{
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // Global counter for generating the IDs.
    private static int partIdCounter = 6; // This counter starts at 6 because I am not allowed to edit Part.java constructor to increment this variable.
    private static int productIdCounter = 1;

    /**
     * Loads the main form on startup. It creates the window for the main program.
     * @param stage The stage in which the scene is put on.
     * @throws IOException IOException from FXMLLoader.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inventory.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1079, 476);
        stage.setTitle("Inventory Management System | Ronnie Kaito Imagawa");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates sample data and launches the program. This method is called from the start.
     * @param args This parameter is included in every main method.
     */
    public static void main(String[] args) {
        Product product1 = new Product(1, "Mountain Bike", 3, 499.99, 3, 2);
        Product product2 = new Product(2, "Road Bike", 5, 799.99, 6, 2);
        Product product3 = new Product(3, "Hybrid Bike", 20, 349.99, 100, 4);
        Product product4 = new Product(4, "Electric Bike", 2, 1299.99, 3, 1);
        Product product5 = new Product(5, "Kids Bike", 1, 199.99, 4, 1);

        Inventory.getAllProducts().add(product1);
        Inventory.getAllProducts().add(product2);
        Inventory.getAllProducts().add(product3);
        Inventory.getAllProducts().add(product4);
        Inventory.getAllProducts().add(product5);

        Part handlebar = new InHouse(1, "Handlebar", 30.99, 3, 3,5,112);
        Part pedal = new Outsourced(2, "Pedal", 19.99, 2, 1, 6, "Kaitoz");
        Part tire = new Outsourced(3, "Tire", 15.99, 4, 1,4,"TIRES!!");
        Part saddle = new InHouse(4, "Saddle", 49.99, 6, 4,8,234);
        Part chain = new InHouse(5, "Chain",  14.99, 30, 20,30,435);

        Inventory.getAllParts().add(handlebar);
        Inventory.getAllParts().add(pedal);
        Inventory.getAllParts().add(tire);
        Inventory.getAllParts().add(saddle);
        Inventory.getAllParts().add(chain);
        launch();
    }

    /**
     * Adds a new part object to allParts. It uses the getter for the allParts ObservableList to add a new part.
     * @param newPart The new part to be added.
     */
    public static void addPart(Part newPart) {
        getAllParts().add(newPart);
    }

    /**
     * Adds a new product object to allProducts. It uses the getter for the allProducts ObservableList to add a new product.
     * @param newProduct The new product to be added.
     */
    public static void addProduct(Product newProduct) {
        getAllProducts().add(newProduct);
    }

    /**
     * Looks up a part within allParts given its ID. It uses a for loop to iterate through each part until it finds a match. Otherwise, it returns null.
     * @param partId The part ID to look up.
     * @return p The searched part.
     */
    public static Part lookupPart(int partId) {
        for (Part p : getAllParts()) {
            if (p.getId() == partId) {
                return p;
            }
        }
        return null;
    }

    /**
     * Looks up a part within allProd given its ID. It uses a for loop to iterate through each product until it finds a match. Otherwise, it returns null.
     * @param productId The product ID to look up.
     * @return The searched product.
     */
    public static Product lookupProduct(int productId) {
        for (Product p : getAllProducts()) {
            if (p.getId() == productId) {
                return p;
            }
        }
        return null;
    }

    /**
     * Searches a list of parts by its name. It uses a for loop to iterate through each part until it finds a match and adds it to the list.
     * @param partName The part name.
     * @return The list of parts that are found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();

        for (Part p : getAllParts()) {
            if (p.getName().equals(partName)) {
                parts.add(p);
            }
        }
        return parts;
    }

    /**
     * Searches a list of products by its name. It uses a for loop to iterate through each product until it finds a match and adds it to the list.
     * @param productName The product name.
     * @return The list of product that are found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        for (Product p : getAllProducts()) {
            if (p.getName().equals(productName)) {
                products.add(p);
            }
        }
        return products;
    }

    /**
     * Replaces a part in a given index within the list of parts with a given part.
     * @param index The index of the part to be replaced.
     * @param selectedPart The new part to add.
     */
    public static void updatePart(int index, Part selectedPart) {
        getAllParts().set(index, selectedPart);
    }

    /**
     * Replaces a product in a given index within the list of products with a given product.
     * @param index The index of the product to be replaced.
     * @param selectedProduct The new product to add.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        getAllProducts().set(index, selectedProduct);
    }

    /**
     * Deletes a part from the parts list.
     * @param selectedPart Part to delete.
     * @return True if the part has been successfully removed. False if removal has failed.
     */
    public static boolean deletePart(Part selectedPart) {
        if (getAllParts().contains(selectedPart)) {
            getAllParts().remove(selectedPart);
            return true;
        }
        return false;
    }

    /**
     * Deletes a product from the products list.
     * @param selectedProduct Product to delete.
     * @return True if the product has been successfully removed. False if removal has failed.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (getAllProducts().contains(selectedProduct)) {
            getAllProducts().remove(selectedProduct);
            return true;
        }
        return false;
    }

    /**
     * Getter method for the parts list.
     * @return The list of all the parts.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Getter method for the products list.
     * @return The list of all the products.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Getter method for the part ID counter.
     * @return The current counter to be used when generating a new part ID.
     */
    public static int getPartIdCounter() {
        return partIdCounter;
    }

    /**
     * Setter method for the part ID counter.
     * @param partIdCounter The new part ID.
     */
    public static void setPartIdCounter(int partIdCounter) {
        Inventory.partIdCounter = partIdCounter;
    }

    /**
     * Getter method for the product ID counter.
     * @return The current counter to be used when generating a new product ID.
     */
    public static int getProductIdCounter() {
        return productIdCounter;
    }

    /**
     * Setter method for the product ID counter.
     * @param productIdCounter The new product ID.
     */
    public static void setProductIdCounter(int productIdCounter) {
        Inventory.productIdCounter = productIdCounter;
    }

    /**
     * Returns to the main screen view.
     * @param actionEvent Occurs when a button is clicked.
     * @throws IOException IOException from FXMLLoader.
     */
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Inventory.class.getResource("main-form.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Helper method that creates an error window.
     * @param content Error message.
     */
    private static void createError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Displays an error message based on which error has occurred.
     * @param alert The case label for the switch statement.
     * LOGICAL ERROR: I added break statements to each case label to avoid multiple popups occurring at once.
     */
    public static void popupError(int alert) {
        switch (alert) {
            case 1:
                createError("Form contains blank values or invalid characters.");
                break;
            case 2:
                createError("Machine ID must be an integer.");
                break;
            case 3:
                createError("Min can not be greater than max or be less than 0.");
                break;
            case 4:
                createError("Inventory has to be between min and max.");
                break;
            case 5:
                createError("Name field is empty.");
                break;
        }
    }

    /**
     * Checks that the minimum value does not exceed the maximum value or is 0.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return True if values pass the check. False if values fail.
     */
    public static boolean checkMinMax(int min, int max) {
        if(max <= min || min < 0) {
            popupError(3);
            return false;
        }
        return true;
    }

    /**
     * Checks that the stock is between the minimum and maximum values.
     * @param stock Stock value.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return True if stock passes the check. False if stock is not between min and max.
     */
    public static boolean checkStock(int stock, int min, int max) {
        if (stock < min || stock > max) {
            popupError(4);
            return false;
        }
        return true;
    }

    /**
     * Checks that the name field is not empty.
     * @param name Name value.
     * @return True if name text field is not empty. False if text field is empty.
     */
    public static boolean checkName(String name) {
        if (name.isEmpty()) {
            popupError(5);
            return false;
        }
        return true;
    }
}