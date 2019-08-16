package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報コントローラー
 * 
 * @author takahiro.araki
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	
	/**
	 * 入力された管理者情報を格納するフォームをリクエストマッピングに格納する.
	 * 
	 * @return　管理者情報フォーム
　	 */
	@ModelAttribute
	public InsertAdministratorForm setUpAdministrator() {
		InsertAdministratorForm insertAdministratorForm=new InsertAdministratorForm();
		return insertAdministratorForm;
	}
	
	@ModelAttribute
	public LoginForm setUpLogin() {
		LoginForm loginForm=new LoginForm();
		return loginForm;
	}
	
	@Autowired
	private AdministratorService administatorService;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * 管理者情報登録画面を表示する.
	 * 
	 * @return 管理者情報登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報を登録する.
	 * @param insertAdministratorForm 管理者情報の入ったフォーム
	 * @return　ログイン画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm insertAdministratorForm) {
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(insertAdministratorForm, administrator);
		administatorService.insert(administrator);
		return "redirect:/";
	}
	/**
	 * ログイン画面を表示する.
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * メールアドレスとパスワードを取得し、ログイン処理を行う.
	 * 
	 * @param メールアドレスとパスワードを受け取るフォーム
	 * @param リクエストスコープ
	 * @return　成功したら従業員一覧画面、失敗したらログイン画面を返す
	 */
	@RequestMapping("/login")
	public String login(LoginForm loginForm ,Model model) {
		Administrator administrator=new Administrator();
		BeanUtils.copyProperties(loginForm, administrator);
		Administrator administratorDate =administatorService.findBy(administrator.getPassword(),administrator.getMailAddress());
		if(administratorDate!=null) {
			session.setAttribute("administratorName", administratorDate.getName());
			return "forward:/employee/showList";
	}else {model.addAttribute("error","パスワードまたはメースアドレスが不正です。");
	return toLogin();}
		
		
	
}
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
