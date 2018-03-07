package org.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sang on 2016/12/30.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person person = personRepository.save(new Person(null, name, age, address));
        return person;
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person people = personRepository.findByNameAndAddress(name, address);
        return people;
    }

    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        Person person = personRepository.withNameAndAddressQuery(name, address);
        return person;
    }

    @RequestMapping("/q4")
    public Person q4(String name, String address) {
        Person person = personRepository.withNameAndAddressNamedQuery(name, address);
        return person;
    }

    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return people;
    }

    @RequestMapping("/page")
    public Page<Person> page(int page, int size) {
        Page<Person> all = personRepository.findAll(new PageRequest(page, size));
        return all;
    }

    @RequestMapping("/all")
    public List<Person> all() {
        return personRepository.findAll();
    }

    @RequestMapping("/order/save")
    public void save() {
        User user = new User();
        user.setName("tom");
        user.setEmail("tom@qq.com");

        Order order1 = new Order();
        order1.setName("o1");
        order1.setUser(user);

        userRepository.save(user);
        orderRepository.save(order1);
    }

    @RequestMapping("/oneOrder")
    public String orders() {
        return orderRepository.findOne(5).toString();
    }

    @RequestMapping("/updateUser")
    public void updateUser() {
        User user = userRepository.findByName("tom");
        user.setEmail("wwyknight@163.com");
        userRepository.save(user);
    }

    @RequestMapping("/user/orders")
    public void userOrders() {
        User user = userRepository.findByName("tom");
        user.setName("wwy");
        userRepository.save(user);

        user.getOrders().forEach(x -> System.out.println(x.getName().toString()));
    }

    @RequestMapping("/category/ready")
    public void ready() {
        Category category1 = new Category();
        category1.setName("ctg-1");

        Category category2 = new Category();
        category2.setName("ctg-2");

        Item item1 = new Item();
        item1.setName("item-1");

        Item item2 = new Item();
        item2.setName("item-2");

        // 建立关联关系
        category1.getItemSet().add(item1);
        category1.getItemSet().add(item2);

        category2.getItemSet().add(item1);
        category2.getItemSet().add(item2);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);

        itemRepository.save(item1);
        itemRepository.save(item2);
        categoryRepository.save(categoryList);
    }

    @RequestMapping("/categorys")
    public void categorys() {
        Set<Item> items = categoryRepository.findById(7).getItemSet();

        List<Category> categories = categoryRepository.findAll();

        categories.forEach(x -> {
            System.out.println(x.getName());
        });

        items.forEach(x -> {
            System.out.println(x.getName());
        });
    }

    @RequestMapping("/department/ready")
    public void departmentReady() {
        Manager manager = new Manager();
        manager.setName("manger w");

        Department department = new Department();
        department.setName("department y");


        manager.setDepartment(department);
        department.setManager(manager);

        managerRepository.save(manager);
    }

    @RequestMapping("/departments")
    public void departments() {
        /*Department department = departmentRepository.findByName("x");

        System.out.println("--->>-----:" + department.getName());

        Manager manager = department.getManager();

        System.out.println("----->>>-----:" + manager.getName());*/

        Manager manager1 = managerRepository.findOne(8);

        System.out.println("----->>>-----:" + manager1.getName());
    }


    @RequestMapping("/saveCompany")
    public void saveCompany() {
        Manager manager = new Manager();
        manager.setName("wangjingli");

        Department department = new Department();
        department.setName("kaifabu");
        department.setManager(manager);
        manager.setDepartment(department);

        Company company = new Company();
        company.setName("heli");
        company.getDepartmentSet().add(department);

        department.setCompany(company);

        managerRepository.save(manager);
        departmentRepository.save(department);
        companyRepository.save(company);

        System.out.println("OK");
    }

    @RequestMapping("/oneCompany")
    public void oneCompany() {
        Set<Department> departments = companyRepository.findOne(4).getDepartmentSet();

        departments.forEach(x -> {
            System.out.println(x.getName());
            System.out.println(x.getManager().getName());
        });
    }

    @RequestMapping("/deleteCompany")
    public void deleteCompay() {
        companyRepository.delete(4);
    }
}
