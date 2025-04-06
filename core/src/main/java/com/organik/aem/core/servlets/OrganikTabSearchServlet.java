package com.organik.aem.core.servlets;

import java.io.IOException;
import java.util.*;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organik.aem.core.utils.OrganikTabUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.osgi.framework.Constants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Component(service = Servlet.class, immediate = true, property = {
        Constants.SERVICE_DESCRIPTION + "=Organik Tabs Search Servlet",
        ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES
                + "=/apps/organik-aem-website/components/content/organik-tabs",
        ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=json",
        ServletResolverConstants.SLING_SERVLET_SELECTORS + "=organikTabSearch",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET })

public class OrganikTabSearchServlet extends SlingAllMethodsServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(OrganikTabSearchServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
            throws IOException {
        String rootPath = request.getRequestParameter("searchRootPath") != null
                ? request.getRequestParameter("searchRootPath").getString()
                : StringUtils.EMPTY;
        String searchTags = request.getRequestParameter("searchCategory") != null
                ? request.getRequestParameter("searchCategory").getString()
                : StringUtils.EMPTY;
        
        String[] searchTagsArray = searchTags.trim().split(",");

        final Session session = request.getResourceResolver().adaptTo(Session.class);
        final QueryBuilder queryBuilder = request.getResourceResolver().adaptTo(QueryBuilder.class);
        
        Map<String, String> map = new HashMap<>();
        map.put("type", "dam:Asset");
        map.put("path", rootPath);
        map.put("1_property", "jcr:content/@contentFragment");
        map.put("1_property.value", "true");

        if(!ArrayUtils.isEmpty(searchTagsArray) && !searchTagsArray[0].isEmpty()) {
            map.put("2_property", "jcr:content/data/master/cq:tags");

            for (int i = 1; i <= searchTagsArray.length; i++) {
                map.put("2_property.".concat(Integer.toString(i)).concat("_value"), searchTagsArray[i - 1]);
            }
        }
        
        map.put("p.limit", "-1");

        Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
        SearchResult searchResult = query.getResult();
        List<Map<String, String>> outputList = new ArrayList<>();

        for (Hit hit : searchResult.getHits()) {
            Map<String, String> hitMap;
            try {
                Resource hitResource = hit.getResource();
                hitMap = OrganikTabUtils.getOrganikTabData(hitResource, request);
                if (!hitMap.isEmpty()) {
                    outputList.add(hitMap);
                }
            } catch (RepositoryException e) {
                log.error("Repository exception: {}", e.getMessage());
            }
        }

        outputList.sort(Comparator.comparing(product -> product.get("productName").toLowerCase()));
        
        ObjectMapper objectMapper = new ObjectMapper();
        String resultListJsonString = objectMapper.writeValueAsString(outputList);
        response.setContentType("application/json");
        response.getWriter().write(resultListJsonString);
    }
}