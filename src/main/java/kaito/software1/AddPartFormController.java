package kaito.software1;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Controller class for the add part form of the program.
 * FUTURE ENHANCEMENT: I could add a popup box that shows if a user types into the data fields but presses exit, so they don't lose
 *                      their data.
 */
public class AddPartFormController {
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

    /**
     * Returns to main view.
     * @param actionEvent Occurs when cancel is clicked.
     * @throws IOException IOException from FXMLLoader.
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        Main.returnToMain(actionEvent);
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
     * Saves part information and adds it to the list of parts. It also conducts input validation.
     * @param actionEvent Occurs when save is clicked.
     * RUNTIME ERROR: I had an error whenever I input a string instead of an int for the machine ID.
     *                    To fix this, I added a try catch statement to catch this exception and pop up
     *                    an error box.
     */
    public void saveData(ActionEvent actionEvent) {
        try {
            int id = Main.getPartIdCounter();
            String name = nameText.getText();
            double price = Double.parseDouble(priceText.getText());
            int stock = Integer.parseInt(stockText.getText());
            int min = Integer.parseInt(minText.getText());
            int max = Integer.parseInt(maxText.getText());
            int machineId;
            String companyName;
            if (Main.checkMinMax(min, max) && Main.checkStock(stock, min, max) && Main.checkName(name)) {
                if (inhouseButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(changingText.getText());
                        Part part = new InHouse(id, name, price, stock, min, max, machineId);
                        Main.addPart(part);
                        Main.setPartIdCounter(Main.getPartIdCounter() + 1);
                        Main.returnToMain(actionEvent);
                    } catch (Exception e) {
                        Main.popupError(2);
                    }

                } else {
                    companyName = changingText.getText();
                    Part part = new Outsourced(id, name, price, stock, min, max, companyName);
                    Main.addPart(part);
                    Main.setPartIdCounter(Main.getPartIdCounter() + 1);
                    Main.returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Main.popupError(1);
        }
    }


}
