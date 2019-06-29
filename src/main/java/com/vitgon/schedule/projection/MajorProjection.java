package com.vitgon.schedule.projection;

import com.vitgon.schedule.dto.DegreeEnum;

public interface MajorProjection {
	Integer getId();
	String getName();
	String getUrl();
	Integer getDuration();
	DegreeEnum getDegree();
	String getTranslation();
}
