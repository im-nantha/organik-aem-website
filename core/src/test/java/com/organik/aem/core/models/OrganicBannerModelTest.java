// package com.organik.aem.core.models;

// import io.wcm.testing.mock.aem.junit5.AemContextExtension;
// import io.wcm.testing.mock.aem.junit5.AemContext;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;

// /**
//  * The test class for OrganicBannerModelTest
//  */
// @ExtendWith(AemContextExtension.class)
// public class OrganicBannerModelTest {

//     /**
//      * The AEM context
//      */
//     private final AemContext context = new AemContext();

//     /**
//      * The OrganicBannerModelTest instance
//      */
//     private OrganicBannerModelTest OrganicBannerModelTest;

//     /**
//      * Setup method to load resources and model before each test
//      */
//     @BeforeEach
//     void setUp() {
//         // Register model classes for AEM Context
//         context.addModelsForClasses(OrganicBannerModelTest.class, OrganicBannerMultifield.class);

//         // Load JSON data to simulate resource structure in AEM
//         context.load().json("/com/hero/aem/core/models/OrganicBannerModelTest.json", "/content");

//         // Adapt current resource to OrganicBannerModelTest
//         OrganicBannerModelTest = context.currentResource("/content/key-feature-controller").adaptTo(OrganicBannerModelTest.class);
//     }

//     /**
//      * Test method for 'getTabList' method
//      */
//     @Test
//     void testGetTabList() {
//         /*List<OrganicBannerMultifield> tabList = OrganicBannerModelTest.getTabList();

//         // Check if the list is not null and not empty
//         assertNotNull(tabList);
//         assertFalse(tabList.isEmpty());

//         // Check the first item in the list
//         assertEquals("Feature 1", tabList.get(0).getTitle());
//         assertEquals("Description of Feature 1", tabList.get(0).getId());
//         assertEquals("image1.jpg", tabList.get(0).getImageDetails().get(0).getTitle1());*/
//     }
// }

