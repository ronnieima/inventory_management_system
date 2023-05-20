package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void cancel(ActionEvent actionEvent) throws IOException {
        returnToMain(actionEvent);
    }

    public void switchToInHouse(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }
    public void switchToOutsourced(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }

    // TODO: Add OUTSOURCED OPTION
    public void saveData(ActionEvent actionEvent) throws IOException {
        if (inhouseButton.isSelected()) {
            Part part = new InHouse(Main.partIdCounter, nameText.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stockText.getText()), Integer.parseInt(minText.getText()), Integer.parseInt(maxText.getText()), Integer.parseInt(changingText.getText()));
            Main.partList.add(part);
            Main.partIdCounter++;
            returnToMain(actionEvent);
        }
    }

    private void returnToMain(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
