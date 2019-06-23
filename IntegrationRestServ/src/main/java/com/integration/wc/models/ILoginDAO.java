package com.integration.wc.models;

public interface ILoginDAO {
	public abstract boolean authenticateUser(UserBean userBean);
}