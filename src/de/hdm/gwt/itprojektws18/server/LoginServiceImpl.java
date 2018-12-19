package de.hdm.gwt.itprojektws18.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gwt.itprojektws18.client.LoginInfo;
import de.hdm.gwt.itprojektws18.shared.LoginService;

/**
 * Implementierung des serverseitigen RPC-Services fuer den Login. 
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

	/**
	 * Von Eclipse automatisch generiert.
	 */
	private static final long serialVersionUID = 1L;
	

	  public LoginInfo login(String requestUri)  throws IllegalArgumentException {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    LoginInfo loginInfo = new LoginInfo();

	    if (user != null) {
	      loginInfo.setLoggedIn(true);
	      loginInfo.setEmailAddress(user.getEmail());
	      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
	    } else {
	      loginInfo.setLoggedIn(false);
	      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
	    }
	    return loginInfo;
	  }


}