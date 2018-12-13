package de.hdm.gwt.itprojektws18.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.gwt.itprojektws18.client.LoginInfo;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService{
	public LoginInfo login(String requestUri);
}