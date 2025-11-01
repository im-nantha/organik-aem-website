package com.organik.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OrganicBannerModel {

    @Inject
    private String bannerDescription;

    @ChildResource
    private List<OrganicBannerMultifield> bannerList;

    public String getBannerDescription() {
        return bannerDescription;
    }

    public List<OrganicBannerMultifield> getBannerList() {
        return bannerList != null ? bannerList : Collections.emptyList();
    }
}
