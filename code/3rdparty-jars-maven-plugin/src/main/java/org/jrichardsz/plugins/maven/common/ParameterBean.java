package org.jrichardsz.plugins.maven.common;

public class ParameterBean {
	
	private String parameterName;
	private Object parameterValue;
	private boolean required;
	private boolean mustBeEmpty;
	private Class<?> parameterClass;
	
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Object getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public Class<?> getParameterClass() {
		return parameterClass;
	}
	public void setParameterClass(Class<?> parameterClass) {
		this.parameterClass = parameterClass;
	}
	public boolean mustBeEmpty() {
		return mustBeEmpty;
	}
	public void setMustBeEmpty(boolean mustBeEmpty) {
		this.mustBeEmpty = mustBeEmpty;
	}
}
