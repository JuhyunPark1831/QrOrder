$(function () {

    $("#ogMaxSelect-check").on('change', function () {
        if ($(this).is(':checked')) {
            $('#ogMaxSelect').prop('disabled', false);
        } else {
            $('#ogMaxSelect').val("").prop('disabled', true);
        }
    });
    $("#ogMinSelect-check").on('change', function () {
        if ($(this).is(':checked')) {
            $('#ogMinSelect').prop('disabled', false);
        } else {
            $('#ogMinSelect').val("").prop('disabled', true);
        }
    });
});