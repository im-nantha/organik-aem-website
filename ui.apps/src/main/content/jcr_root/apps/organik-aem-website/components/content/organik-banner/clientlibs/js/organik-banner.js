$(document).ready(function () {
  try {
    initiateBannerSlick();
  } catch (error) {
    console.log("component not found :", error);
  }
  function initiateBannerSlick() {
    $(".organic-banner").slick({
      dots: false,
      infinite: true,
      speed: 1500,
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: false,
      arrows: true,
      prevArrow: `<div class="arrows arrow-left"><p class="previous">&#8249;</p></div>`,
      nextArrow: `<div class="arrows arrow-right"><p class="next">&#8250;</p></div>`
    });
  }
});