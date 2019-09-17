package com.vitgon.schedule.dto;


import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.MajorExists;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class MajorTranslationDto {


    @MajorExists
    private Integer majorId;
    @LocaleExists
    private Integer localeId;
    @Size(min = 3, max = 50, message = "{Size.default}")
    private String translation;

    public MajorTranslationDto() {
    }

    public MajorTranslationDto(Integer majorId, Integer localeId, String translation) {
        this.majorId = majorId;
        this.localeId = localeId;
        this.translation = translation;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getLocaleId() {
        return localeId;
    }

    public void setLocaleId(Integer localeId) {
        this.localeId = localeId;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
