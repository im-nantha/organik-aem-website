$(document).ready(function () {
    try {
        organikFooter();
    } catch (error) {
        console.log("component not found :",error);
    }
    function organikFooter() {
        console.log("footer component");
    }
});