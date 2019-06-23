package com.cgi.ewi.token.oauth.repository;

import org.springframework.data.repository.CrudRepository;

import com.cgi.ewi.token.oauth.domain.Role;


interface RoleRepository extends CrudRepository<Role, Long> {
}
