package cardealerbackend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long vin;

    @Column
    private String color;

    @Column
    private String fuelEfficiency;

    @Column
    private Long price;


    @ManyToOne
    @JoinColumn(name = "cars_id")
    @JsonIgnore
    private Cars car;

    @OneToMany(mappedBy = "individualCar", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Image> imageList;

    public Inventory() {
    }

    public Inventory(Long id, Long vin, String color, String fuelEfficiency, Long price) {
        this.id = id;
        this.vin = vin;
        this.color = color;
        this.fuelEfficiency = fuelEfficiency;
        this.price = price;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVin() {
        return vin;
    }

    public void setVin(Long vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(String fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", vin=" + vin +
                ", color='" + color + '\'' +
                ", fuelEfficiency='" + fuelEfficiency + '\'' +
                ", price=" + price +
                '}';
    }
}
