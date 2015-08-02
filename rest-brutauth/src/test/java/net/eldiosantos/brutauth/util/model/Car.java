package net.eldiosantos.brutauth.util.model;

/**
 * Created by Eldius on 01/08/2015.
 */
public class Car {
    private Long id;
    private String model;
    private String brand;

    public Car() {
    }

    public Car(Long id, String model, String brand) {
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }
}
