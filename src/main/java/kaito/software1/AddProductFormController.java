package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kaito.software1.Main;
import kaito.software1.Part;
import kaito.software1.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the add product form of the program.
 * FUTURE ENHANCEMENT: I can check if each individual field is empty when the user clicks save. Each field would have their own respective error message.
 */
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
    private Product newProduct;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * Initializes the TableViews and search methods
     * @param url Always included with class.
     * @param resourceBundle Always included with class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        availablePartsTable.setItems(Main.getAllParts());

        assoPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assoPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assoPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assoPartStockCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedTable.setItems(associatedPartsList);

        // Search
        FilteredList<Part> filteredParts = new FilteredList<>(Main.getAllParts(), p -> true);
        searchPart.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // Searches the's part ID by turning it into a string and comparing it with the existing parts in the list
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

    /**
     * Returns to main view.
     * @param actionEvent Occurs when cancel is clicked.
     * @throws IOException IOException from FXMLLoader.
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        Main.returnToMain(actionEvent);
    }

    /**
     * Add selected associated part to the product and table.
     * @throws IOException IOException from FXMLLoader.
     */
    public void addAssociatedPart() throws IOException {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        associatedTable.setItems(associatedPartsList);
    }

    /**
     * Remove selected associated part from the product and table.
     * LOGIC ERROR: After deleting the item, it did not remove itself from the table. I added associatedTable.setItems(associatedPartsList); to update the table after deletion.
     */
    public void removeAssociatedPart() {
        Part selectedPart = associatedTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Disassociate Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to disassociate this part?");
        if (!associatedTable.getSelectionModel().isEmpty()) {
            if (alert.showAndWait().get() == ButtonType.OK) {
                associatedPartsList.remove(selectedPart);
                associatedTable.setItems(associatedPartsList);
            }
        }
    }

    /**
     * Saves product information and adds it to the list of products. It also conducts input validation.
     * @param actionEvent Occurs when save is clicked.
     */
    public void pressSaveButton(ActionEvent actionEvent) {
        try{
            int id = Main.getProductIdCounter();
            String name = nameText.getText();
            int stock = Integer.parseInt(stockText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());

            if (Main.checkStock(stock, min, max) && Main.checkMinMax(min, max) && Main.checkName(name)) {
                newProduct = new Product(id, name, stock, price, max, min);
                for (Part p : associatedPartsList) {
                    newProduct.addAssociatedPart(p);
                }
                Main.addProduct(newProduct);
                Main.returnToMain(actionEvent);
            }
        } catch (Exception e) {
            Main.popupError(1);
        }
    }
}