package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

public interface EmployeeService {
     

	public Employee getEmployee(int empId);
	public List<Employee> getAllEmployee();
	public Employee addEmployee(Employee emp);
	public Employee modifyEmployee(Employee emp);
	public String removeEmployee(int empId);
}
