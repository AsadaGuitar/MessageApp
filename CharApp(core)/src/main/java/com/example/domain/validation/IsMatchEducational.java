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

import com.example.domain.enums.Educational;

@Documented
@Constraint(validatedBy= {IsMatchEducationalValidator.class})
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsMatchEducational {

	String enumType() ;
	String param();
	
	String message() default "年齢と現職が合致しません。";
	Class<?>[] groups() default{};
	Class<? extends Payload>[]  payload() default{};
}

class IsMatchEducationalValidator implements ConstraintValidator<IsMatchEducational, Object>{
	
	private String enumType;
	private String param;
	
	@Override
	public void initialize(IsMatchEducational annotation) {
		this.enumType = annotation.enumType();
		this.param = annotation.param();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		/** 
		 * プロパティの名前から値を取得
		 *  getPropertyType は型を取得
		 */
		int age = (int)beanWrapper.getPropertyValue(param);
		Educational educational = (Educational)beanWrapper.getPropertyValue(enumType);
		
		if(educational == null)
			return false;
		
		int start = educational.getStart();
		int end = educational.getEnd();
		
		
		if(start <= age && age <= end) 
			return true;

		return false;
	}
}
