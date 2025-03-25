$(document).ready(function () {
  cardAnimation();
  // Intersection observer functionality
  function cardAnimation() {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("show");
        }
      });
    });
    const hiddenElements = document.querySelectorAll(
      ".oc-card-left, .oc-card-right, .ao-img, .ao-subtitle, .ao-title, .ao-text, .ao-button, .ao-paper-items, .of-boxes, .organic-product-list"
    );
    hiddenElements.forEach((el) => observer.observe(el));
  }
  
  $(".explore-more").on("click", function () {
    $("html, body").animate(
      {
        scrollTop: $("#products").offset().top,
      },
      600
    );
  });
});
