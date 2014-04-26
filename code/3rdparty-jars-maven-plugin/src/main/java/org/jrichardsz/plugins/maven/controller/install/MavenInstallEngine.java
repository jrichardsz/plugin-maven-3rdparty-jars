package org.jrichardsz.plugins.maven.controller.install;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.maven.plugin.logging.Log;
import org.jrichardsz.plugins.maven.MainMojo;
import org.jrichardsz.plugins.maven.common.ParameterValidation;

public class MavenInstallEngine {
	
	private Log log;
	
	private MainMojo mojo; 
	
	public MavenInstallEngine(Log log, MainMojo mojo) {
		super();
		this.log = log;
		this.mojo = mojo;
	}



	public void execute(String command) throws Exception{
		
		executeFirstValidation();
		
		try {
			CommandLine cmdLine = CommandLine.parse(command);
			DefaultExecutor executor = new DefaultExecutor();
			int exitValue = executor.execute(cmdLine);
			log.info("Exit value of execution command : "+exitValue);
		} catch (IOException e) {
			throw new Exception("Error when try to execute this command : "+command,e);
		}
	}
	
	public void executeFirstValidation() throws Exception{
		
		ParameterValidation parameterValidation= new ParameterValidation();
		
		boolean isDependenciesVliad = parameterValidation.validateCollection("dependecies", mojo.getDependencies(), true, false);
		
		if(!isDependenciesVliad){
			throw new Exception(parameterValidation.getMessageError());
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		
		String command = "cmd /C mvn -f C:\\VerizonTasks\\000-ecomerce\\subtask-025-sonar\\ws\\001\\test-plugin\\pom.xml org.jrichardsz.plugins.maven:3rdparty-jars-maven-plugin:1.0.0:execute";
		execToString3(command);
	}
	
	public static void execToString3(String commandStr) throws ExecuteException, IOException {
		CommandLine cmdLine = CommandLine.parse(commandStr);
		DefaultExecutor executor = new DefaultExecutor();
		int exitValue = executor.execute(cmdLine);
		System.out.println("exitValue:"+exitValue);
	}


	public static void execToString1(String commandStr) throws ExecuteException, IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PumpStreamHandler ps = new PumpStreamHandler(os);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		CommandLine commandLine = CommandLine.parse(commandStr);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(1000000);
		Executor executor = new DefaultExecutor();
		executor.setWatchdog(watchdog);
		executor.setStreamHandler(ps);
		executor.execute(commandLine, resultHandler);
		Reader reader = new InputStreamReader(new ByteArrayInputStream(os.toByteArray()));
		BufferedReader r = new BufferedReader(reader);
		String tmp = null;
		while ((tmp = r.readLine()) != null) {
			System.out.println(tmp);
		}

	}
	
	public static String execToString2(String command) throws Exception {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    CommandLine commandline = CommandLine.parse(command);
	    DefaultExecutor exec = new DefaultExecutor();
	    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
	    exec.setStreamHandler(streamHandler);
	    exec.execute(commandline);
	    return(outputStream.toString());
	}

}
