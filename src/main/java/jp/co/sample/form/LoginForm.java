package jp.co.sample.form;

/**
 * ログインフォーム.
 * @author takahiro.araki 
 *
 */
public class LoginForm {
	/**パスワード*/
	private String password;
	
	/**メールアドレス*/
	private String mailAddress;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String toString() {
		return "LoginForm [password=" + password + ", mailAddress=" + mailAddress + "]";
	}
	
		
	

}
