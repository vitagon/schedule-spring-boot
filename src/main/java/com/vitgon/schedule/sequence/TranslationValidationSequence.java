package com.vitgon.schedule.sequence;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.vitgon.schedule.group.TranslationGroup;

@GroupSequence({Default.class, TranslationGroup.class})
public interface TranslationValidationSequence {

}
