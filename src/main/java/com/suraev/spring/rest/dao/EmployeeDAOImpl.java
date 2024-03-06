package com.suraev.spring.rest.dao;

import com.suraev.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class  EmployeeDAOImpl implements EmployeeDAO{
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    


    public List<Employee> getAllEmployees() {
        Session session= sessionFactory.getCurrentSession();
//        List<Employee> employeeList=session.createQuery("from Employee",
//                Employee.class).getResultList();
        Query<Employee> query=session.createQuery("from Employee", Employee.class);
        List<Employee> employeeList= query.getResultList();

        return employeeList;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session= sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session= sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session=sessionFactory.getCurrentSession();
        /*Employee employee= session.get(Employee.class,id);
        session.delete(employee);*/

        Query<Employee>employeeQuery=session.createQuery("delete from Employee " +
                "where id=:employeeId");
        employeeQuery.setParameter("employeeId",id);
        employeeQuery.executeUpdate();
    }


}
