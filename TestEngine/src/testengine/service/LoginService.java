package testengine.service;

import testengine.beans.UserInfo;
import testengine.dao.UserLoginInterface;
import testengine.dao.batch.BatchInterface;
import testengine.exceptions.UserNotFoundException;
import testengine.factory.TestEngineFactory;

public class LoginService {
	
	public boolean validateUser(UserInfo userInfo) throws UserNotFoundException{
		TestEngineFactory testFactory = new TestEngineFactory();
		UserLoginInterface loginInterface = testFactory.getLoginDAO("login");
		
		return loginInterface.validateUser(userInfo);
		
	}
	public String loadUserModuleDetails(UserInfo userInfo) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		UserLoginInterface loginInterface = testFactory.getLoginDAO("login");
		
		return loginInterface.loadUserModuleDetails(userInfo);
		
	}
	
	
	
	

}
