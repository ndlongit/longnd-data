package org.java.demo.dao;

import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl extends AbstractDao<Person, Long> implements PersonDao {
}
