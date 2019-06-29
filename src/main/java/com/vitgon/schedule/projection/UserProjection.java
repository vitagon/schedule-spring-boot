package com.vitgon.schedule.projection;

public interface UserProjection {
	Integer getId();
	String getFirstname();
	String getLastname();
	String getMiddlename();
	
	String getKey_firstname();
	String getKey_lastname();
	String getKey_middlename();
}
