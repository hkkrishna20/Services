package com.cgi.integration.service;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cgi.integration.schema.SourceType;

@Service
public class WcServiceImpl implements WcService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndpointClass.class);

	@Override
	public SourceType formLogic(SourceType sourceType) throws Exception {

		return new FormLogicImpl().formLogic(sourceType);

	}

	@Override
	public SourceType enroute(SourceType sourceType) throws Exception {

		return new RoutingServiceImpl().routeServ(sourceType);
	}

	@Override
	public SourceType interfaceP(SourceType sourceType) throws Exception {

		return new InterfaceImpl().interfaceP(sourceType);
	}

	@Override
	public SourceType endtoEnd(SourceType sourceType) throws Exception {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		logger.info(" In End to End Test Logic  " + xgcal);
		sourceType.getPackage().getHeader().setRequestTimeStamp(xgcal);
		sourceType = interfaceP(sourceType);
		sourceType = enroute(sourceType);
		sourceType = formLogic(sourceType);
		sourceType = formGen(sourceType);
		logger.info("End to End Done");
		return sourceType;
	}

	@Override
	public SourceType split(SourceType sourceType) throws Exception {

		return new SplitServiceImpl().split(sourceType);
	}

	@Override
	public SourceType merge(SourceType sourceType) throws DatatypeConfigurationException, Exception {
		return new MergeServiceImpl().merge(sourceType);
	}

	@Override
	public SourceType formGen(SourceType sourceType) throws Exception {
		return new WCFormGenerationImpl().formGen(sourceType);
	}

}