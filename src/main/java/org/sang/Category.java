package org.sang;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "t_category")
@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @JoinTable(name = "category_item", joinColumns = {@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Set<Item> itemSet = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
    }
}
