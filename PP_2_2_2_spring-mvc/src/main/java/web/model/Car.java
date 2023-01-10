package web.model;

public class Car {

    private String brand;

    private int productionYear;

    private int doors;

    public Car(String brand, int productionYear, int doors) {
        this.brand = brand;
        this.productionYear = productionYear;
        this.doors = doors;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
