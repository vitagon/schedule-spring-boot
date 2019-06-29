package com.vitgon.schedule.projection;

public interface SchoolProjection {
	Integer getId();
	String getName();
	String getTranslation();
	String getUrl();
	
	Integer getMajor_id();
	String getMajor_name();
	String getMajor_translation();
	String getMajor_url();
}
