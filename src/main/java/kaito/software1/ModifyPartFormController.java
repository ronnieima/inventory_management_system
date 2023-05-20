package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartFormController implements Initializable {

    @FXML
    public TextField priceText;
    public TextField maxText;
    public TextField minText;
    public TextField changingText;
    public TextField nameText;
    public TextField stockText;
    public ToggleGroup modifyPart;
    public RadioButton inhouseButton;
    public Label idLabel;
    public RadioButton outsourcedButton;
    public Label nameLabel;
    public TextField idText;
    public Label changingLabel;
    public Button saveButtonModify;

    private Part part;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void getPart(Part part) {
        this.part = part;
        idText.setText(String.valueOf(part.getId()));
        nameText.setText(part.getName());
        stockText.setText(String.valueOf(part.getStock()));
        priceText.setText(String.valueOf(part.getPrice()));
        maxText.setText(String.valueOf(part.getMax()));
        minText.setText(String.valueOf(part.getMin()));

        if (part instanceof InHouse) {
            inhouseButton.setSelected(true);
            changingLabel.setText("Machine ID");
            changingText.setText(String.valueOf(((InHouse)part).getMachineId()));
        } else {
            outsourcedButton.setSelected(true);
            changingLabel.setText("Company Name");
            changingText.setText(((Outsourced)part).getCompanyName());
        }
    }
    public void switchToInHouse(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }
    public void switchToOutsourced(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Inventory.returnToMain(actionEvent);
    }

    public void saveModify(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(idText.getText());
            String name = nameText.getText();
            double price = Double.parseDouble(priceText.getText());
            int stock = (Integer.parseInt(stockText.getText()));
            int max = (Integer.parseInt(maxText.getText()));
            int min = (Integer.parseInt(minText.getText()));
            int machineId;
            String companyName;

            if (Inventory.checkMinMax(min, max) && Inventory.checkStock(stock, min, max)) {
                if (inhouseButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(changingText.getText());
                        InHouse modifiedPart = new InHouse(id, name, price,stock, min, max, machineId);
                        Inventory.updatePart(Inventory.getAllParts().indexOf(part), modifiedPart);
                        Inventory.returnToMain(actionEvent);
                    } catch (Exception e) {
                        Inventory.popupError(2);
                    }
                } else {
                    companyName = changingText.getText();
                    Outsourced modifiedPart = new Outsourced(id, name, price,stock, min, max, companyName);
                    Inventory.updatePart(Inventory.getAllParts().indexOf(part), modifiedPart);
                    Inventory.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Inventory.popupError(1);
        }
    }
}
