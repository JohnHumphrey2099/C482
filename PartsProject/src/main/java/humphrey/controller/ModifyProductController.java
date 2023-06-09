package humphrey.controller;

import humphrey.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class control the Modify Product Screen. It loads attributes from a product object selected on the main screen and allows the user to make changes.
 */
public class ModifyProductController implements Initializable {
    /**
     * ALl Parts table.
     */
    @FXML
    public TableView partTableView;
    /**
     * Associated parts table
     */
    @FXML
    public TableView associatedParts;
    /**
     * All Parts table id column
     */
    @FXML
    public TableColumn table1c1;
    /**
     * ALl Parts table name column
     */
    @FXML
    public TableColumn table1c2;
    /**
     * All Parts table inventory level column
     */
    @FXML
    public TableColumn table1c3;
    /**
     * All Parts table price column
     */
    @FXML
    public TableColumn table1c4;
    /**
     * Associated Parts table id column
     */
    @FXML
    public TableColumn table2c1;
    /**
     * Associated Parts table name column
     */
    @FXML
    public TableColumn table2c2;
    /**
     * Associated Parts table inventory level column
     */
    @FXML
    public TableColumn table2c3;
    /**
     * Associated Parts table price column
     */
    @FXML
    public TableColumn table2c4;
    /**
     * Search bar for parts table
     */
    @FXML
    public TextField tf1;

    /**
     * Textfield used to display existing id for a product. Cannot be edited by user.
     */
    @FXML
    private TextField idField;
    /**
     * Textfield used to display existing product name. Can be edited by user.
     */
    @FXML
    private TextField nameField;
    /**
     * Textfield used to display existing product price. Can be edited by user.
     */
    @FXML
    private TextField priceField;
    /**
     * Textfield used to display existing product stock level. Can be edited by user.
     */
    @FXML
    private TextField stockField;
    /**
     * Textfield used to display existing product max stock. Can be edited by user.
     */
    @FXML
    private TextField maxField;
    /**
     * Textfield used to display existing product min stock. Can be edited by user.
     */
    @FXML
    private TextField minField;
    /**
     * Used to receive selected product passed from the Main screen.
     */
    static private Product passedProduct;
    /**
     * Used to make a copy of the selected product. Allows the user to cancel without saving changes.
     */
    Product copyProduct = new Product(passedProduct.getId(), passedProduct.getName(), passedProduct.getPrice(), passedProduct.getStock(), passedProduct.getMin(), passedProduct.getMax());

    /**
     * loads the Modify Product scene and populates the allParts and associatedParts tables
     * @param url path of the root object
     * @param resourceBundle resources of the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Part p : passedProduct.getAllAssociatedParts()){
            copyProduct.addAssociatedPart(p);
        }

        partTableView.setItems(Inventory.getAllParts());
        table1c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table1c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table1c3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table1c4.setCellValueFactory(new PropertyValueFactory<>("price"));


        associatedParts.setItems(passedProduct.getAllAssociatedParts());
        table2c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table2c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table2c3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table2c4.setCellValueFactory(new PropertyValueFactory<>("price"));

        idField.setText(String.valueOf(passedProduct.getId()));
        nameField.setText(passedProduct.getName());
        stockField.setText(String.valueOf(passedProduct.getStock()));
        priceField.setText(String.valueOf(passedProduct.getPrice()));
        maxField.setText(String.valueOf(passedProduct.getMax()));
        minField.setText(String.valueOf(passedProduct.getMin()));
    }

    /**
     * Adds selected Part to associatedParts list
     * @param actionEvent add button click event
     */
    public void addSelectedItems(ActionEvent actionEvent) {

        Part selectedItem = (Part)(partTableView.getSelectionModel().getSelectedItem());
        if(selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Part Selected");
            alert.show();
        }
        else {
            passedProduct.addAssociatedPart(selectedItem);
        }

    }

    /**
     * Saves changes to the product
     * @param actionEvent save button click event
     * @throws IOException
     */
    public void saveProduct(ActionEvent actionEvent)throws IOException{

        int id = 0;
        int stock = 0;
        double price = 0;
        int min = 0;
        int max = 0;

        try{
            id = Integer.parseInt(idField.getText());
        }
        catch(NumberFormatException e){}

        try{
            stock = Integer.parseInt(stockField.getText());

        }
        catch (NumberFormatException e){}

        try{
            price = Double.parseDouble(priceField.getText());

        }
        catch (NumberFormatException e) {

            }
        try{
            price = Double.parseDouble(priceField.getText());
        }
        catch(NumberFormatException e){}

        try{
            min = Integer.parseInt(minField.getText());
        }
        catch(NumberFormatException e){}

        try{
            max = Integer.parseInt(maxField.getText());
        }
        catch(NumberFormatException e){}


        if (!(priceField.getText()).matches("-?\\d+(\\.\\d+)?")) {
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
            passedProduct.setId(id);
            passedProduct.setName(nameField.getText());
            passedProduct.setStock(stock);
            passedProduct.setPrice(price);
            passedProduct.setMax(max);
            passedProduct.setMin(min);

            //load widget hierarchy of the Main screen
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));

            //get the stage from the Action Event of clicking the "Add" button on the Parts table
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
    }

    /**
     * Rreturns to the main screen without saving changes
     * @param actionEvent cancel button click event
     * @throws IOException
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
        int index = Inventory.getAllProducts().indexOf(passedProduct);

        Inventory.updateProduct(index, copyProduct);

        //load widget hierarchy of the Main screen
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));

        //get the stage from the Action Event of clicking the "Add" button on the Parts table
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
     * Populates the Parts table with parts matching the user given search terms.
     * @param actionEvent the search button click event
     * @throws IOException
     */
    public void partSearch(ActionEvent actionEvent) throws IOException {
        String s = tf1.getText();
        ObservableList<Part> searchResult = FXCollections.observableArrayList();
        Part searchPart;

        try {
            int num = Integer.parseInt(s);
            searchPart = Inventory.lookupPart(num);
            if(searchPart == null){

            }
            else {
                searchResult.add(searchPart);
            }

        } catch (Exception e) {
            searchResult = Inventory.lookupPart(s);
        }
        partTableView.setItems(searchResult);
        if(searchResult.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Found");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Part Found");
            alert.show();
        }
    }
    /**
     * Removes selected part from the associated parts list
     * @param actionEvent remove button click event
     * @throws IOException
     */
    public void removePart(ActionEvent actionEvent) throws IOException{
        Part selectedItem = (Part)(associatedParts.getSelectionModel().getSelectedItem());
        if(selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Part Selected");
            alert.show();
        }
        else{
            passedProduct.deleteAssociatedPart(selectedItem);
        }
    }

    /**
     * Receives the Product passed from the Main screen controller
     * @param p the product passed
     */
    public static void setPassedProduct(Product p) {
        passedProduct = p;
    }
}