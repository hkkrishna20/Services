package com.integration.wc.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integration.wc.models.LoginDAO;
import com.integration.wc.models.UserBean;
import com.integration.wc.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private LoginRepository loginRepository;

	public boolean authenticateUser(UserBean userBean) {
		return loginDAO.authenticateUser(userBean);
	}

	public Object save(Object entity) {
		UserBean userBean = (UserBean) entity;
		loginRepository.save(userBean);
		return userBean;
	}

	public Object getById(Long id) {
		return null;
	}

	public Iterable getAll() {
		return null;
	}

	public void delete(Serializable id) {
	}

}