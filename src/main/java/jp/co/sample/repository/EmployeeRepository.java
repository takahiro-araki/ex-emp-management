package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員レポジトリー
 * 
 * @author takahiro.araki
 *
 */
@Repository
public class EmployeeRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER=(rs,i)->{
		Employee employee=new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * 従業員情報を全件検索する.
	 * @return 従業員情報のリスト
	 */
	public List<Employee> findAll(){
		String findAllSql="SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date";
		List<Employee> employeeList=template.query(findAllSql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/**
	 * 従業員情報を主キー検索する.
	 * @param id　id
	 * @return 従業員情報
	 */
	public Employee load(Integer id) {
		String loadSql="SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id";
		SqlParameterSource param =new MapSqlParameterSource().addValue("id",id);
		Employee employee=template.queryForObject(loadSql, param,EMPLOYEE_ROW_MAPPER);
		return employee;
		
	}
	
	/**
	 * 主キーで照合した労働者情報の扶養人数を変更する.
	 * @param employee 労働者情報
	 */
	public void update(Employee employee) {
		String updateSql="UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		SqlParameterSource param=new BeanPropertySqlParameterSource(employee);
		template.update(updateSql, param);
	}
	
	
}