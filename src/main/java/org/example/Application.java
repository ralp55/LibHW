package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start of Lib Service");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        String basedir = new File(".").getAbsolutePath();
        tomcat.setBaseDir(basedir + "/target/tomcat");
        logger.info("Tomcat Port: " + 8080);
        logger.info("Tomcat Base Dir: " +  basedir + "/target/tomcat");

        String contextPath = "/";
        String docBase = new File("src/main/webapp").getAbsolutePath();

        tomcat.addWebapp(contextPath, docBase);

        logger.info("Application deployed at: http://localhost:8080" + contextPath);
        logger.info("API endpoint: http://localhost:8080/api/library");

        try {
            tomcat.start();
            logger.info("started successfully");
            tomcat.getServer().await();
        } catch (Exception e) {
            logger.error("Failed", e);
            throw e;
        }
    }
}