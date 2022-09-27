package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
//import com.example.demo.pojo.EmployeeRecord;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServicempl implements EmployeeService {
	
	@Autowired
	public EmployeeRepository empDao;

	public Employee getEmployee(int empId) {
//		Employee employee = empService.getEmployee(empId);
		Employee emp = new Employee();
		try {
			emp = convertToEmployee(empDao.findById(empId));
		} catch (Exception e) {
			System.out.println("exception while getting employee");
		}
		return emp;
	}

	private Employee convertToEmployee(Optional<Employee> emp) {
		Employee employee = new Employee();
		if (emp != null) {
			employee.setEmpId(emp.get().getEmpId());
			employee.setEmpName(emp.get().getEmpName());
		}
		return employee;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = new ArrayList<>();
		try {
			empList = empDao.findAll();
		} catch (Exception e) {
			System.out.println("Exception while getting list of Employee");
		}
		return empList;
	}

	public Employee addEmployee(Employee emp) {
		Employee savedEmployee = new Employee();
		try {
			savedEmployee = empDao.save(emp);
		} catch (Exception e) {
			System.out.println("Exception while adding Employee");
		}
		return savedEmployee;
	}

	public Employee modifyEmployee(Employee emp) {
		Employee employee = new Employee();
		try {
			employee = getEmployee(emp.getEmpId());

			if (employee != null) {
				employee.setEmpName(emp.getEmpName());
				empDao.save(employee);
			}
			
		} catch (Exception e) {
			System.out.println("Exception while modifing Employee");
		}
		return employee;
	}

	public String removeEmployee(int empId) {
		Employee employee = new Employee();
		boolean flag = false;
		try {
			employee = getEmployee(empId);
			if (employee != null) {
				empDao.deleteById(empId);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Exception while removing Employee");
		}
		if (flag) {
			return "employee deleted Successfully with id= " + empId;
		} else {
			return "id " + empId + " does not exist";
		}
	}

	



		
	}
