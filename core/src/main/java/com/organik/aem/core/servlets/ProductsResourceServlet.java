package com.organik.aem.core.servlets;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.organik.aem.core.constants.OrganikConstants;
import com.organik.aem.core.osgiconfigs.APIConfigs;
import com.organik.aem.core.utils.StaticWrapper;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

@Component(service = Servlet.class, property = {
    Constants.SERVICE_DESCRIPTION + "= Dummy Products API Resource Servlet",
    ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=hero/components/page",
    ServletResolverConstants.SLING_SERVLET_SELECTORS + "=productsapi",
    ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=json",
    ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
})

public class ProductsResourceServlet extends SlingSafeMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(ProductsResourceServlet.class);

    transient StaticWrapper httpGetWrapper = new StaticWrapper();

    @Reference
    transient APIConfigs apiConfigurations;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        try (CloseableHttpClient httpClient = httpGetWrapper.createDefaultHttpClient()) {

            String apiUrl = apiConfigurations.getConfigProperty(OrganikConstants.PRODUCT_API_URL);
            HttpGet httpGet = httpGetWrapper.getHttpGet(apiUrl);
            httpGet.setHeader("Accept", "application/json");

            try (CloseableHttpResponse apiResponse = httpClient.execute(httpGet)) {

                int statusCode = apiResponse.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(apiResponse.getEntity());

                if (statusCode == 200) {
                    JsonElement json = JsonParser.parseString(responseString);
                    response.setContentType("application/json");
                    response.getWriter().write(json.toString());
                } else {
                    log.error("API call failed with status code: {}", statusCode);
                    response.sendError(500, "Failed to fetch products from API.");
                }
            }

        } catch (Exception e) {
            log.error("Exception while calling external API", e);
            response.sendError(500, "Error occurred while processing your request.");
        }
    }
}
