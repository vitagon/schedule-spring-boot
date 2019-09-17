package com.vitgon.schedule.controller.rest;

import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.annotation.validation.MajorExists;
import com.vitgon.schedule.controller.rest.advice.FieldValidationException;
import com.vitgon.schedule.dto.MajorTranslationDto;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.service.database.translation.MajorTranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RequestMapping("/api/translations")
@RestController
public class MajorTranslationRestController {

    private MajorTranslationService majorTranslationService;

    public MajorTranslationRestController(MajorTranslationService majorTranslationService) {
        this.majorTranslationService = majorTranslationService;
    }

    @GetMapping("/majors/{majorId}/locales/{localeId}")
    public ResponseEntity<?> getMajorTranslation(@PathVariable("majorId") @MajorExists Integer majorId,
                                                 @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {

        Optional<MajorTranslation> majorTranslationOpt = majorTranslationService.findByLocaleIdAndMajorId(localeId, majorId);
        if (!majorTranslationOpt.isPresent()) {
            throw new FieldValidationException("localeId", "translation.notFound");
        }
        MajorTranslationDto majorTranslationDto = new MajorTranslationDto(
                majorId,
                localeId,
                majorTranslationOpt.get().getTranslation());
        return ResponseEntity.ok(majorTranslationDto);
    }

    @PostMapping("/majors/{majorId}/locales/{localeId}")
    public ResponseEntity<?> addMajorTranslation(@RequestBody @Valid MajorTranslationDto majorTranslationDto) throws FieldValidationException {
        Optional<MajorTranslation> majorTranslationOpt = majorTranslationService
                .findByLocaleIdAndMajorId(
                        majorTranslationDto.getLocaleId(),
                        majorTranslationDto.getMajorId()
                );

        if (!majorTranslationOpt.isPresent()) {
            majorTranslationService.save(majorTranslationDto.getMajorId(), majorTranslationDto.getLocaleId(), majorTranslationDto.getTranslation());
        } else {
            throw new FieldValidationException("translation", "Duplicate.translation");
        }

        return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully added translation!"));
    }

    @PutMapping("/majors/{majorId}/locales/{localeId}")
    public ResponseEntity<?> editMajorTranslation(@RequestBody @Validated MajorTranslationDto majorTranslationDto) throws FieldValidationException {

        Optional<MajorTranslation> majorTranslationOpt = majorTranslationService
                .findByLocaleIdAndMajorId(
                        majorTranslationDto.getLocaleId(),
                        majorTranslationDto.getMajorId()
                );

        if (majorTranslationOpt.isPresent()) {
            MajorTranslation majorTranslation = majorTranslationOpt.get();
            majorTranslation.setTranslation(majorTranslationDto.getTranslation());
            majorTranslationService.update(majorTranslation);
            return ResponseEntity.ok().body(null);
        }

        throw new FieldValidationException("localeId", "translation.notFound");
    }

    @DeleteMapping("/majors/{majorId}/locales/{localeId}")
    public ResponseEntity<?> removeMajorTranslation(@PathVariable("majorId") @MajorExists Integer majorId,
                                                    @PathVariable("localeId") @LocaleExists Integer localeId) throws FieldValidationException {
        Optional<MajorTranslation> majorTranslationOpt = majorTranslationService
                .findByLocaleIdAndMajorId(localeId, majorId);

        if (majorTranslationOpt.isPresent()) {
            majorTranslationService.deleteByMajorIdAndLocaleId(majorId, localeId);
            return ResponseEntity.ok().body(null);
        }

        throw new FieldValidationException("localeId", "translation.notFound");
    }
}
