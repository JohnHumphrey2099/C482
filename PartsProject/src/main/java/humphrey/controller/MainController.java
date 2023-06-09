package humphrey.controller;

import humphrey.model.Main;
import humphrey.model.Inventory;
import humphrey.model.Part;
import humphrey.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class controls the Main screen. It creates tables to view and select parts and products.
 */
public class MainController implements Initializable {
    /**
     * Parts table
     */
    public TableView partTableView;
    /**
     * Parts table id column
     */
    public TableColumn table1c1;
    /**
     * Parts table name column
     */
    public TableColumn table1c2;
    /**
     * Parts table inventory level column
     */
    public TableColumn table1c3;
    /**
     * Parts table price column
     */
    public TableColumn table1c4;
    /**
     * Product table
     */
    public TableView productTableView;
    /**
     * Product table id column
     */
    public TableColumn table2c1;
    /**
     * Product table name column
     */
    public TableColumn table2c2;
    /**
     * Product table inventory level column
     */
    public TableColumn table2c3;
    /**
     * Product table price column
     */
    public TableColumn table2c4;
    /**
     * Search bar for parts table
     */
    public TextField tf1;
    /**
     * Search bar for product table
     */
    public TextField tf2;

    /**
     * Initializes the Main screen and populates the Parts and Products tables
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

        productTableView.setItems(Inventory.getAllProducts());
        table2c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        table2c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        table2c3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        table2c4.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Loads the Add Part scene
     * @param actionEvent the Add button click event
     * @throws IOException
     */

    public void addPartScene(ActionEvent actionEvent) throws IOException {
        //load widget hierarchy of the Add Part screen
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/AddPart.fxml"));

        //get the stage from the Action Event of clicking the "Add" button on the Parts table
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //create a new scene the same size as the "Add Part" screen
        Scene scene = new Scene(fxmlLoader.load(), 666.0, 765.0);

        //set the title of the stage
        stage.setTitle("Add Part");

        //set the scene on the Stage object
        stage.setScene(scene);

        //load the scene
        stage.show();
    }

    /**
     * Calls the passPart method and then loads the Modify Part scene
     * @param actionEvent the modify button click event
     * @throws IOException
     */
    public void modPartScene(ActionEvent actionEvent) throws IOException {
        try {
            passPart();

            //load widget hierarchy of the Modify Part screen
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/ModifyPart.fxml"));

            //get the stage from the Action Event of clicking the "Modify" button on the Parts table
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            //create a new scene the same size as the "Modify Part" screen
            Scene scene = new Scene(fxmlLoader.load(), 666.0, 765.0);

            //set the title of the stage
            stage.setTitle("Modify Part");

            //set the scene on the Stage object
            stage.setScene(scene);

            //load the scene
            stage.show();
        }
        catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Part Selected");
                alert.setHeaderText("ERROR");
                alert.setContentText("No Part Selected");
                alert.show();
        }
    }

    /**
     * Closes the program
     * @param actionEvent the exit button click event
     */

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Loads the Add Product screen
     * @param actionEvent the add button click action event
     * @throws IOException
     */

    public void goToAddProduct(ActionEvent actionEvent) throws IOException {
        //load widget hierarchy of the Add Product screen
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/AddProduct.fxml"));

        //get the stage from the Action Event of clicking the "Add" button on the Parts table
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //create a new scene the same size as the "Add Part" screen
        Scene scene = new Scene(fxmlLoader.load(), 975.0, 653.0);

        //set the title of the stage
        stage.setTitle("Add Product");

        //set the scene on the Stage object
        stage.setScene(scene);

        //load the scene
        stage.show();

    }

    /**
     * Calls the passProduct method and then loads the Modify Product screen
     * @param actionEvent the modify button click event
     * @throws IOException
     */

    public void goToModifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            passProduct();

            //load widget hierarchy of the Add Product screen
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/ModifyProduct.fxml"));

            //get the stage from the Action Event of clicking the "Add" button on the Parts table
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            //create a new scene the same size as the "Add Part" screen
            Scene scene = new Scene(fxmlLoader.load(), 975.0, 653.0);

            //set the title of the stage
            stage.setTitle("Modify Product");

            //set the scene on the Stage object
            stage.setScene(scene);

            //load the scene
            stage.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Product Selected");
            alert.show();
        }
    };

    /**
     * Populates the Parts table with parts matching the user given search terms. RUNTIME ERROR: "Caused by: javafx.fxml.LoadException: Error resolving onAction='#partSearch', either the event handler is not in the Namespace or there is an error in the script." I fixed this by using a Try / Catch block for the parseInt and also had to declare the string "s" outside of the block, or it would not compile.
     * @param actionEvent the search button click event
     * @throws IOException
     */
    public void partSearch(ActionEvent actionEvent) throws IOException{
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
        }
        catch (Exception e){
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
     * Populates the Products table with Products matching the user given search terms
     * @param actionEvent the search button click event
     * @throws IOException
     */

    public void productSearch(ActionEvent actionEvent)  throws IOException{
        String s = tf2.getText();
        ObservableList<Product> searchResult = FXCollections.observableArrayList();
        Product searchProduct;

        try {
            int num = Integer.parseInt(s);
            searchProduct = Inventory.lookupProduct(num);
            if(searchProduct == null){

            }
            else {
                searchResult.add(searchProduct);
            }

        }
        catch (Exception e){
            searchResult = Inventory.lookupProduct(s);
        }
        productTableView.setItems(searchResult);
        if(searchResult.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Product Found");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Product Found");
            alert.show();
        }

    }

    /**
     * Removes selected Part
     * @param actionEvent remove button click event
     */

    public void removeSelectedPart(ActionEvent actionEvent) {
        Part selectedItem = (Part) (partTableView.getSelectionModel().getSelectedItem());

        if(selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Part Selected");
            alert.show();
        }
        else {
            Inventory.deletePart(selectedItem);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Success!");
            alert.setContentText("Selected Part was Deleted");
            alert.show();

        }
    }

    /**
     * Removes selected Product
     * @param actionEvent remove button click event
     */
    public void removeSelectedProduct(ActionEvent actionEvent) {

        Product selectedItem = (Product) (productTableView.getSelectionModel().getSelectedItem());
        if(selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("ERROR");
            alert.setContentText("No Product Selected");
            alert.show();}
        else if(!(selectedItem.getAllAssociatedParts().isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Contains Associated Parts");
            alert.setHeaderText("ERROR");
            alert.setContentText("Product Contains Associated Parts");
            alert.show();

        }
        else {
            Inventory.deleteProduct(selectedItem);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Success!");
            alert.setContentText("Selected Product was Deleted");
            alert.show();
        }
    }

    /**
     * Passes the selected part to the Modify Part controller
     */
    public void passPart() {
        Part selectedItem = (Part)(partTableView.getSelectionModel().getSelectedItem());
        ModifyPartController.setPassedPart(selectedItem);
    }

    /**
     * Passes the selected product to the Modify Product controller
     */
    public void passProduct() {
        Product selectedItem = (Product)(productTableView.getSelectionModel().getSelectedItem());
        ModifyProductController.setPassedProduct(selectedItem);
    }
}
