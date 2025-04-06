package com.organik.aem.core.models;

import java.util.Arrays;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OrganikTabMultifield {
    
    @ValueMapValue
    private String categoryLabel;

    @ValueMapValue
    private String[] categoryTag;

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public List<String> getCategoryTag() {
        return categoryTag != null ? Arrays.asList(categoryTag) : null;
    }
}