package com.organik.aem.core.utils;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.*;

public class OrganikTabUtils {

    public static Map<String, String> getOrganikTabData(Resource hitResource, SlingHttpServletRequest request) {
        Map<String, String> hitMap = new HashMap<>();
        Resource jcrResource = hitResource.getChild("jcr:content/data/master");
        if (jcrResource != null) {
            ValueMap valueMap = jcrResource.getValueMap();
            hitMap.put("productName", valueMap.getOrDefault("productName", "").toString());
            hitMap.put("productImagePath", valueMap.getOrDefault("productImagePath", "").toString());
            hitMap.put("isAvailableNow", valueMap.getOrDefault("isAvailableNow", "").toString());
            hitMap.put("productAmount", valueMap.getOrDefault("productAmount", "").toString());
        }
        return hitMap;
    }
}

