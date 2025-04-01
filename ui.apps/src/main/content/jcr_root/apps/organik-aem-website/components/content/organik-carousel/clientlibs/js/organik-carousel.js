$(document).ready(function () {
  try {
    initiateCarousel();
  } catch (error) {
    console.log("component not found :", error);
  }
  function initiateCarousel() {
    const genericBanner = $('.generic-banner-container');
    const slickContainer = $('.generic-banner-carousel');

    if (genericBanner.length === 0) {
      return;
    }

    $(".generic-banner-video-container").each(function() {
      const videoContainer = $(this);
      
      videoContainer.find(".volume-button").on("click", function () {
        let currentElement = videoContainer.find(".generic-banner-video-web")[0];
        
        if ($(window).width() < 768) {
          currentElement = videoContainer.find(".generic-banner-video-mobile")[0];
        }
  
        if (currentElement && currentElement.muted) {
          currentElement.muted = false;
          $(this).find(".volume-on").removeClass("d-none").addClass("d-block");
          $(this).find(".volume-off").removeClass("d-block").addClass("d-none");
        } else {
          currentElement.muted = true;
          $(this).find(".volume-on").removeClass("d-block").addClass("d-none");
          $(this).find(".volume-off").removeClass("d-none").addClass("d-block");
        }
      }); 
    });

    let speedval = genericBanner.find('.generic-banner-wrapper').attr("data-interval");
    speedval = speedval ? parseInt(speedval) : 5000;

    const slickSettings = {
      dots: true,
      infinite: false,
      speed: 1000,
      autoplay: true,
      autoplaySpeed: speedval,
      slidesToShow: 1,
      slidesToScroll: 1,
      swipe: true,
      arrows: false,
      adaptiveHeight: true,
      appendDots: genericBanner.find('.generic-banner-indicator-wrapper .indicator-position'),
    };

    if (slickContainer.children().length === 1) {
      slickContainer.find('.generic-banner-indicator-wrapper').hide();
    }

    slickContainer.slick(slickSettings);
  }
});