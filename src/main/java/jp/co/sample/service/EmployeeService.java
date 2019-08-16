package jp.co.sample.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 雇用者情報サービス
 * 
 * @author takahiro.araki
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll(){
		List<Employee> employeeList =employeeRepository.findAll();
		return employeeList;
	}
	
	public Employee load(Integer id) {
		Employee employee=employeeRepository.load(id);
		return employee;
	}
	public void update(Employee employee) {
		
		employeeRepository.update(employee);
		
	}
	
	
}