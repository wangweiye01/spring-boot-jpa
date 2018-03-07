package org.sang;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_company")
public class Company {
    @GeneratedValue
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Department> departmentSet = new HashSet<>();

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

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }
}
