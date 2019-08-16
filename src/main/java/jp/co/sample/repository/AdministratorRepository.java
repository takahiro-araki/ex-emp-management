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
	
	
	private final static RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER=(rs,i)->{
		Administrator administrator=new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * 管理者情報を挿入.
	 * @param 管理者情報ドメイン
	 */
	public void insert(Administrator administrator) {
		String insertSql="INSERT INTO administrators(name,mail_address,password) VALUES (:name,:mailAddress,:password)";
		SqlParameterSource param= new BeanPropertySqlParameterSource(administrator);
		template.update(insertSql, param);
		
	} 
	
	
	/**
	 * メールアドレスとパスワードによって、管理者情報を取得する
	 * 
	 * @param mailAddress　メールアドレス
	 * @param password　パスワード
	 * @return　管理者情報
	 */
	public Administrator findByMailAddressAndPasword(String mailAddress, String password) {
		String findBySql ="SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password";
		SqlParameterSource param=new MapSqlParameterSource().addValue("mailAddress",mailAddress).addValue("password", password);
		List<Administrator> administratorList=template.query(findBySql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorList.size()==0) {
			return null;
		}
		Administrator administrator= administratorList.get(0);
		return administrator;
	}
	
}
