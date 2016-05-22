package testengine.service;

import java.util.ArrayList;

import testengine.beans.ModuleInfo;
import testengine.beans.UserInfo;
import testengine.dao.batch.BatchInterface;
import testengine.dao.user.UserImpl;
import testengine.dao.user.UserInterface;
import testengine.factory.TestEngineFactory;

public class UserService {
	public boolean saveUser(UserInfo user) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		int userCreate = ui.saveUser(user);
		if(userCreate > 0){
			return true;
		}
		else
		{
			return false;
		}
	}
	public ArrayList<UserInfo> listUsers(int offset, int noOfRecords) {
		
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		 return ui.listUsers(offset,noOfRecords);
		
	}
	public ArrayList<ModuleInfo> getAllModules(){
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		return ui.getAllModules();
		
	}
	public int deleteUser(String userId){
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		return ui.deleteUser(userId);
	}
	public ArrayList<UserInfo> loadUser(String userId){
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		return ui.loadUser(userId);
	}
	public boolean editUser(UserInfo user) {
	
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		int userEdit = ui.editUser(user);
		if(userEdit > 0){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ArrayList<UserInfo> searchUsers(String searchParam , int offset, int noOfRecords) {
		TestEngineFactory testFactory = new TestEngineFactory();
		UserInterface ui = testFactory.getUserDAO("user");
		return ui.searchUsers(searchParam,offset,noOfRecords);
		
	}
	
}
