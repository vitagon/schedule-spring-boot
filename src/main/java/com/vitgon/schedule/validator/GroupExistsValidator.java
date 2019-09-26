package com.vitgon.schedule.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.annotation.validation.GroupExists;
import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.service.database.GroupService;

@Component
public class GroupExistsValidator implements ConstraintValidator<GroupExists, Integer> {
	
	private GroupService groupService;

	public GroupExistsValidator(GroupService groupService) {
		super();
		this.groupService = groupService;
	}

	@Override
	public boolean isValid(Integer id, ConstraintValidatorContext context) {
		Optional<Group> groupOpt = groupService.findById(id);
		if (groupOpt.isPresent()) {
			return true;
		}
		return false;
	}
	
}
