package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kaito.software1.Inventory.returnToMain;

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
                createError("Error modifying part", "Form contains blank values or invalid characters.");
            case 2:
                createError("Error modifying machine ID", "Machine ID must be an integer.");
            case 3:
                createError("Error modifying part", "Min can not be greater than max or be less than 0.");
                //TODO add stock error
        }
    }

    private boolean checkMinMax(int min, int max) {
        if(max <= min || min < 0) {
            popupError(3);
            return false;
        }
        return true;
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
            changingLabel.setText("Machine ID");
            inhouseButton.setSelected(true);
            changingText.setText(String.valueOf(((InHouse)part).getMachineId()));
        } else {
            changingLabel.setText("Company Name");
            outsourcedButton.setSelected(true);
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
            boolean partAdded = false;

            if (checkMinMax(min, max)) {
                if (inhouseButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(changingText.getText());
                        InHouse modifiedPart = new InHouse(id, name, price,stock, max, min, machineId);
                        Inventory.getAllParts().add(modifiedPart);
                        partAdded = true;
                    } catch (Exception e) {
                        popupError(2);
                    }

                } else {
                    companyName = changingText.getText();
                    Outsourced modifiedPart = new Outsourced(id, name, price,stock, max, min, companyName);
                    Inventory.getAllParts().add(modifiedPart);
                    partAdded = true;
                }
                if (partAdded) {
                    Inventory.getAllParts().remove(part);
                    returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            popupError(1);
        }

    }
}
