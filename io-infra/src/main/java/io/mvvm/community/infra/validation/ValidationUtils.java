package io.mvvm.community.infra.validation;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtils {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * 参数校验
     *
     * @param paramObject 需要校验的参数
     */
    public static void validateObject(Object paramObject, Class<?>... groups) {
        Validator validator = VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<Object>> validateResult = validator.validate(paramObject, groups);
        if (CollectionUtils.isEmpty(validateResult)) {
            return;
        }

        String messages = validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        throw new ValidationException(messages);
    }

    private ValidationUtils() {
    }
}