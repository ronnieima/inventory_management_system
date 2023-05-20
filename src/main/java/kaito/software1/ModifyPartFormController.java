package kaito.software1;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartFormController implements Initializable {

    private Part part = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO pass the football from MAINFORMctrler TO this controller

    }

    public void getPart(Part part) {
        this.part = part;

    }


}
