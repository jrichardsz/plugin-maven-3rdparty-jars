package org.jrichardsz.plugins.maven.common;

import java.util.ArrayList;
import java.util.Collection;

public class ParameterValidation {

	private ArrayList<ParameterBean> parametersToValidate = null;
	private String messageError;

	public void addParameterToValidate(ParameterBean parameterBean) {

		if (parametersToValidate == null) {
			parametersToValidate = new ArrayList<ParameterBean>();
		}

		parametersToValidate.add(parameterBean);

	}

	public void addParameterToValidate(String parameterName,Object parameterValue,Class<?> parameterClass,boolean required,boolean mustBeEmpty) {

		ParameterBean parameterBean = new ParameterBean();
		parameterBean.setParameterName(parameterName);
		parameterBean.setParameterValue(parameterValue);
		parameterBean.setParameterClass(parameterClass);
		parameterBean.setRequired(required);
		parameterBean.setMustBeEmpty(mustBeEmpty);
		
		addParameterToValidate(parameterBean);

	}

	public boolean execute() throws Exception {

		for (ParameterBean parameterBean : parametersToValidate) {

			boolean validation = validate(parameterBean.getParameterName(),
					parameterBean.getParameterValue(),
					parameterBean.getParameterClass(),
					parameterBean.isRequired(),
					parameterBean.mustBeEmpty());
			
			if(!validation){
				return false;
			}

		}

		return true;

	}

	public boolean validate(String parameterName, Object parameterValue, Class<?> classe, boolean required, boolean mustBeEmpty) throws Exception {

		boolean result = false;

		if (parameterValue instanceof String) {
			return validateString(parameterName,(String)parameterValue, classe, required, mustBeEmpty);
		}else if (parameterValue instanceof Collection<?>) {
			return validateCollection(parameterName, (Collection<?>)parameterValue, required, mustBeEmpty);
		}

		return result;

	}
	
	public boolean validateString(String parameterName, String parameterValue, Class<?> classe, boolean required,boolean mustBeEmpty) throws Exception {
		
		if(!required){
			return true;
		}
		
		String parameterString = (String) parameterValue;

		if (parameterString == null) {			
			setMessageError(String.format("Parameter %s has a wrong value :%s", parameterName,parameterValue));
			return false;
			
		}else if(!mustBeEmpty && parameterString.equals("")){
			setMessageError(String.format("Parameter %s is empty.", parameterName));
			return false;			
		}
		
		return true;

		
	}
	
	public boolean validateCollection(String parameterName, Collection<?> parameterValue,boolean required ,boolean mustBeEmpty){
		
		if(!required){
			return true;
		}
		
		if(parameterValue==null){
			setMessageError(String.format("Parameter %s has a null value.", parameterName));
			return false;
			
		}else if(!mustBeEmpty && parameterValue.isEmpty()){
			setMessageError(String.format("Parameter %s is empty.", parameterName));
			return false;
		}
		
		return true;
	}
	
	public void setMessageError(String message){
		messageError = message;
	}

	public String getMessageError() {
		return messageError;
	}
}
