package com.task.taskmanager.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.taskmanager.models.entity.Task;
@Repository
public interface TaskMangerRepository extends CrudRepository<Task, String> {	

	
	List<Task> findByDeadlineIn(List<String> dates);
	List<Task> findByDeadline(String deadline);
}