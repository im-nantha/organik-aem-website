package com.organik.aem.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OrganicBannerMultifield {

    @Inject
    private String bannerTitle;

    @Inject
    private String bannerMainTitle;

    @Inject
    private String bannerPathLabel;

    @Inject
    private String bannerImagePath;

    public String getBannerTitle() {
        return bannerTitle;
    }

    public String getBannerMainTitle() {
        return bannerMainTitle;
    }

    public String getBannerPathLabel() {
        return bannerPathLabel;
    }

    public String getBannerImagePath() {
        return bannerImagePath;
    }
}