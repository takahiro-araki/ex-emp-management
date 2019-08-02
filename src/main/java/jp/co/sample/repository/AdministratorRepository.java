package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者情報のレポジトリー
 * 
 * @author takahiro.araki
 *
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administrator =new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
		
	};
	
	/**
	 * 管理者情報挿入メソッド.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		
		SqlParameterSource param= new BeanPropertySqlParameterSource(administrator);
		
			String insertSql="INSERT INTO administrator(id,name,mail_address,password)"
					+ "VALUES(:id,:name,:mailAddress,:password)";
			template.update(insertSql, param);
		
		
		
		
	}
	/**
	 * 管理者情報取得メソッド.
	 * メールアドレスとパスワードによって照合して、該当する管理者情報を取得する。
	 * 
	 * @param mailAddress メールアドレス
	 * @param password　パスワード
	 * @return　Administrator　管理者情報(もし存在しなければnullが返る)
	 */
	public Administrator findByMailAddressAndPassword (String mailAddress, String password) {
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password",password);
		String findBySql="SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress"
					+ "&& password=:password";
		List<Administrator> list= template.query(findBySql, param,ADMINISTRATOR_ROW_MAPPER);
		if(list.size()==0) {
			return null;
		}else {
			Administrator administrator = list.get(0);
			return administrator;
			
		}
				}
	
}
