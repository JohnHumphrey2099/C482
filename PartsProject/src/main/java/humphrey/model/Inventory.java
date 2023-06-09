package humphrey.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is used to manage the list of all parts and the list of all products.
 */
public class Inventory {
    /**
     * List of all Parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * List of all Products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Used to get the allParts list
     * @return allParts static observable list
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Used to get the all products list.
     * @return allProducts static observable list
     */
    public static ObservableList<Product> getAllProducts(){return allProducts;}

    /**
     * Adds a part to the allParts list
     * @param newPart the part to add to the allParts list
     */
    public static void addPart (Part newPart){
        allParts.add(newPart);
    }

    /**
     * Adds a product to the allProducts list
     * @param newProduct the product to add
     */
    public static void addProduct (Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * Returns an observable list of parts based on part name searched
     * @param partName the name of the part to look up
     * @return the list
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchResult = FXCollections.observableArrayList();
        for (Part searchPart : allParts) {
            if (searchPart.getName().contains(partName)){
                searchResult.add(searchPart);
            }
        }
        return searchResult;
    }

    /**
     * Returns an observable list of parts based on id searched
     * @param partID the id of the part to look up
     * @return the search results
     */
public static Part lookupPart(int partID) {
        Part searchResult = null;
        for (Part searchPart : allParts) {
            if (searchPart.getId() == partID){
                searchResult = searchPart;
            }
        }
        return searchResult;
    }

    /**
     * Returns a product based on the id searched
     * @param productID the id of the product to look up
     * @return the search results
     */
    public static Product lookupProduct(int productID){
        Product searchResult = null;
        for (Product searchProduct: allProducts) {
            if (searchProduct.getId() == productID){
                searchResult = searchProduct;
            }
        }
        return searchResult;
    }

    /**
     * Returns an observable list of products based on name searched
     * @param productName the name to search for
     * @return list of products that match the search name
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> searchResult = FXCollections.observableArrayList();
        for (Product searchProduct : allProducts) {
            if (searchProduct.getName().contains(productName)){
                searchResult.add(searchProduct);
            }
        }
        return searchResult;
    }

    /**
     * Removes a part from allParts list. Returns true if successful
     * @param selectedPart the part to delete
     * @return true if successful
     */
    public static boolean deletePart(Part selectedPart){
        return (allParts.remove(selectedPart));
    }

    /**
     * Removes a product from the allProducts list. Returns true if successful
     * @param selectedProduct the product to remove.
     * @return true if successful
     */
    public static boolean deleteProduct(Product selectedProduct){
        return (allProducts.remove(selectedProduct));
    }

    /**
     * Removes a part from the allParts list and replaces it with new part
     * @param index position of the part in the list
     * @param selectedPart the new part to add to the list
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.remove(index);
        allParts.add(index, selectedPart);
    }

    /**
     * Removes a product from the allProducts list and replaces it with a new product
     * @param index position of the product in the list
     * @param newProduct the new product to add to the list
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.remove(index);
        allProducts.add(index, newProduct);
    }
}
