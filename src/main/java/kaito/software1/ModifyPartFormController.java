package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kaito.software1.AddPartFormController.returnToMain;

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
        if (part.getClass().getSimpleName().equals("InHouse")) {
            inhouseButton.setSelected(true);
            InHouse inHouse = (InHouse) part;
            idText.setText(String.valueOf(inHouse.getId()));
            nameText.setText(inHouse.getName());
            stockText.setText(String.valueOf(inHouse.getStock()));
            priceText.setText(String.valueOf(inHouse.getPrice()));
            maxText.setText(String.valueOf(inHouse.getMax()));
            minText.setText(String.valueOf(inHouse.getMin()));
            changingText.setText(String.valueOf((inHouse.getMachineId())));
        } else {
            outsourcedButton.setSelected(true);
            Outsourced outsourced = (Outsourced) part;
            idText.setText(String.valueOf(outsourced.getId()));
            nameText.setText(outsourced.getName());
            stockText.setText(String.valueOf(outsourced.getStock()));
            priceText.setText(String.valueOf(outsourced.getPrice()));
            maxText.setText(String.valueOf(outsourced.getMax()));
            minText.setText(String.valueOf(outsourced.getMin()));
            changingText.setText(String.valueOf((outsourced.getCompanyName())));
        }

    }


    public void switchToInHouse(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }
    public void switchToOutsourced(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        AddPartFormController.returnToMain(actionEvent);
    }

    public void saveModify(ActionEvent actionEvent) throws IOException {
        if (inhouseButton.isSelected()) {
            InHouse modifiedPart = (InHouse) part;
            modifiedPart.setId(Integer.parseInt(idText.getText()));
            modifiedPart.setName(nameText.getText());
            modifiedPart.setPrice(Double.parseDouble(priceText.getText()));
            modifiedPart.setMax(Integer.parseInt(maxText.getText()));
            modifiedPart.setMin(Integer.parseInt(minText.getText()));
            modifiedPart.setMachineId(Integer.parseInt(changingText.getText()));
        }

        returnToMain(actionEvent);
    }
}
