package org.java.demo.service;
import org.java.demo.dao.DepartmentDao;
import org.java.demo.model.Department;
import org.java.demo.service.core.AbstractService;
import org.springframework.stereotype.Service;
@Service
public class DepartmentServiceImpl extends AbstractService<Department, Long, DepartmentDao> implements DepartmentService {}
