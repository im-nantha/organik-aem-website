$(document).ready(function () {
  
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
  // load more functionality
  let contentItems = $(".organic-product-list");
  const loadMoreButton = $("#load-more-button");
  let initialCount = 4;
  let loadedCount = initialCount;

  // Show the initial set of cards
  contentItems.slice(0, initialCount).show();

  // Event listener for the Load More button
  loadMoreButton.on("click", function () {
    contentItems.slice(loadedCount, loadedCount + 4).show();
    loadedCount += 4;
    if (loadedCount >= contentItems.length) {
      loadMoreButton.addClass("d-none");
    }
    // Hide the button if all items are shown
    if (loadedCount >= contentItems.length) {
      loadMoreButton.hide();
    }
  });
  $(".explore-more").on("click", function () {
    $("html, body").animate(
      {
        scrollTop: $("#products").offset().top,
      },
      600
    );
  });
});
