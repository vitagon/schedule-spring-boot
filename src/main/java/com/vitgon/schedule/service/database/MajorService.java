package com.vitgon.schedule.service.database;

import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.base.Service;

public interface MajorService extends Service<Major, Integer>{
	Major findByTitle(String title);
	Major findByUrl(String url);
}
