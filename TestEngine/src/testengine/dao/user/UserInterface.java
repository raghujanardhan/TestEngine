package testengine.dao.user;

import java.util.ArrayList;

import testengine.beans.ModuleInfo;
import testengine.beans.UserInfo;

public interface UserInterface {
	public int saveUser(UserInfo user);
	public ArrayList<UserInfo> listUsers(int offset, int noOfRecords);
	public ArrayList<ModuleInfo> getAllModules();
	public int deleteUser(String userId);
	public ArrayList<UserInfo> loadUser(String userId);
	public int editUser(UserInfo user);
	public ArrayList<UserInfo> searchUsers(String searchParam , int offset, int noOfRecords) ;

}
