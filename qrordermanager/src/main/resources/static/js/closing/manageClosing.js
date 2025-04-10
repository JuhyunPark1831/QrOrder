$(function () {

    /* 날짜, 시간 선택 UI 처리 */
    const today = new Date();
    const roundedTime = getNextRoundedTime(10);

    const datePickerOptions = {
        restrictions: {
            minDate: today
        },
        display: {
            components: {
                calendar: true,
                date: true,
                month: true,
                year: true,
                decades: true,
            }
        },
        defaultDate: new Date(),
        localization: {
            format: 'yyyy-MM-dd'
        }
    };

    const timePickerOptions = {
        restrictions: {
            minDate: today
        },
        display: {
            components: {
                calendar: true,
                date: true,
                month: true,
                year: true,
                decades: true,
            }
        },
        defaultDate: new Date(),
        localization: {
            format: 'yyyy-MM-dd'
        }
    };

    new tempusDominus.TempusDominus($('#clStartDate')[0], datePickerOptions);
    new tempusDominus.TempusDominus($('#clEndDate')[0], datePickerOptions);

    new tempusDominus.TempusDominus($('#clStartTime')[0],timePickerOptions);
    new tempusDominus.TempusDominus($('#clEndTime')[0],timePickerOptions);

    /* 날짜, 시간 선택 UI 처리 종료 */

    $("#open-create-btn").on("click", function () {
        $("#create-div").removeClass("d-none");
        $(this).addClass("d-none");
    });
    $("#close-create-btn").on("click", function () {
        closeCreateDiv();
    });
});

function getNextRoundedTime(stepMinutes) {
    const now = new Date();
    const ms = 1000 * 60 * stepMinutes;
    return new Date(Math.ceil(now.getTime() / ms) * ms);
}

function closeCreateDiv() {
    $("#create-div").addClass("d-none");
    $("#open-create-btn").removeClass("d-none");
}