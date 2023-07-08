package kaito.software1;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the main view of the program.
 * FUTURE ENHANCEMENT: I can improve the code in modifyProduct/Part by writing it into one helper method to reduce the amount of repeated code.
 */
public class MainFormController implements Initializable {
    @FXML
    private TextField searchPart;
    @FXML
    private TextField searchProduct;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public TableView<Part> partsTable;
    public TableView<Product> productsTable;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInv;
    public TableColumn partPrice;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productInv;
    public TableColumn productPrice;

    /**
     * Initializes the TableViews and search methods
     * @param url Always included with class.
     * @param resourceBundle Always included with class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(Main.getAllProducts());

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Main.getAllParts());

        FilteredList<Part> filteredParts = new FilteredList<>(Main.getAllParts(), p -> true);
        searchPart.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Searches the's part ID by turning it into a string and comparing it with the existing parts in the
                // list
                if (Integer.toString(part.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Part> sortedParts = new SortedList<>(filteredParts);
        sortedParts.comparatorProperty().bind(partsTable.comparatorProperty());
        partsTable.setItems(sortedParts);

        FilteredList<Product> filteredProducts = new FilteredList<>(Main.getAllProducts(), p -> true);
        searchProduct.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredProducts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Searches the's part ID by turning it into a string and comparing it with the existing parts in the
                // list
                if (Integer.toString(part.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (part.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Product> sortedProducts = new SortedList<>(filteredProducts);
        sortedProducts.comparatorProperty().bind(productsTable.comparatorProperty());
        productsTable.setItems(sortedProducts);
    }

    /**
     * Creates an error when user has not selected anything.
     */
    private void popupNoSelectionError() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Nothing Selected");
        error.setHeaderText(null);
        error.setContentText("You have not selected anything.");
        error.showAndWait();
    }

    /**
     *  Helper method which takes an FXML file to switch scenes to.
     * @param fxmlFile Name of file to switch to.
     * @param actionEvent Occurs when button is clicked.
     * @throws IOException IOException from FXMLLoader.
     */
    private void switchScene(String fxmlFile, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches scene to the add part form.
     * @param actionEvent Occurs when add button is clicked under part table.
     * @throws IOException IOException from FXMLLoader.
     */
    public void addPart(ActionEvent actionEvent) throws IOException {
        switchScene("add-part-form.fxml", actionEvent);
    }

    /**
     * Switches scene to the add product form.
     * @param actionEvent Occurs when add button is clicked under product table.
     * @throws IOException IOException from FXMLLoader.
     */
    public void addProduct(ActionEvent actionEvent) throws IOException {
        switchScene("add-product-form.fxml", actionEvent);
    }

    /**
     * Switches scene to the modify part form.
     * @param actionEvent Occurs when modify button is clicked under part table.
     * @throws IOException IOException from FXMLLoader.
     */
    public void modifyPart(ActionEvent actionEvent) throws IOException {
        try {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-part-form.fxml"));
            root = loader.load();
            ModifyPartFormController controller = loader.getController();
            controller.getPart(selectedPart);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e) {
            popupNoSelectionError();
        }
    }

    /**
     * Switches scene to the modify product form.
     * @param actionEvent Occurs when modify button is clicked under product table.
     */
    public void modifyProduct(ActionEvent actionEvent) {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-product-form.fxml"));
            root = loader.load();
            ModifyProductFormController controller = loader.getController();
            controller.getProduct(selectedProduct);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e) {
            popupNoSelectionError();
        }
    }

    /**
     * Deletes a selected part in the part table.
     */
    public void deletePart() {
        try {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

            // Creates an alert confirmation whenever user wants to delete a part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selectedPart.getName() + "?");
            if (alert.showAndWait().get() == ButtonType.OK){
                Main.deletePart(selectedPart);
            }
        }
        catch (NullPointerException e) {
            popupNoSelectionError();
        }
    }

    /**
     * Deletes a selected product in the product table.
     */
    public void deleteProduct() {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            // Creates an alert confirmation whenever user wants to delete a product
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selectedProduct.getName() + "?");
            if (alert.showAndWait().get() == ButtonType.OK){
                Main.deleteProduct(selectedProduct);
            }
        }
        catch (NullPointerException e) {
            popupNoSelectionError();
        }
    }

    /**
     * Prompts a confirmation window and exits the program if user clicks OK.]
     * LOGICAL ERROR: The confirmation popped up twice everytime I clicked exit. This was because I had an extra "alert.showAndWait()" before the if statement. I deleted it, and it now pops up only once.
     */
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}