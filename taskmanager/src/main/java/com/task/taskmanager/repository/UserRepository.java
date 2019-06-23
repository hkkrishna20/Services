package com.task.taskmanager.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.taskmanager.models.entity.User;
@Repository
public interface UserRepository extends CrudRepository<User, String> {


}