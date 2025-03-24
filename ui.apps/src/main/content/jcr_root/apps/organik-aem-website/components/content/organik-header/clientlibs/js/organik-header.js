$(document).ready(function () {
  try {
    organikHeader();
  } catch (error) {
    console.log("component not found :", error);
  }
  function organikHeader() {
    // mobile view hamburger
    if ($(window).width() < 768) {
      let menu = $(".organic-navbar-content");
      let $menuItems = $(".organic-nav-wrap");
      let hamburger = $(".hamburger");
      let closeIcon = $(".closeIcon");
      let menuIcon = $(".menuIcon");

      function toggleMenu() {
        if (menu.hasClass("showMenu")) {
          menu.removeClass("showMenu");
          closeIcon.hide();
          menuIcon.show();
          document.body.style.overflow = "auto";
        } else {
          menu.addClass("showMenu");
          closeIcon.show();
          menuIcon.hide();
          document.body.style.overflow = "hidden";
        }
      }
      hamburger.on("click", toggleMenu);
      $menuItems.each(function () {
        $(this).on("click", toggleMenu);
      });
    }
  }
});