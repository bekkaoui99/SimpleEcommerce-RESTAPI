package ma.web.productbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Product   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String name;
    private Double price;
    private double quantity;
    private boolean selected;
    private boolean availble;

}
