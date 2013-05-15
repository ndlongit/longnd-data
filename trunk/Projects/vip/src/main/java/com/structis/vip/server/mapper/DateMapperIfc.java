package com.structis.vip.server.mapper;

import java.util.Date;

public interface DateMapperIfc {
	
	public Date getActualDate();
	
	public Date getActualDate(String format);
	
	public String getActualStringDate();
	
	public String createStringFromDate(Date date);
	
	public Date createDateFromString(String string);
	
	public Date getActualMonth();

}
