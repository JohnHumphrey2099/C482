package humphrey.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates Product objects
 */
public class Product {
    /**
     * The id of the Product
     */
    private int id;
    /**
     * The name of the product
     */
    private String name;
    /**
     * The price of the product
     */
    private double price;
    /**
     * The stock level of the product
     */
    private int stock;
    /**
     * The min stock of the product
     */
    private int min;
    /**
     * The max stock of the product
     */
    private int max;
    /**
     * The list of associated parts of the product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Creates a new Product
     * @param id the id of the new product
     * @param name the name of the new product
     * @param price the price of the new
     * @param stock the stock level of the new product
     * @param min the minimum of the new product
     * @param max the maximum of the new product
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Sets the id of the Product
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the product
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the product
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the stock level of the product
     * @param stock the stock level to be set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the min of the product
     * @param min the min to be set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the max of the product
     * @param max the max to be set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets the id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Associates a part with a product
     * @param part the part to associate
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Removes part from the associated part list
     * @param selectedAssociatedPart the part to remove
     * @return true if successful
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
            return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     *
     * @return list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}

