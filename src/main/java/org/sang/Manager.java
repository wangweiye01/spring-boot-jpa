package org.sang;

import javax.persistence.*;

@Table(name = "T_MANAGER")
@Entity
public class Manager {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL, optional = true)
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
