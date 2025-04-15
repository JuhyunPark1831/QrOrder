$(function () {

    new tempusDominus.TempusDominus($('#clStartDate')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#clEndDate')[0], datePickerAfterTodayOptions);

    new tempusDominus.TempusDominus($('#clStartTime')[0],timePickerOptions);
    new tempusDominus.TempusDominus($('#clEndTime')[0],timePickerOptions);

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