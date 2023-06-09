package humphrey.model;

/**
 * This class creates In-House part objects.
 */
public class InHouse extends Part{
    /**
     * The Machine ID of the part
     */
    private int machineId;

    /**
     * Creates a new In-House Part
     * @param id the id of the new part
     * @param name the name of the new part
     * @param price the price of the new part
     * @param stock the stock level of the new part
     * @param min the minimum stock level of the new part
     * @param max the maximum stock level of the new part
     * @param machineId the Machine ID of the new part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *
     * @param machineId the Machine ID to set
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     *
     * @return the Machine ID
     */
    public int getMachineId(){
        return  machineId;
    }
}
