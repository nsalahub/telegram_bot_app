package com.gmail.salahub.nikolay.telegram.bot.application.controller.validator;

import com.gmail.salahub.nikolay.telegram.bot.application.repository.model.City;
import com.gmail.salahub.nikolay.telegram.bot.application.service.CityService;
import com.gmail.salahub.nikolay.telegram.bot.application.service.exception.NoResultCityServiceException;
import com.gmail.salahub.nikolay.telegram.bot.application.service.model.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.gmail.salahub.nikolay.telegram.bot.application.controller.validator.message.ValidationMessage.VALIDATION_EMPTY_DESCRIPTION_MESSAGE;
import static com.gmail.salahub.nikolay.telegram.bot.application.controller.validator.message.ValidationMessage.VALIDATION_EMPTY_TITLE_MESSAGE;
import static com.gmail.salahub.nikolay.telegram.bot.application.controller.validator.message.ValidationMessage.VALIDATION_TITLE_EXIST_MESSAGE;

@Component("cityValidator")
public class CityValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(CityValidator.class);

    private final CityService cityService;

    @Autowired
    public CityValidator(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "", VALIDATION_EMPTY_TITLE_MESSAGE);
        ValidationUtils.rejectIfEmpty(errors, "description", "", VALIDATION_EMPTY_DESCRIPTION_MESSAGE);

        CityDTO cityDTO = (CityDTO) o;

        try {
            cityService.findByTitle(cityDTO.getTitle());
            errors.rejectValue("title", "", VALIDATION_TITLE_EXIST_MESSAGE);
        } catch (NoResultCityServiceException e) {
            logger.info(cityDTO.getTitle() + "is valid you can add this city");
        }
    }
}
