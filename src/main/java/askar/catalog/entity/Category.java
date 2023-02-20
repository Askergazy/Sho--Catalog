package askar.catalog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")

public class Category {
     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @OneToMany (mappedBy = "category")
    private List<Option> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}

