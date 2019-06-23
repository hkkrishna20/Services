package com.encomm.service;

import com.encomm.models.content.entityTypeP.FileTableType;

public interface EncommFileService extends GenericEncommFileService<FileTableType,Integer> {

	FileTableType getById(Integer id);

}