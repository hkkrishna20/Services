package com.integration.wc.service;

import com.integration.wc.models.ContentTsType;

public interface WCContentService extends GenericWCContentService<ContentTsType,Integer> {

	ContentTsType getById(Integer id);

}