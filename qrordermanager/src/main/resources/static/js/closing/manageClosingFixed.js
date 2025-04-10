$(function () {
    $("#open-create-btn").on("click", function () {
        $("#create-div").removeClass("d-none");
        $(this).addClass("d-none");
    });
    $("#close-create-btn").on("click", function () {
        closeCreateDiv();
    });
});

function closeCreateDiv() {
    $("#create-div").addClass("d-none");
    $("#open-create-btn").removeClass("d-none");
}