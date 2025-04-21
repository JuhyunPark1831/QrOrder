$(function () {

    new tempusDominus.TempusDominus($('#clStartDateSearch')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#clEndDateSearch')[0], datePickerAfterTodayOneYearsTermOptions);

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

    $("#create-btn").on("click", function () {
        createClosing();
    });
    $("#search-btn").on("click", function () {
        viewPage(1);
    });
    $("#delete-btn").on("click", function () {
        deleteClosing();
    });
});

function createClosing() {
    const $clStartDate = $("#clStartDate input");
    const $clStartTime = $("#clStartTime input");
    const $clEndDate = $("#clEndDate input");
    const $clEndTime = $("#clEndTime input");

    if (!$clStartDate.val()) {
        alert("휴무 시작 날짜를 입력하세요");
        $clStartDate.focus();
        return;
    }

    if (!$clStartTime.val()) {
        alert("휴무 시작 시간을 입력하세요");
        $clStartTime.focus();
        return;
    }

    if (!$clEndDate.val()) {
        alert("휴무 종료 날짜를 입력하세요");
        $clEndDate.focus();
        return;
    }

    if (!$clEndTime.val()) {
        alert("휴무 종료 시간을 입력하세요");
        $clEndTime.focus();
        return;
    }

    const startDateTimeStr = $clStartDate.val() + 'T' + $clStartTime.val();
    const endDateTimeStr = $clEndDate.val() + 'T' + $clEndTime.val();

    if (startDateTimeStr >= endDateTimeStr) {
        alert('휴무 시작 시각은 종료 시각보다 앞서야 합니다.');
        $clStartDate.focus();
        return;
    }

    $.ajax({
        url: "/api/closing/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "clStart": startDateTimeStr,
            "clEnd": endDateTimeStr
        }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}

function viewPage(pageNum) {

    const $clStartDateSearch = $("#clStartDateSearch input");
    const $clEndDateSearch = $("#clEndDateSearch input");

    if (!$clStartDateSearch.val()) {
        alert("검색조건을 입력하세요");
        $clStartDateSearch.focus();
        return;
    }

    if (!$clStartDateSearch.val()) {
        alert("검색조건을 입력하세요");
        $clEndDateSearch.focus();
        return;
    }

    if ($clStartDateSearch.val() > $clEndDateSearch.val()) {
        alert('검색 종료 날짜가 시작 날짜보다 빠릅니다');
        $clStartDateSearch.focus();
        return;
    }

    $.ajax({
        url: "/closing/replace/manage/search?page=" + (pageNum-1),
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "clStartSearch": $clStartDateSearch.val(),
            "clEndSearch": $clEndDateSearch.val()
        }),
        success: function (fragment) {
            $("#closing-list").replaceWith(fragment);
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function deleteClosing() {

    const selectedIds = [];

    $("input.closing-check:checked").each(function () {
        const clId = $(this).closest("tr").data("id");
        if (clId !== undefined) {
            selectedIds.push(clId);
        }
    });

    if (selectedIds.length === 0) {
        alert("삭제할 휴무를 선택하세요.");
        return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/api/closing/delete",
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify(selectedIds),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}


function closeCreateDiv() {
    $("#create-div").addClass("d-none");
    $("#open-create-btn").removeClass("d-none");
}