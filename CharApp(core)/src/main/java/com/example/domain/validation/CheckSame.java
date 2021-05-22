package com.example.domain.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@Documented
@Constraint(validatedBy= {CheckSameValidator.class})
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSame {

	String password ();
	String check();
	
	String message() default "パスワードと確認用パスワードが合致しません。";
	Class<?>[] groups() default{};
	Class<? extends Payload>[]  payload() default{};
}

class CheckSameValidator implements ConstraintValidator<CheckSame, Object>{

	private String password;
	private String check;
	
	@Override
	public void initialize(CheckSame annotation) {
		this.password = annotation.password();
		this.check = annotation.check();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String passwordParam = (String) beanWrapper.getPropertyValue(password);
		String checkParam = (String) beanWrapper.getPropertyValue(check);
		
		if(passwordParam.equals(checkParam))
			return true;
		
		return false;
	}	
}
