$(document).ready(function () {
    try {
        organikHeader();
    } catch (error) {
        console.log("component not found :",error);
    }
    function organikHeader() {
        console.log("jquery working");
    }
});