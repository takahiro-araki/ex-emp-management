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

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephon"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;

	};

	/**
	 * 従業員一覧取得メソッド.
	 *  従業員一覧情報を入社日順で取得。
	 * 
	 * @return　List<Employee>　従業員リストを返す。存在しない場合はサイズ0件の従業員一覧を返す　
	 */
	public List<Employee> findAll() {

		String findAllSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics"
				+ " FROM employee ORDER BY hire_date";
		List<Employee> list = template.query(findAllSql, EMPLOYEE_ROW_MAPPER);
		return list;
	}

	/**
	 * 従業員情報取得メソッド 主キーから従業情報を取得
	 * 
	 * @param id　従業員の主キー
	 * @return　Employee　従業員情報を返す
	 */
	public Employee load(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		String loadSql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics"
				+ "FROM employee WHERE id=:id";
//		try {
		Employee employee = template.queryForObject(loadSql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	/**
	 * 従業員情報変更メソッド 従業員情報を変更する
	 * 
	 * @param employee　従業員情報
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql,param);
	}
}