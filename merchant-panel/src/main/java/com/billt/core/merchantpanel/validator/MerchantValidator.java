package com.billt.core.merchantpanel.validator;


import com.billt.core.datasourcebase.entities.jpa.Merchant;
import com.billt.core.datasourcebase.services.MerchantService;
import com.billt.core.merchantpanel.Utils.EmailValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class MerchantValidator implements Validator {

    @Autowired
    private MerchantService merchantService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Merchant.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Merchant merchant = (Merchant) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        if (EmailValidatorService.isValid(merchant.getEmail())) {
            errors.rejectValue("email", "Email entered is not valid ");
        }
        if (merchantService.findByEmail(merchant.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (merchant.getPassword().length() < 8 || merchant.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!merchant.getPasswordConfirm().equals(merchant.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}