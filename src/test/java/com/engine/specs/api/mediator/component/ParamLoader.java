package com.engine.specs.api.mediator.component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParamLoader {
	public Properties properties() {
		Properties testParams = new Properties();

		try {
			String resourcePath = String.valueOf(System.getProperty("envProperties"));
			InputStream file = new FileInputStream(resourcePath);
			testParams.load(file);
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return testParams;
	}
}
