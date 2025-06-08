package com.organik.aem.core.services.osgiconfigs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Organik congfigurations osgi service")
public @interface APIConfiguration {
    
    @AttributeDefinition(name = "products api url", description = "provides url for products", type = AttributeType.STRING)
    String productAPIUrl() default "https://dummyjson.com/products";
}
