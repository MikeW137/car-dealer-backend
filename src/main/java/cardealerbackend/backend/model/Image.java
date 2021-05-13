package cardealerbackend.backend.model;

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

    @ManyToOne
    @JoinColumn
    private Inventory individualCar;


}
