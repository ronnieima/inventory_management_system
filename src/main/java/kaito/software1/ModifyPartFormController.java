package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Controller class for the modify part form of the program.
 * FUTURE ENHANCEMENT: I can create a generic method to handle the difference between InHouse and Outsourced objects to reduce repeated code.
 */
public class ModifyPartFormController {

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

    /**
     * Gets selected part information from the main form to populate the text fields.
     * @param part Selected part from the main form.
     */
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

    /**
     * Switches label to "Machine ID" when In-House radio is selected
     */
    public void switchToInHouse() {
        changingLabel.setText("Machine ID");
    }

    /**
     * Switches label to "Company Name" when Outsourced radio is selected
     */
    public void switchToOutsourced() {
        changingLabel.setText("Company Name");
    }

    /**
     * Returns to the main view.
     * @param actionEvent Occurs when cancel is clicked.
     * @throws IOException IOException from FXMLLoader.
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        Main.returnToMain(actionEvent);
    }

    /**
     * Saves modified part information and updates it to the list of parts. It also conducts input validation.
     * @param actionEvent Occurs when save is clicked.
     * RUNTIME ERROR: I had errors when I tried to type cast an InHouse object to an Outsourced object and vice-versa when saving. I realized that was not necessary.
     *                    I fixed it by using .isSelected() to determine the type first then create a new object to replace the object that got modified.
     */
    public void saveModify(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(idText.getText());
            String name = nameText.getText();
            double price = Double.parseDouble(priceText.getText());
            int stock = (Integer.parseInt(stockText.getText()));
            int max = (Integer.parseInt(maxText.getText()));
            int min = (Integer.parseInt(minText.getText()));
            int machineId;
            String companyName;

            if (Main.checkMinMax(min, max) && Main.checkStock(stock, min, max) && Main.checkName(name)) {
                if (inhouseButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(changingText.getText());
                        InHouse modifiedPart = new InHouse(id, name, price,stock, min, max, machineId);
                        Main.updatePart(Main.getAllParts().indexOf(part), modifiedPart);
                        Main.returnToMain(actionEvent);
                    } catch (Exception e) {
                        Main.popupError(2);
                    }
                } else {
                    companyName = changingText.getText();
                    Outsourced modifiedPart = new Outsourced(id, name, price,stock, min, max, companyName);
                    Main.updatePart(Main.getAllParts().indexOf(part), modifiedPart);
                    Main.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Main.popupError(1);
        }
    }
}