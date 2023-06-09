package humphrey.controller;

import humphrey.model.Inventory;
import humphrey.model.Main;
import humphrey.model.Part;
import humphrey.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the Add Product screen. It takes data from the user and creates new Products objects.
 */
public class AddProductController implements Initializable {
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
     * Observable list to add associated parts to
     */
    @FXML
    private  ObservableList<Part> tempList = FXCollections.observableArrayList();
    /**
     * Textfield to get user supplied name for the product
     */
    @FXML
    private TextField nameField;
    /**
     * Textfield to get user supplied stock level for the product
     */
    @FXML
    private TextField stockField;
    /**
     * Textfield to get user supplied price for the product
     */
    @FXML
    private TextField priceField;
    /**
     * Textfield to get user supplied min stock for the product
     */
    @FXML
    private TextField minField;
    /**
     * Textfield to get user supplied max stock for the product
     */
    @FXML
    private TextField maxField;

    /**
     * Initializes the Add Product screen and populates the allParts table and the Associated Parts table
     * @param url path of the root object
     * @param resourceBundle resources of the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partTableView.setItems(Inventory.getAllParts());
        table1c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table1c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table1c3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table1c4.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedParts.setItems(tempList);
        table2c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table2c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table2c3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table2c4.setCellValueFactory(new PropertyValueFactory<>("price"));

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
         * Adds the selected Part to the associated parts list
         * @param actionEvent the add button action event
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
                tempList.add(selectedItem);
            }
    }

    /**
     * Creates a new Product and adds it to the allProducts list
     * @param actionEvent save button click event
     * @throws IOException
     */
    public void saveNewProduct(ActionEvent actionEvent)throws IOException{

        int stock = 0;
        double price = 0;
        int min = 0;
        int max = 0;

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
            stockAlert.setContentText("Stock must be less than or equal to Max and greater than or equal to Min. Please try again.");
            stockAlert.show();
        }
        else {
            Product p = new Product(Main.productId, (nameField.getText()), price, stock, min, max);
            for(Part part : tempList) {
                p.addAssociatedPart(part);
            }
            Inventory.addProduct(p);
            Main.productIdIncrease();

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
     * Loads the Main screen without saving a new product
     * @param actionEvent cancel button click action event
     * @throws IOException
     */
    public void cancel(ActionEvent actionEvent) throws IOException {
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
     * Removes a selected part from the Associated Parts list
     * @param actionEvent remove button click action event
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
            associatedParts.getItems().remove(selectedItem);
        }
    }

}