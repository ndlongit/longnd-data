package org.java.demo.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.java.demo.model.Person;
import org.java.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Preparable;

@Results({ @Result(name = "list", location = "/pages/list.jsp"), @Result(name = "edit", location = "/index.jsp") })
public class PersonAction implements Preparable {

    @Autowired
    private PersonService service;
    private List<Person> persons;
    private Person person;
    private Integer id;

    public String execute() {
        this.persons = service.findAll();
        return "list";
    }

    @Action("list")
    public String list() {
        return execute();
    }

    @Action("user/save")
    public String save() {
        this.service.save(person);
        return execute();
    }

    @Action("edit")
    public String edit() {        
        if (id != null) {
            person = service.find(id);
        }
        return "edit";
    }

    @Action("remove")
    public String remove() {
        service.remove(id);
        return execute();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepare() throws Exception {
        if (id != null) {
            person = service.find(id);
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
