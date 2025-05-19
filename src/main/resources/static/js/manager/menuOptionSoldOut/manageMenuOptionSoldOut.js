$(function () {
    initSearchHandler();
    initDateTimePickers();
    initPopHandlers();
    initHideButtonHandler();
    initCancelSoldOutHandler();
});

function initSearchHandler() {
    $(document).on("input", "#search-word", function () {
        if (!isLoading) {
            isLoading = true;
            window.scrollTo(0, 0);
            $("#menu-option-soldout-list").empty();
            currentPage = 1;
            scrollDown(currentPage);
        }
    });
}

function initDateTimePickers() {
    new tempusDominus.TempusDominus($('#osStartDate')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#osStartTime')[0], timePickerOptions);
    new tempusDominus.TempusDominus($('#osEndDate')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#osEndTime')[0], timePickerOptionsLater);
}

function initPopHandlers() {
    $(document).on("click", ".open-create-btn", function () {
        const opId = $(this).closest("[data-id]").data("id");
        $("#create-soldout-pop").data("id", opId).removeClass("d-none");
    });

    $(document).on("click", "#close-btn, #close-create-soldout-pop", function () {
        $("#create-soldout-pop").addClass("d-none");
    });

    $(document).on("click", "#create-btn", function () {
        createMenuOptionSoldOut();
    });
}

function initHideButtonHandler() {
    $(document).on("click", ".hide-btn", function () {
        if (!confirm("옵션을 숨김처리 하시겠습니까?\n직접 해제하기 전에는 고객에게 보이지 않습니다")) {
            return;
        }

        const opId = $(this).closest("[data-id]").data("id");

        $.ajax({
            url: "/api/menu-option/modify/hidden/" + opId,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({ "opId": opId }),
            success: function (response) {
                alert(response.data);
                location.reload();
            },
            error: function (xhr, status, error) {
                commonErrorCallBack(xhr, status, error);
            }
        });
    });
}

function initCancelSoldOutHandler() {
    $(document).on("click", ".cancel-hide-btn, .cancel-sold-out-btn", function () {
        const opId = $(this).data("id") || $(this).closest("[data-id]").data("id");
        modifyMenuStatusAvailable(opId);
    });
}

function scrollDown(pageNum) {
    const searchWord = $("#search-word").val();

    $.ajax({
        url: "/menu-option/sold-out/replace/manage/search?page=" + (pageNum - 1),
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({ searchWord: searchWord }),
        success: function (fragment) {
            $("#menu-option-soldout-list").append(fragment);
            hideLoadingIndicator();
            isLoading = false;
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
            hideLoadingIndicator();
            isLoading = false;
        }
    });
}

function createMenuOptionSoldOut() {
    const opId = $("#create-soldout-pop").data("id");

    const $osStartDate = $("#osStartDate input");
    const $osStartTime = $("#osStartTime input");
    const $osEndDate = $("#osEndDate input");
    const $osEndTime = $("#osEndTime input");

    if (!opId) {
        alert("잘못된 접근입니다. 다시 시도하세요");
        $("#create-soldout-pop").addClass("d-none");
        return;
    }

    if (!$osStartDate.val()) {
        alert("품절 시작 날짜를 입력하세요");
        $osStartDate.focus();
        return;
    }

    if (!$osStartTime.val()) {
        alert("품절 시작 시간을 입력하세요");
        $osStartTime.focus();
        return;
    }

    if (!$osEndDate.val()) {
        alert("품절 종료 날짜를 입력하세요");
        $osEndDate.focus();
        return;
    }

    if (!$osEndTime.val()) {
        alert("품절 종료 시간을 입력하세요");
        $osEndTime.focus();
        return;
    }

    const startDateTimeStr = $osStartDate.val() + 'T' + $osStartTime.val();
    const endDateTimeStr = $osEndDate.val() + 'T' + $osEndTime.val();

    if (startDateTimeStr >= endDateTimeStr) {
        alert('품절 시작 시각은 종료 시각보다 앞서야 합니다.');
        $osStartDate.focus();
        return;
    }

    $.ajax({
        url: "/api/menu-option/sold-out/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "osOpId": opId,
            "osStart": startDateTimeStr,
            "osEnd": endDateTimeStr
        }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
            $("#create-soldout-pop").addClass("d-none");
        }
    });
}

function modifyMenuStatusAvailable(opId) {
    if (!confirm("옵션을 판매중으로 변경하겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/api/menu-option/modify/available/" + opId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ "opId": opId }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}
