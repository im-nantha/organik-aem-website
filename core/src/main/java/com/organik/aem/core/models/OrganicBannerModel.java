package com.organik.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;

@Model(
    adaptables = Resource.class,
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class OrganicBannerModel {

    @Inject
    private String name;

    @Inject
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
