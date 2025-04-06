$(document).ready(function () {
  function initializeCarousel() {

    const carouselDotIndicatorsElement = $(".accessories-carousel-indicators").find(".dash-container");

    $(".accessories-container").find(".accessories-product-cards").slick({
        useTransform: true,
        autoplay: false,
        cssEase: "ease-out",
        dots: true,
        infinite: false,
        slidesToShow: 4,
        slidesToScroll: 4,
        prevArrow: $(".accessories-slick-prev"),
        nextArrow: $(".accessories-slick-next"),
        dotsClass: "carousel-indicators accessories-indicator",
        appendDots: carouselDotIndicatorsElement,
        responsive: [
          {
            breakpoint: 1250,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 3,
            },
          },
          {
            breakpoint: 767,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
              arrows: false,
              variableWidth: true,
            },
          },
        ],
    });
  }

  function fetchAndDisplayProducts(category) {

    var element = $(".accessories-product-cards");
    const resourcePath = element.attr("data-resource-path");
    const searchPath = element.attr("data-search-path");
    var reqLink;

    if (category === "all") {
      reqLink = `${resourcePath}.organikTabSearch.json?searchRootPath=${searchPath}`;
    } else {
      reqLink = `${resourcePath}.organikTabSearch.json?searchRootPath=${searchPath}&searchCategory=${category}`;
    }

    $.ajax({
      url: reqLink,
      method: "GET",
      dataType: "json",
      success: function (data) {

        if ($(".accessories-product-cards").hasClass("slick-initialized")) {
          $(".accessories-product-cards").slick("unslick");
        }

        $(".accessories-product-cards").empty();

        data.sort((a, b) => a.productName.localeCompare(b.productName));

        if (data.length > 0) {
          data.forEach(function (product) {
            var productHtml = `
            <a href="#" class="accessories-card-block">
              <div class="accessories-img-container">
                <img class="product-img" loading="lazy" src="${product.productImagePath}" alt="${product.productName}"/>
              </div>
              <div class="accessories-content-wrapper">
                <div class="accessories-content">
                  <p class="product-name">${product.productName}</p>
                  <p class="price ${product.isAvailableNow=='true' ? 'green':'red'}">₹${product.productAmount.toLocaleString()}</p>
                </div>
                <div class="view-product-details">
                  <div class="upword-arrow1 cta-arrow">
                    <img
                      class="arrow-img"
                      loading="lazy"
                      src="/content/dam/organik-aem-website/in/en-in/product-images/arrow.svg"
                      alt="CTA Arrow"
                    />
                  </div>
                  <div class="upword-arrow2 cta-arrow">
                    <img
                      class="arrow-img"
                      loading="lazy"
                      src="/content/dam/organik-aem-website/in/en-in/product-images/arrow.svg"
                      alt="CTA Arrow"
                    />
                  </div>
                </div>
              </div>
            </a>`;

            $(".accessories-product-cards").append(productHtml);
          });
        }

        var isMobile = window.matchMedia("(max-width: 767px)").matches;
        var isTablet = window.matchMedia("(min-width: 768px) and (max-width: 1250px)").matches;

        if (
          (isMobile && data.length > 1) ||
          (isTablet && data.length > 3) ||
          (!isMobile && !isTablet && data.length > 4)
        ) {
          initializeCarousel();
          $(".accessories-slick-prev, .accessories-slick-next").show();
        } else if (window.innerWidth < 1862 && data.length == 4) {
          initializeCarousel();
          $(".accessories-slick-prev, .accessories-slick-next").hide();
        } else {
          $(".accessories-slick-prev, .accessories-slick-next").hide();
        }
      },

      error: function (xhr, status, error) {
        console.error("Error fetching data:", error);
        $(".accessories-product-cards").html("<p>Error fetching products. Please try again later.</p>");
      },

    });
  }

  let firstCategory = $(".accessories-container .nav-link").first().data("category");

  if (firstCategory) {
    fetchAndDisplayProducts(firstCategory);
  } else {
    fetchAndDisplayProducts("all");
  }

  $(".accessories-container .nav-link").on("click", function () {
    var category = $(this).data("category");

    $(".accessories-container .nav-link").removeClass("active");
    $(this).addClass("active");
    fetchAndDisplayProducts(category);

  });
  
});
