package org.doxla.graphflow.spring.config;

import org.apache.log4j.Logger;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class WebInitialiser implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final Logger LOG = Logger.getLogger(getClass());

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("WebInitialiser.initialize");
        CloudEnvironment env = new CloudEnvironment();
        if (env.getInstanceInfo() != null) {
            LOG.info("Detected cloud environment. Cloud API: " + env.getCloudApiUri());
            applicationContext.getEnvironment().setActiveProfiles("cloud");
        } else {
            LOG.info("Detected non cloud environment setting default profile");
            applicationContext.getEnvironment().setActiveProfiles("default");
        }
    }
}
