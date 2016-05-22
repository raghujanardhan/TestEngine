package testengine.modal;

import testengine.beans.UserInfo;
import testengine.exceptions.UserNotFoundException;
import testengine.service.LoginService;

public class LoginModal {
	
	public boolean validateUser(UserInfo userInfo)throws UserNotFoundException {
		LoginService loginService = new LoginService();
		return loginService.validateUser(userInfo);
		
	}
	public String loadUserModuleDetails(UserInfo userInfo) {
		LoginService loginService = new LoginService();
		return loginService.loadUserModuleDetails(userInfo);
		
	}
	
	
}
