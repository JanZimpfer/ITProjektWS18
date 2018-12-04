package de.hdm.gwt.itprojektws18.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.client.LoginInfo;


public interface LoginServiceAsync{

	public void login(String requestUri, AsyncCallback<LoginInfo> callback);

}