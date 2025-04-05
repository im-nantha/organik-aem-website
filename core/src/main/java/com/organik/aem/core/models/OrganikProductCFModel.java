package com.organik.aem.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OrganikProductCFModel {

    @ValueMapValue
    private String fragmentPath;

    @SlingObject
    private ResourceResolver resolver;

    private List<ProductData> productList;

    @PostConstruct
    protected void init() {
        productList = new ArrayList<>();

        String cfFolderPath = (fragmentPath != null && !fragmentPath.isEmpty())
                ? fragmentPath
                : "/content/dam/organik-aem-website/in/en-in/content-fragments";

        Resource folder = resolver.getResource(cfFolderPath);
        if (folder != null) {
            for (Resource child : folder.getChildren()) {
                ContentFragment cf = child.adaptTo(ContentFragment.class);
                if (cf != null) {
                    ProductData p = new ProductData();
                    p.name = getElementValue(cf, "productName");
                    p.category = getElementValue(cf, "productCategory");
                    p.image = getElementValue(cf, "imagePath");
                    p.starImage = getElementValue(cf, "starImagePath");
                    p.strikePrice = getElementValue(cf, "strikePrice");
                    p.actualPrice = getElementValue(cf, "actualPrice");
                    productList.add(p);
                }
            }
        }
    }

    private String getElementValue(ContentFragment cf, String name) {
        return cf.getElement(name) != null ? cf.getElement(name).getContent() : "";
    }

    public List<ProductData> getProductList() {
        return productList;
    }

    public static class ProductData {
        public String name;
        public String category;
        public String image;
        public String starImage;
        public String strikePrice;
        public String actualPrice;
    }
}