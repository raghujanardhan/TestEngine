package testengine.modal;

import java.util.ArrayList;

import testengine.beans.ModuleInfo;
import testengine.beans.UserInfo;
import testengine.service.CandidateService;
import testengine.service.UserService;

public class UserModal {
	
	public boolean  saveUser(UserInfo user) {
		
		UserService userService = new UserService();
		return userService.saveUser(user);
		
	}
	public ArrayList<UserInfo> listUsers(int offset, int noOfRecords) {
		
		UserService userService = new UserService();
		return userService.listUsers(offset,noOfRecords);
		
	}
	public ArrayList<ModuleInfo> getAllModules(){
		UserService userService = new UserService();
		return userService.getAllModules();
		
	}
	
	public int deleteUser(String userId){
		UserService userService = new UserService();
		return userService.deleteUser(userId);
	}
	public ArrayList<UserInfo> loadUser(String userId) {
		UserService userService = new UserService();
		return userService.loadUser(userId);
		
	}
	public boolean  editUser(UserInfo user) {
		
		UserService userService = new UserService();
		return userService.editUser(user);
		
	}
	public ArrayList<UserInfo> searchUsers(String searchParam , int offset, int noOfRecords) {
		
		UserService userService = new UserService();
		return userService.searchUsers(searchParam,offset,noOfRecords);
		
	}
}
