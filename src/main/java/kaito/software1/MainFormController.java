package kaito.software1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
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
        partsTable.setItems(Part.partList);
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-product-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void deletePart(ActionEvent actionEvent) throws IOException {
    }

    public void addPart(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-part-form.fxml"));
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

    public void exit() {
        Platform.exit();
    }
}