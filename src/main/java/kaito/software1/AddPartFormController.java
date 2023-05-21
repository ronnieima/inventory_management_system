package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPartFormController implements Initializable {
    public RadioButton inhouseButton;
    public RadioButton outsourcedButton;
    public TextField idText;
    public TextField priceText;
    public TextField maxText;
    public TextField minText;
    public TextField changingText;
    public ToggleGroup addPart;
    public Label idLabel;
    public Label maxLabel;
    public Label minLabel;
    public Label priceLabel;
    public Label changingLabel;
    public Button saveButton;
    public Label nameLabel;
    public TextField nameText;
    public TextField stockText;
    public Label stockLabel;
    public ToggleGroup modifyPart;
    public Button saveButtonModify;
    public TextField idTextMod;
    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void cancel(ActionEvent actionEvent) throws IOException {
        Inventory.returnToMain(actionEvent);
    }

    public void switchToInHouse(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }
    public void switchToOutsourced(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }


    public void saveData(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getPartIdCounter();
            String name = nameText.getText();
            double price = Double.parseDouble(priceText.getText());
            int stock = Integer.parseInt(stockText.getText());
            int min = Integer.parseInt(minText.getText());
            int max = Integer.parseInt(maxText.getText());
            int machineId;
            String companyName;
            if (Inventory.checkMinMax(min, max) && Inventory.checkStock(stock, min, max) && Inventory.checkName(name)) {
                if (inhouseButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(changingText.getText());
                        Part part = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(part);
                        Inventory.setPartIdCounter(Inventory.getPartIdCounter() + 1);
                        Inventory.returnToMain(actionEvent);
                    } catch (Exception e) {
                        Inventory.popupError(2);
                    }

                } else {
                    companyName = changingText.getText();
                    Part part = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.addPart(part);
                    Inventory.setPartIdCounter(Inventory.getPartIdCounter() + 1);
                    Inventory.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Inventory.popupError(1);
        }
    }


}
