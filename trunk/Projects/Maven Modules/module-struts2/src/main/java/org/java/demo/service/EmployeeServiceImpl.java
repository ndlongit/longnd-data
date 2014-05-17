package org.java.demo.service;

import org.java.demo.dao.EmployeeDao;
import org.java.demo.model.Employee;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends AbstractService<Employee, Long, EmployeeDao> implements EmployeeService {
}
