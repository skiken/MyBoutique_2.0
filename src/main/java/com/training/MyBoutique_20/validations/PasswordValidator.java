package com.training.MyBoutique_20.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.training.MyBoutique_20.dto.CustomerDto;

public class PasswordValidator  implements ConstraintValidator<ValidPassword, Object> { 
    
   @Override
   public void initialize(ValidPassword validPassword) {       
   }
   

   @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	   CustomerDto customer = (CustomerDto) value;
       return customer.getPassword().equals(customer.getMatchingPassword());   
}     

}
