$(document).ready(function () {

     $("#leftMenu-icon").on("click", function () {
        let $this = $(this);
        let currentClass = $this.attr("class");

        if (currentClass.includes("active")) {
            $this.attr("class", currentClass.replace("active", "").trim());
            closeMenu();
        } else {
            $this.attr("class", currentClass + " active");
            openMenu();
        }
    })

    $("#darkArea").on("click", function () {
        let $leftMenuIcon = $("#leftMenu-icon");
        $leftMenuIcon.attr("class", $leftMenuIcon.attr("class").replace("active", "").trim());
        closeMenu();
    })
});

function openMenu () {
    $("#leftMenu").addClass("active");
    $("#darkArea").show();
}

function closeMenu () {
    $("#leftMenu").removeClass("active");
    $("#darkArea").hide();
}