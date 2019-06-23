package com.cgi.integration.service;

import com.cgi.integration.schema.SourceType;

public interface WcService {
	SourceType split(SourceType sourceType) throws Exception;

	SourceType merge(SourceType sourceType) throws Exception;

	SourceType formGen(SourceType sourceType) throws Exception;

	SourceType formLogic(SourceType sourceType) throws Exception;

	SourceType enroute(SourceType sourceType) throws Exception;

	SourceType interfaceP(SourceType sourceType) throws Exception;

	SourceType endtoEnd(SourceType sourceType) throws Exception;
	SourceType encomm(SourceType sourceType) throws Exception;

}