package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
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

    public void addProduct(ActionEvent actionEvent) {

    }
}