package org.jrichardsz.plugins.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.jrichardsz.plugins.maven.model.Dependency;
import org.apache.maven.plugin.logging.Log;

/**
 * Goal : Install 3rd Party jars to local repository
 * 
 * @goal execute
 * 
 * @phase process-sources
 */
public class MainMojo extends AbstractMojo {

	/**
	 * Dependencies to install.
	 *
	 * @parameter
	 */
	private  List<Dependency> dependencies;
	
	private Log log;

	public void execute() throws MojoExecutionException {
		
		initializeLogger();
		
		log.info("dependencies :"+dependencies);
		log.info("dependencies size:"+dependencies.size());
		
	}
	
	public void initializeLogger(){
		log = getLog();
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
	
	
}
