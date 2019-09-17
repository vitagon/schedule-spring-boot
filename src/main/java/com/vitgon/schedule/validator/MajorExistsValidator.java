package com.vitgon.schedule.validator;


import com.vitgon.schedule.annotation.validation.MajorExists;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.service.database.MajorService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class MajorExistsValidator implements ConstraintValidator<MajorExists, Integer> {

    private MajorService majorService;

    public MajorExistsValidator(MajorService majorService) {
        this.majorService = majorService;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        Optional<Major> majorOpt = majorService.findById(id);
        if (majorOpt.isPresent()) {
            return true;
        }
        return false;
    }
}
