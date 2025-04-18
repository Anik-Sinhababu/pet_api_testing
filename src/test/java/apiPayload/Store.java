package apiPayload;

public class Store {
    int id;
    int petID;
    int quantity;
    String shipDate;
    String status;
    Boolean complete;

    public Store(int id, int petID, int quantity, String shipDate, String status, Boolean complete)
    {
        this.id = id;
        this.petID = petID;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPetID() {
        return petID;
    }
    public void setPetID(int petID) {
        this.petID = petID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getShipDate() {
        return shipDate;
    }
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Boolean getComplete() {
        return complete;
    }
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
