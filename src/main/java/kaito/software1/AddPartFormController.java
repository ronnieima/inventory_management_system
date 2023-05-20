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

    // TODO: Add input validation
    public void saveData(ActionEvent actionEvent) throws IOException {
        if (inhouseButton.isSelected()) {
            Part part = new InHouse(Inventory.partIdCounter, nameText.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stockText.getText()), Integer.parseInt(minText.getText()), Integer.parseInt(maxText.getText()), Integer.parseInt(changingText.getText()));
            Inventory.getAllParts().add(part);
            Inventory.partIdCounter++;
        }
        else {
            Part part = new Outsourced(Inventory.partIdCounter, nameText.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stockText.getText()), Integer.parseInt(minText.getText()), Integer.parseInt(maxText.getText()), changingText.getText());
            Inventory.getAllParts().add(part);
            Inventory.partIdCounter++;
        }
        Inventory.returnToMain(actionEvent);
    }

}
