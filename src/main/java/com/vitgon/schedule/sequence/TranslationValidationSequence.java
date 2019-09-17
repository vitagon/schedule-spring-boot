package com.vitgon.schedule.sequence;

import com.vitgon.schedule.group.TranslationGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, TranslationGroup.class})
public interface TranslationValidationSequence {

}
