package kaito.software1;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productId.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        productInv.setCellValueFactory(new PropertyValueFactory<>("ProductInv"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));
        productsTable.setItems(Main.productList);

        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Main.partList);

        FilteredList<Part> filteredParts = new FilteredList<>(Main.partList, p -> true);
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

        FilteredList<Product> filteredProducts = new FilteredList<>(Main.productList, p -> true);
        searchProduct.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredProducts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Searches the's part ID by turning it into a string and comparing it with the existing parts in the
                // list
                if (Integer.toString(part.getProductId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (part.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Product> sortedProducts = new SortedList<>(filteredProducts);
        sortedProducts.comparatorProperty().bind(productsTable.comparatorProperty());
        productsTable.setItems(sortedProducts);

    }

    private void switchScene(String fxmlFile, ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addPart(ActionEvent actionEvent) throws IOException {
        switchScene("add-part-form.fxml", actionEvent);
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        switchScene("add-product-form.fxml", actionEvent);
    }

    public void modifyPart(ActionEvent actionEvent) throws IOException {
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

    public void modifyProduct(ActionEvent actionEvent) throws IOException {
        switchScene("modify-product-form.fxml", actionEvent);
    }

    public void deletePart(ActionEvent actionEvent) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            // Creates an alert confirmation whenever user wants to delete a part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Confirmation");
            alert.setHeaderText("Deletion Confirmation");
            alert.setContentText("Are you sure you want to delete " + selectedPart.getName() + "?");
            if (alert.showAndWait().get() == ButtonType.OK){
                Main.partList.remove(selectedPart);
            }
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            // Creates an alert confirmation whenever user wants to delete a product
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Confirmation");
            alert.setHeaderText("Deletion Confirmation");
            alert.setContentText("Are you sure you want to delete " + selectedProduct.getProductName() + "?");
            if (alert.showAndWait().get() == ButtonType.OK){
                Main.productList.remove(selectedProduct);
            }
        }
    }
    //test
    public void exit() {
        System.out.println("Successfully logged out!");
        Platform.exit();
    }

}