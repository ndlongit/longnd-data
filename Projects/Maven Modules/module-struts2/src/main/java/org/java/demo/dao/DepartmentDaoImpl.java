package org.java.demo.dao;
import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.Department;
import org.springframework.stereotype.Repository;
@Repository
public class DepartmentDaoImpl extends AbstractDao<Department, Long> implements DepartmentDao {}
