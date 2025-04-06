package com.organik.aem.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OrganikTabModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String subTitle;

    @ValueMapValue
    private String fragmentPath;

    @ChildResource
    private List<OrganikTabMultifield> organicTabs;

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getFragmentPath() {
        return fragmentPath;
    }

    public List<OrganikTabMultifield> getOrganicTabs() {
        return organicTabs;
    }

}