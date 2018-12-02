package com.vitgon.schedule.service;

import com.vitgon.schedule.model.Major;
import com.vitgon.schedule.service.base.Service;

public interface MajorService extends Service<Major, Integer>{
	Major findByTitle(String title);
}
