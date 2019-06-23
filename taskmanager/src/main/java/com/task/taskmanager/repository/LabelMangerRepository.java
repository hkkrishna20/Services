package com.task.taskmanager.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.taskmanager.models.entity.Label;
@Repository
public interface LabelMangerRepository extends CrudRepository<Label, String> {

}