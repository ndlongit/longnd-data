package com.structis.vip.server.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dozer.DozerConverter;
import org.springframework.beans.factory.InitializingBean;

public class DateStringConverter extends DozerConverter<String, Date> implements InitializingBean {
	
	private static Logger logger = Logger.getLogger(DateStringConverter.class);
	private String formatDate;
	private SimpleDateFormat sdf;
	private DateMapperIfc dateMapper;
	private boolean useActualDate = true;

	public DateStringConverter() {
		super(String.class, Date.class);
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public void setDateMapper(DateMapperIfc dateMapper) {
		this.dateMapper = dateMapper;
	}
	
	public void setUseActualDate(boolean useActualDate){
		this.useActualDate = useActualDate;
	}

	@Override
	public String convertFrom(Date arg0, String arg1) {
		if (arg0 == null){
			return null;
		}
		return sdf.format(arg0);
	}

	@Override
	public Date convertTo(String arg0, Date arg1) {
		Date date = null;
		try {
			if (null == arg0) {
				if (useActualDate) {
					date = dateMapper.getActualDate();
				}
			}
			else {
				date = sdf.parse(arg0);
			}
		} catch (ParseException e) {
			logger.error("Conversion String->Date a �t� �chou�", e);
		}
		return date;
	}

	public void afterPropertiesSet() throws Exception {
		sdf = new SimpleDateFormat(formatDate);
	}

}
