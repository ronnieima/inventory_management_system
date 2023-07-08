package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the modify product form of the program.
 * FUTURE ENHANCEMENT: I can make the add/removeAssociatedPart methods static in the Inventory class, so I can use it
 *                      in this class and AddProductFormController to reduce repetitive code.
 */
public class ModifyProductFormController implements Initializable {
    public TextField searchPart;
    public TableView<Part> availablePartsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partStockCol;
    public TableColumn partPriceCol;
    public TextField idText;
    public TextField stockText;
    public TextField nameText;
    public TextField priceText;
    public TextField maxText;
    public TextField minText;
    public TableView<Part> associatedTable;
    public TableColumn assoPartIdCol;
    public TableColumn assoPartNameCol;
    public TableColumn assoPartPriceCol;
    public TableColumn assoPartStockCol;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    private Product product;

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

    /**
     * Add selected associated part to the product and table.
     */
    public void addAssociatedPart() {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        associatedTable.setItems(associatedPartsList);
    }

    /**
     * Remove selected associated part from the product and table.
     */
    public void removeAssociatedPart() {
        Part selectedPart = associatedTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Disassociate Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to disassociate this part?");
        if (associatedTable.getSelectionModel().isEmpty() == false) {
            if (alert.showAndWait().get() == ButtonType.OK) {
                associatedPartsList.remove(selectedPart);
                associatedTable.setItems(associatedPartsList);
            }
        }
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
     * Saves product information and adds it to the list of products. It also conducts input validation.
     * @param actionEvent Occurs when save is clicked.
     *  LOGIC ERROR: I discovered that the product would save whenever I left the name text field empty.
     *                    I implemented a new pop-up error that is detected using name.isEmpty() and applied it to all other forms.
     */
    public void pressSaveButton(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idText.getText());
            String name = nameText.getText();
            int stock = Integer.parseInt(stockText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());

            if (name.isEmpty()) {
                Main.popupError(4);
            } else {
                if (Main.checkMinMax(min, max) && Main.checkStock(stock, min, max) && Main.checkName(name)) {
                    Product modifiedProduct = new Product(id, name, stock, price, max, min);
                    for (Part p : associatedPartsList) {
                        modifiedProduct.addAssociatedPart(p);
                    }
                    Main.updateProduct(Main.getAllProducts().indexOf(product), modifiedProduct);
                    Main.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Main.popupError(1);
        }

    }

    /**
     * Gets selected product information from the main form to populate the text fields.
     * @param product Selected product from the main view.
     */
    public void getProduct(Product product) {
        this.product = product;
        idText.setText(String.valueOf(product.getId()));
        nameText.setText(product.getName());
        stockText.setText(String.valueOf(product.getStock()));
        priceText.setText(String.valueOf(product.getPrice()));
        maxText.setText(String.valueOf(product.getMax()));
        minText.setText(String.valueOf(product.getMin()));
        associatedTable.setItems(product.getAllAssociatedParts());
    }
}