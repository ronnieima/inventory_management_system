package kaito.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    public TextField invText;
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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInHouse(ActionEvent actionEvent) {
        changingLabel.setText("Machine ID");
    }

    public void switchToOutsourced(ActionEvent actionEvent) {
        changingLabel.setText("Company Name");
    }

    public void saveData(ActionEvent actionEvent) {
        if (inhouseButton.isSelected()) {
            Part part = new InHouse(6, nameText.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stockText.getText()), Integer.parseInt(minText.getText()), Integer.parseInt(maxText.getText()), Integer.parseInt(changingText.getText()));
            Main.partList.add(part);
            clearFields();
            popupAlert();
        }
    }

    private void clearFields() {
        nameText.clear();
        priceText.clear();
        stockText.clear();
        minText.clear();
        maxText.clear();
        changingText.clear();
    }

    private void popupAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Part Added");
        alert.setHeaderText(null);
        alert.setContentText("Your part has been successfully added!");

        alert.showAndWait();
    }
}
