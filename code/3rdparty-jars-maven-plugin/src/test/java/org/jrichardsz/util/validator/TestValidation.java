package org.jrichardsz.util.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.jrichardsz.plugins.maven.common.ParameterValidation;
import org.jrichardsz.plugins.maven.model.Dependency;
import org.junit.Test;

public class TestValidation {

	@Test
	public void testCollectionRequiredAndNoEmpty() {
		
		ArrayList<Dependency> arrayList = new ArrayList<Dependency>();
		
		Dependency d1 =  new Dependency();
		Dependency d2 =  new Dependency();
		
		arrayList.add(d1);
		arrayList.add(d2);
		
		ParameterValidation parameterValidation = new ParameterValidation();
		
		boolean isValid = parameterValidation.validateCollection("dependencias", arrayList,true,false);
		
		assertTrue("This collection is not null and not empty.",isValid);
	}
	
	@Test
	public void tesNullCollectionRequiredAndNoEmpty() {
		
		ArrayList<Dependency> arrayList = null;
		
		ParameterValidation parameterValidation = new ParameterValidation();
		
		boolean isValid = parameterValidation.validateCollection("dependencias", arrayList,true,false);
		
		assertFalse("This collection is null",isValid);
	}
	
	@Test
	public void testCollectionRequiredAndEmpty() {
		
		ArrayList<Dependency> arrayList = new ArrayList<Dependency>();
		
		Dependency d1 =  new Dependency();
		Dependency d2 =  new Dependency();
		
		arrayList.add(d1);
		arrayList.add(d2);
		
		ParameterValidation parameterValidation = new ParameterValidation();
		
		boolean isValid = parameterValidation.validateCollection("dependencias", arrayList,true,true);
		
		assertTrue("This collection is not null and empty.",isValid);
	}
	
	@Test
	public void testNullCollectionRequiredAndEmpty() {
		
		ArrayList<Dependency> arrayList = null;
		
		ParameterValidation parameterValidation = new ParameterValidation();
		
		boolean isValid = parameterValidation.validateCollection("dependencias", arrayList,true,true);
		
		assertFalse("This collection is null.",isValid);
	}

}
