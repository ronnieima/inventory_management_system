package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    public TableView<Part> availablePartsTable;
    public TextField searchPart;
    public TextField stockText;
    public TextField nameText;
    public TextField priceText;
    public TextField maxText;
    public TextField minText;
    public TableView<Part> associatedTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partStockCol;
    public TableColumn partPriceCol;
    public TableColumn assoPartIdCol;
    public TableColumn assoPartNameCol;
    public TableColumn assoPartPriceCol;
    public TableColumn assoPartStockCol;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Product newProduct;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        availablePartsTable.setItems(Inventory.getAllParts());

        assoPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assoPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assoPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assoPartStockCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedTable.setItems(associatedPartsList);

        // Search
        FilteredList<Part> filteredParts = new FilteredList<>(Inventory.getAllParts(), p -> true);
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
        sortedParts.comparatorProperty().bind(availablePartsTable.comparatorProperty());
        availablePartsTable.setItems(sortedParts);
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Inventory.returnToMain(actionEvent);
    }

    public void addAssociatedPart(ActionEvent actionEvent) throws IOException {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        associatedTable.setItems(associatedPartsList);
    }

    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = associatedTable.getSelectionModel().getSelectedItem();
        associatedPartsList.remove(selectedPart);
    }

    public void pressSaveButton(ActionEvent actionEvent) throws IOException {
        int id = Inventory.productIdCounter;
        String name = nameText.getText();
        int stock = Integer.parseInt(stockText.getText());
        double price = Double.parseDouble(priceText.getText());
        int max = Integer.parseInt(maxText.getText());
        int min = Integer.parseInt(minText.getText());

        newProduct = new Product(id, name, stock, price, max, min);
        Inventory.addProduct(newProduct);
        Inventory.returnToMain(actionEvent);
    }
}
