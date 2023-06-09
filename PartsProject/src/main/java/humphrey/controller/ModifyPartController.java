package humphrey.controller;


import humphrey.model.*;
import javafx.collections.ObservableList;
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
 * This class controls the Modify Part screen. It loads attributes from a part object selected on the main screen and allows the user to make changes.
 */
public class ModifyPartController implements Initializable {
    /**
     * Text label denoting whether the last field is set to Machine ID or Company name depending on the radio button setting
     */
    public Label changeMe;
    /**
     * Used to receive a part object passed from the main screen controller
     */
    static private Part passedPart;
    /**
     * Used as a flag to signal whether the part is an In-House or an Outsourced subtype
     */
    private boolean isOutsourced = false;
    /**
     * Textfield used to display existing id for a part. Cannot be edited by user.
     */
    @FXML
    private TextField idField;
    /**
     * Textfield used to display existing part name. Can be edited by user.
     */
    @FXML
    private TextField nameField;
    /**
     * Textfield used to display existing part stock level. Can be edited by user.
     */
    @FXML
    private TextField stockField;
    /**
     * Textfield used to display existing part price. Can be edited by user.
     */
    @FXML
    private TextField priceField;
    /**
     * Textfield used to display existing part max stock. Can be edited by user.
     */
    @FXML
    private TextField maxField;
    /**
     * Textfield used to display existing part machine code. Can be edited by user.
     */
    @FXML
    private TextField machineField;
    /**
     * Textfield used to display existing part min stock. Can be edited by user.
     */
    @FXML
    private TextField minField;
    /**
     * Shows that the selected part is In House
     */
    @FXML
    private RadioButton radioInHouse;
    /**
     * Shows that the selected part is Outsourced
     */
    @FXML
    private RadioButton radioOutsourced;

    /**
     *Initializes the Modify Part scene and populates the text fields with the selected Part attributes
     * @param url path of the root object
     * @param resourceBundle resources of the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idField.setText(String.valueOf(passedPart.getId()));
        nameField.setText(passedPart.getName());
        stockField.setText(String.valueOf(passedPart.getStock()));
        priceField.setText(String.valueOf(passedPart.getPrice()));
        maxField.setText(String.valueOf(passedPart.getMax()));
        minField.setText(String.valueOf(passedPart.getMin()));

        if(passedPart instanceof InHouse){
            machineField.setText(String.valueOf((((InHouse) passedPart).getMachineId())));
            isOutsourced = false;
            radioInHouse.fire();
        }
        else{
            machineField.setText(((Outsourced)passedPart).getCompanyName());
            isOutsourced = true;
            radioOutsourced.fire();
        }
    }

    /**
     * Sets the last text field to Machine ID and changes the isOutsourced flag to false
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
     * Returns to the main screen without saving changes.
     * @param actionEvent the Cancel Button click
     * @throws IOException
     */
    public void cancel(ActionEvent actionEvent) throws IOException {

        //load wget hierarchy of the Main screen
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

    /**
     * Receives the part that was selected on the Main screen
     * @param p the selected part
     */
    public static void setPassedPart(Part p) {
        passedPart = p;
    }

    /**
     * Saves the changes made to a new part and replaces the existing part in the allParts list as well as in any Associated Parts lists within a Product
     * @param actionEvent the Save Button click
     * @throws IOException
     */
    public void savePart(ActionEvent actionEvent)throws IOException {

        int id = 0;
        int stock = 0;
        double price = 0;
        int min = 0;
        int max = 0;
        int machineCode = 0;

        int index = Inventory.getAllParts().indexOf(passedPart);

        try{
            id = Integer.parseInt(idField.getText());
        }
        catch(Exception e){}

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
                    InHouse inhousePart = new InHouse(id, (nameField.getText()), price, stock, min, max, machineCode);
                    Inventory.updatePart(index, inhousePart);
                    for (Product prod : Inventory.getAllProducts()) {
                        for (Part p : prod.getAllAssociatedParts()) {
                            if (p.getId() == passedPart.getId()) {
                                prod.deleteAssociatedPart(p);
                                prod.addAssociatedPart(inhousePart);
                            }
                        }
                    }
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
                Outsourced outsourcedPart = new Outsourced(id, (nameField.getText()), price, stock, min, max, (machineField.getText()));
                Inventory.updatePart(index, outsourcedPart);
                for(Product prod : Inventory.getAllProducts()){
                    for(Part p : prod.getAllAssociatedParts()){
                        if(p.getId() == passedPart.getId()){
                            prod.deleteAssociatedPart(p);
                            prod.addAssociatedPart(outsourcedPart);
                        }
                    }
                }
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