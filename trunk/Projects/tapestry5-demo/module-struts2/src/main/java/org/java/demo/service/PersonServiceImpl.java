package org.java.demo.service;

import org.java.demo.dao.PersonDao;
import org.java.demo.model.Person;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl extends AbstractService<Person, Long, PersonDao> implements PersonService {
}
