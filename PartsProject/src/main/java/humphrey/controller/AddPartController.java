package humphrey.controller;

import humphrey.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the Add Part screen. It takes data from the user and creates a new In-House or Outsourced Part object.
 */
public class AddPartController implements Initializable {
    /**
     * Text label denoting whether the last field is set to Machine ID or Company name depending on the radio button setting
     */
    public Label changeMe;
    /**
     * Used as a flag to signal whether the part is an In-House or an Outsourced subtype
     */
    private boolean isOutsourced = false;
    /**
     * Textfield to get user supplied name for the part
     */
    @FXML
    private TextField nameField;
    /**
     * Textfield to get user supplied stock level for the part
     */
    @FXML
    private TextField stockField;
    /**
     * Textfield to get user supplied price for the part
     */
    @FXML
    private TextField priceField;
    /**
     * Textfield to get user supplied max stock for the part
     */
    @FXML
    private TextField maxField;
    /**
     * Textfield to get user supplied Machine ID or Company Name for the part, depending on the radio button selection
     */
    @FXML
    private TextField machineField;
    /**
     * Textfield to get user supplied min stock for the part
     */
    @FXML
    private TextField minField;

    /**
     * Initializes the Add Part scene
     * @param url path of the root object
     * @param resourceBundle resources of the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Sets the last text field to Machine ID and the isOutsourced flag to false
     * @param actionEvent the radio button click action event
     */
    public void inhouseRadio(ActionEvent actionEvent){
        changeMe.setText("Machine ID");
        isOutsourced = false;
    }

    /**
     * Sets the last text field to Company Name and the isOutsourced flag to true
     * @param actionEvent the radio button click action event
     */
    public void outsourcedRadio(ActionEvent actionEvent){
        changeMe.setText("Company Name");
        isOutsourced = true;
    }

    /**
     * Loads the Main screen without adding a new part
     * @param actionEvent the cancel button action event
     * @throws IOException
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        //load widget hierarchy of the Main screen
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));

        //get the stage from the Action Event of clicking the "Cancel" button on the Add Product table
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        //create a new scene
        Scene scene = new Scene(fxmlLoader.load(), 1339.0, 483.0);

        //set the title of the stage
        stage.setTitle("Main");

        //set the scene on the Stage object
        stage.setScene(scene);

        //load the scene
        stage.show();
    }

    /**
     * Creates a new Part and adds it to the allParts list
     * @param actionEvent save button click action event
     * @throws IOException
     */
    public void savePart(ActionEvent actionEvent)throws IOException {

        int stock = 0;
        double price = 0;
        int min = 0;
        int max = 0;
        int machineCode = 0;


        try{
            stock = Integer.parseInt(stockField.getText());
        }
        catch (Exception e){}

        try{
            price = Double.parseDouble(priceField.getText());
        }
        catch(Exception e){}
        try{
            min = Integer.parseInt(minField.getText());
        }
        catch(Exception e){}
        try{
            max = Integer.parseInt(maxField.getText());
        }
        catch(Exception e){}

        if(!(priceField.getText()).matches("-?\\d+(\\.\\d+)?")) {
            Alert stockAlert = new Alert(Alert.AlertType.WARNING);
            stockAlert.setTitle("WARNING");
            stockAlert.setHeaderText("WARNING");
            stockAlert.setContentText("Price must be a number");
            stockAlert.show();
        }
        else if(!(stockField.getText()).matches("[0-9]*")){
            Alert stockAlert = new Alert(Alert.AlertType.WARNING);
            stockAlert.setTitle("WARNING");
            stockAlert.setHeaderText("WARNING");
            stockAlert.setContentText("Inv must be an integer");
            stockAlert.show();
        }
        else if(!(minField.getText()).matches("[0-9]*")){
            Alert stockAlert = new Alert(Alert.AlertType.WARNING);
            stockAlert.setTitle("WARNING");
            stockAlert.setHeaderText("WARNING");
            stockAlert.setContentText("Min must be an integer");
            stockAlert.show();
        }
        else if(!(maxField.getText()).matches("[0-9]*")){
            Alert stockAlert = new Alert(Alert.AlertType.WARNING);
            stockAlert.setTitle("WARNING");
            stockAlert.setHeaderText("WARNING");
            stockAlert.setContentText("Max must be an integer");
            stockAlert.show();
        }
        else if ((max < min)){
            Alert maxMin = new Alert(Alert.AlertType.WARNING);
            maxMin.setTitle("WARNING");
            maxMin.setHeaderText("WARNING");
            maxMin.setContentText("Max cannot be less than Min. Please try again.");
            maxMin.show();
        }
        else if(!((max >= stock) && (min <= stock))){
            Alert stockAlert = new Alert(Alert.AlertType.WARNING);
            stockAlert.setTitle("WARNING");
            stockAlert.setHeaderText("WARNING");
            stockAlert.setContentText("Inv must be less than or equal to Max and greater than or equal to Min. Please try again.");
            stockAlert.show();
        }
        else {
            if (!isOutsourced) {
                try {
                    machineCode = Integer.parseInt(machineField.getText());
                } catch (Exception e) {
                }
                if(!(machineField.getText()).matches("[0-9]*")) {
                    Alert stockAlert = new Alert(Alert.AlertType.WARNING);
                    stockAlert.setTitle("WARNING");
                    stockAlert.setHeaderText("WARNING");
                    stockAlert.setContentText("Machine ID must be an integer");
                    stockAlert.show();
                }
                else {
                    InHouse inhousePart = new InHouse(Main.partId, nameField.getText(), price, stock, min, max, machineCode);
                    Inventory.addPart(inhousePart);
                    Main.partIdIncrease();

                    //load widget hierarchy of the Main screen
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));

                    //get the stage from the Action Event of clicking the "Add" button on the Parts table
                    Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

                    //create a new scene the same size as the "Add Part" screen
                    Scene scene = new Scene(fxmlLoader.load(), 1339.0, 483.0);

                    //set the title of the stage
                    stage.setTitle("Main");

                    //set the scene on the Stage object
                    stage.setScene(scene);

                    //load the scene
                    stage.show();
                }
            }
            else {
                Outsourced outsourcedPart = new Outsourced(Main.partId, (nameField.getText()), price, stock, min, max, (machineField.getText()));
                Inventory.addPart(outsourcedPart);
                Main.partIdIncrease();

                //load widget hierarchy of the Main screen
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));

                //get the stage from the Action Event of clicking the "Add" button on the Parts table
                Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

                //create a new scene the same size as the "Add Part" screen
                Scene scene = new Scene(fxmlLoader.load(), 1339.0, 483.0);

                //set the title of the stage
                stage.setTitle("Main");

                //set the scene on the Stage object
                stage.setScene(scene);

                //load the scene
                stage.show();
            }
        }
    }
}