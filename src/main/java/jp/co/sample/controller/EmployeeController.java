package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/showList")
	public String list(Model model) {
		List<Employee> employeeList=employeeService.findAll();
		model.addAttribute("employeeList",employeeList);
		return "employee/list";
	}
	
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Employee employee =employeeService.load(id);
		model.addAttribute("employee",employee);
		return "employee/detail";
	}
	
	@RequestMapping("/update")
	public String update(Integer id, Integer dependentsCount) {
		Employee employee=new Employee();
		employee.setId(id);
		employee.setDependentsCount(dependentsCount);
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
	
	
}


