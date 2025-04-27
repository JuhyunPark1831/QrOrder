let currentPage = 1;
let isLoading = false;

$(function () {

    $(window).on("scroll", function() {
        if (!isLoading && $(window).scrollTop() + $(window).height() >= $(document).height()) {
            isLoading = true;
            showLoadingIndicator();
            currentPage++;
            scrollDown(currentPage);
        }
    });
});

function showLoadingIndicator() {
    $("#loading-indicator").show();
}

function hideLoadingIndicator() {
    $("#loading-indicator").hide();
}