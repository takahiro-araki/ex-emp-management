package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;

/**
 * コントローラーから引数を受け取り、レポジトリーの管理者情報挿入メソッドを呼び出す.
 * @param administrator 管理者情報
 */
public void insert(Administrator administrator) {
	administratorRepository.insert(administrator);
    	
    }
public Administrator findBy(String password,String mailAddress) {
	
	Administrator administrator=administratorRepository.findByMailAddressAndPasword(mailAddress, password);
	return administrator ;
	
}



}
     


