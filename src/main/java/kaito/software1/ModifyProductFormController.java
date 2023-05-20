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

    public void addAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();
        product.getAllAssociatedParts().add(selectedPart);
        associatedTable.setItems(product.getAllAssociatedParts());
    }

    public void removeAssociatedPart(ActionEvent actionEvent) {
        Part selectedPart = associatedTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Disassociate Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to disassociate this part?");
        if (associatedTable.getSelectionModel().isEmpty() == false) {
            if (alert.showAndWait().get() == ButtonType.OK) {
                product.getAllAssociatedParts().remove(selectedPart);
            }
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Inventory.returnToMain(actionEvent);
    }

    public void pressSaveButton(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idText.getText());
            String name = nameText.getText();
            int stock = Integer.parseInt(stockText.getText());
            double price = Double.parseDouble(priceText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());

            if (name.isEmpty()) {
                popupError(4);
            } else {
                if (checkMinMax(min, max) && checkStock(stock, min, max)) {
                    Product modifiedProduct = new Product(id, name, stock, price, max, min);
                    for (Part p : associatedPartsList) {
                        modifiedProduct.addAssociatedPart(p);
                    }
                    Inventory.getAllProducts().add(modifiedProduct);
                    Inventory.getAllProducts().remove(product);
                    Inventory.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            popupError(1);
        }

    }
    //TODO weird bug when this gets called when inv is deleted and save
    private boolean checkMinMax(int min, int max) {
        if(max <= min || min < 0) {
            popupError(2);
            return false;
        }
        return true;
    }

    private boolean checkStock(int stock, int min, int max) {
        if (stock < min || stock > max) {
            popupError(3);
            return false;
        }
        return true;
    }

    private void createError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void popupError(int alert) {
        switch (alert) {
            case 1:
                createError("Error modifying part", "Text fields contain blank values or invalid characters.");
                break;
            case 2:
                createError("Error modifying part", "Min can not be greater than or equal to max or be less than 0.");
                break;
            case 3:
                createError("Error modifying part", "Inventory has to be between min and max.");
                break;
            case 4:
                createError("Name is empty", "Name can not be empty.");
                break;
        }
    }

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
