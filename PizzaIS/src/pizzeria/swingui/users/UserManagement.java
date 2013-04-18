package pizzeria.swingui.users;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import pizzeria.core.userroles.IUserRole;

public class UserManagement {

	private IUserRole currentUser;
	
	private Collection<IUserRole> users = new HashSet<IUserRole>();
	
	public IUserRole getCurrentUserRole() {
		return currentUser;
	}

	public void setCurrentUserRole(IUserRole currentUser) {
		this.currentUser = currentUser;
		if(!users.contains(currentUser)){
			users.add(currentUser);
		}
	}
	
	public void addUser(IUserRole user){
		this.users.add(user);
	}
	
	public void removeUserRole(IUserRole user){	
		this.users.remove(user);
//		if(currentUser.equals(user)){
//			this.actions.signOut();
//		}
	}
	
	public Collection<IUserRole> getUserRoles(){
		return Collections.unmodifiableCollection(users);
	}
}
