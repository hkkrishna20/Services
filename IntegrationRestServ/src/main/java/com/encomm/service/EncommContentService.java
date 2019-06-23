package com.encomm.service;

import com.encomm.models.content.entityTypeP.ParaType;

public interface EncommContentService extends GenericEncommContentService<ParaType, Integer> {
	ParaType getById(Integer id);
}
