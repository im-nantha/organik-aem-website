package com.organik.aem.core.services.osgiconfigs;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = APIConfigs.class, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true)
@Designate(ocd = APIConfiguration.class)
public class APIConfigs {
    
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(APIConfigs.class);

    // create map to store all properties
    private Map<String, Object> configProperties;

    @Activate
    public void activate(final APIConfiguration config, final Map<String, Object> properties) {
        this.configProperties = properties;
    }
    
    // Getters
    public Map<String, Object> getConfigProperties() {
        return configProperties;
    }
    
    public String getConfigProperty(String key) {
        return configProperties.get(key) != null ? configProperties.get(key).toString() : null;
    }

    @Modified
    public void modified(final APIConfiguration config, final Map<String, Object> properties) {
        activate(config, properties);
    }

    @Deactivate
    public void deactivate() {
        if(configProperties != null) {
            configProperties = null;
        }
    }
}
