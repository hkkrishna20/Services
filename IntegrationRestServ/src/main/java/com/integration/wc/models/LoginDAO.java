package com.integration.wc.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO implements ILoginDAO {

	private JdbcTemplate jdbcTemplate;
	   @PersistenceContext
	    private EntityManager entityManager;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean authenticateUser(UserBean userBean) {
		boolean userExists = false;
		String sql = "select count(*) from login where user_Id = ? and password = ?";

		int rowcount = jdbcTemplate.queryForObject(sql, new Object[] { userBean.getUserId(), userBean.getPassword() },
				Integer.class);

		/*
		 * int rowcount = jdbcTemplate.queryFor("select count(*) from login " +
		 * " where uname = " + userBean.getUserId() + " and password = " +
		 * userBean.getPassword());
		 */
		if (rowcount == 1) {
			userExists = true;
		}
		return userExists;
	}


}