package humphrey.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FUTURE ENHANCEMENT: I would implement reading and writing to files so that the parts and products could be saved permanently. After that, I would host the database in the cloud for global access.
 */
public class Main extends Application {
    /**
     * Used to assign an ID to new Products
     */
    public static int productId = 1;
    /**
     * Used to assign an ID to new Parts
     */
    public static int partId = 1;

    /**
     * Increases the static variable productId by 1
     */
    public static void productIdIncrease(){
        productId++;
    }

    /**
     * Increases the static variable partIdIncrease by 1
     */
    public static void partIdIncrease(){
        partId++;
    }

    /**
     * Creates and loads the main stage
     * @param stage the root stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/humphrey/view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1339.0, 483.0);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The Javadoc folder is in PartsProject\Javadoc\
     * @param args
     */
    public static void main(String[] args) {
  
        launch();

    }
}