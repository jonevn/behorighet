package se.evelonn.behorighet.domain.model;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

public abstract class BaseEntity implements Serializable {

	public void validera() {
		Set<ConstraintViolation<Object>> violations = Validation.buildDefaultValidatorFactory()
				.getValidator()
				.validate(this);
		if (violations.isEmpty()) {
			return;
		}
		throw new IllegalArgumentException(violations.stream()
				.map(v -> v.getPropertyPath() + " " + v.getMessage())
				.collect(Collectors.joining(", ")));
	}
}