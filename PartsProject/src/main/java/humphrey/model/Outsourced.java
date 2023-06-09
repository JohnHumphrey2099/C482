package humphrey.model;

/**
 * Used to create Outsourced part objects
 */
public class Outsourced extends Part{
    /**
     * Used to hold the company name of the Outsourced part
     */
    private String companyName;

    /**
     * Creates a new Outsourced Part
     * @param id the id of the new part
     * @param name the name of the new part
     * @param price the price of the new part
     * @param stock the stock level of the new part
     * @param min the minimum stock level of the new part
     * @param max the maximum stock level of the new part
     * @param companyName the name of the company that produces the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * @param companyName the company name to be set
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     *
     * @return the Company Name
     */
    public String getCompanyName(){
        return companyName;
    }
}
