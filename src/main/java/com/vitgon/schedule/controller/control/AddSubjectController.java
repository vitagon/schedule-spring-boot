package com.vitgon.schedule.controller.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.Subject;
import com.vitgon.schedule.model.request.AddSubjectRequest;
import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.service.LocaleService;
import com.vitgon.schedule.service.SubjectService;
import com.vitgon.schedule.service.translation.SubjectTranslationService;

@Controller
public class AddSubjectController {
	
	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SubjectTranslationService subjectTranslService;

	@PostMapping("/control/subject/add")
	public ModelAndView addSubject(@ModelAttribute AddSubjectRequest addSubjectRequestModel, ModelMap modelMap) {
		Subject subject = subjectService.save(new Subject());
		
		Locale enLocale = localeService.findByCode("en");
		Locale ruLocale = localeService.findByCode("ru");
		
		subjectTranslService.save(new SubjectTranslation(
				subject, enLocale, addSubjectRequestModel.getSubjectTitleEn()
		));
		subjectTranslService.save(new SubjectTranslation(
				subject, ruLocale, addSubjectRequestModel.getSubjectTitleRu()
		));
		
		return new ModelAndView("redirect:/control", modelMap);
	}
}
