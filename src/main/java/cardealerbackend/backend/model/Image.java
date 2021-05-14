package cardealerbackend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name="images")
public class Image {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    public Image() {
    }

    public Image(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
