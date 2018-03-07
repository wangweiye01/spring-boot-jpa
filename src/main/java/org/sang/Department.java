package org.sang;

import javax.persistence.*;

@Table(name = "T_DEPARTMENT")
@Entity
public class Department {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "MANAGER_ID")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Manager manager;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
