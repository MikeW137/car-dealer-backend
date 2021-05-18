package cardealerbackend.backend.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Cars {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private Integer year;

    @Column
    private String trim;

    @Column
    private String engine;

    @Column
    private Boolean isNew;

    @Column
    private String stockPhoto;


    public Cars() {
    }

    public Cars(Long id, String make, String model, Integer year, String trim, String engine, Boolean isNew, String stockPhoto) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.trim = trim;
        this.engine = engine;
        this.isNew = isNew;
        this.stockPhoto = stockPhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStockPhoto() {
        return stockPhoto;
    }

    public void setStockPhoto(String stockPhoto) {
        this.stockPhoto = stockPhoto;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", trim='" + trim + '\'' +
                ", engine='" + engine + '\'' +
                ", isNew=" + isNew +
                '}';
    }


}
