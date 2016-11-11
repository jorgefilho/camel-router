package com.github.jorgefilho.camel.router;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.camel.spring.Main;


public class RouterApp {
    
	
	private Main main;
	
    public static void main(String[] args) throws Exception {
    	
    	
    	String contextUri = null;
    	
    	File currentDir = new File(".");
    	File[] files = currentDir.listFiles();
    	
    	for (File file : files) {
			if (file.getName().equals("camel-context.xml")) {
				addPath("camel-context.xml");
				contextUri = "camel-context.xml";
			}
		}
    	
    	
    	RouterApp router = new RouterApp();
    	router.boot(contextUri);
    }
    
    public void boot(String contextUri) throws Exception {
    	
    	main = new Main();
    	main.setFileApplicationContextUri(contextUri);
    	main.enableHangupSupport();
    	System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
    	
    	main.run();
 
    	
    }
    
    public static void addPath(String s) throws Exception {
        File f = new File(s);
        URL u = f.toURI().toURL();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u});
    }    

}