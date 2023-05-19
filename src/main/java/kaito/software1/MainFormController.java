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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kaito.software1.Part.partList;

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
        productsTable.setItems(Product.productList);

        partId.setCellValueFactory(new PropertyValueFactory<>("PartId"));
        partName.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        partInv.setCellValueFactory(new PropertyValueFactory<>("PartInv"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));
        partsTable.setItems(partList);

        FilteredList<Part> filteredParts = new FilteredList<>(partList, p -> true);
        searchPart.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(part.getPartId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (part.getPartName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Part> sortedParts = new SortedList<>(filteredParts);

        sortedParts.comparatorProperty().bind(partsTable.comparatorProperty());

        partsTable.setItems(sortedParts);

    }


    public void addPart(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-part-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-product-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyPart(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("modify-part-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyProduct(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("modify-product-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deletePart(ActionEvent actionEvent) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            // Creates an alert confirmation whenever user wants to delete a part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Confirmation");
            alert.setHeaderText("Deletion Confirmation");
            alert.setContentText("Are you sure you want to delete " + selectedPart.getPartName() + "?");
            if (alert.showAndWait().get() == ButtonType.OK){
                partsTable.getItems().remove(selectedPart);
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
                productsTable.getItems().remove(selectedProduct);
            }
        }
    }

    public void exit() {
        System.out.println("Successfully logged out!");
        Platform.exit();
    }

}