package org.java.demo.dao;

import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends AbstractDao<Employee, Long> implements EmployeeDao {
}
