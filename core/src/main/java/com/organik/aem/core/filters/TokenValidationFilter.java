package com.organik.aem.core.filters;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.engine.EngineConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = Filter.class,
    property = {
        Constants.SERVICE_RANKING + ":Integer=1000",
        EngineConstants.SLING_FILTER_SCOPE + "=" + EngineConstants.FILTER_SCOPE_REQUEST
    }
)
public class TokenValidationFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TokenValidationFilter.class);

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof SlingHttpServletRequest) {
            SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
            String path = slingRequest.getRequestPathInfo().getResourcePath();
            String selector = slingRequest.getRequestPathInfo().getSelectorString();

            // Only apply filter for your servlet’s route (productsapi selector)
            if (path != null && path.startsWith("/content") && "productsapi".equals(selector)) {
                String tokenHeader = slingRequest.getHeader(TOKEN_HEADER);

                if (tokenHeader == null || !tokenHeader.startsWith(TOKEN_PREFIX)) {
                    log.warn("Missing or invalid Authorization header");
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }

                String token = tokenHeader.substring(TOKEN_PREFIX.length());

                if (!isValidToken(token)) {
                    log.warn("Invalid token received: {}", token);
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                    return;
                }

                log.info("Token validation passed for: {}", slingRequest.getRequestPathInfo().getResourcePath());
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        // Replace with real validation logic (JWT, session lookup, etc.)
        return "valid-token".equals(token);
    }

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void destroy() { }
}