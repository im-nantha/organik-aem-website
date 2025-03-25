$(document).ready(function () {
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
});
