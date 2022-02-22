package io.pera.bookstore.model.exception;

import lombok.Getter;

@Getter
public class BusinessValidationException extends RuntimeException {

    private final BusinessValidationRule validationRule;

    public BusinessValidationException(BusinessValidationRule validationRule) {
        super(validationRule.getMessage());
        this.validationRule = validationRule;
    }

    public BusinessValidationException(BusinessValidationRule validationRule, Object... params) {
        super(String.format(validationRule.getMessage(), params));
        this.validationRule = validationRule;
    }

}
