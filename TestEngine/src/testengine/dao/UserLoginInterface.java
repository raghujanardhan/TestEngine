package testengine.dao;

import testengine.beans.UserInfo;
import testengine.exceptions.UserNotFoundException;

public interface UserLoginInterface {
	
	public boolean validateUser(UserInfo userInfo) throws UserNotFoundException;
	
	public String loadUserModuleDetails(UserInfo userInfo);

}
