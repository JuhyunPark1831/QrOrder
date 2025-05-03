$(function () {

    $("#search-word").on("input", function () {
        searchMenu();
    });

    new tempusDominus.TempusDominus($('#msStartDate')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#msStartTime')[0],timePickerOptions);

    new tempusDominus.TempusDominus($('#msEndDate')[0], datePickerAfterTodayOptions);
    new tempusDominus.TempusDominus($('#msEndTime')[0],timePickerOptionsLater);

    $(document).on("click", ".hide-btn", function () {
        if(!confirm("메뉴를 숨김처리 하시겠습니까?\n직접 해제하기 전에는 고객에게 보이지 않습니다")) {
            return;
        }

        const meId = $(this).closest(".basic-card").data("id");

        $.ajax({
            url: "/api/menu/modify/hidden/" + meId,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                "meId": meId
            }),
            success: function (response) {
                alert(response.data);
                location.reload();
            },
            error: function(xhr, status, error) {
                commonErrorCallBack(xhr, status, error);
            }
        });
    });

    $(document).on("click", ".cancel-hide-btn", function () {
        const meId = $(this).closest(".basic-card").data("id");
        modifyMenuStatusAvailable(meId);
    });
    $(document).on("click", ".cancel-sold-out-btn", function () {
        const meId = $(this).closest(".basic-card").data("id");
        modifyMenuStatusAvailable(meId);
    });

    $(document).on("click", ".sold-out-btn", function () {
        const meId = $(this).closest(".basic-card").data("id");
        $("#create-soldout-pop").data("id", meId).removeClass("d-none");
    });
    $("#close-btn, #close-create-soldout-pop").on("click", function () {
        $("#create-soldout-pop").addClass("d-none")
    });

    $("#create-btn").on("click", function () {
        createMenuSoldOut();
    });
});

function searchMenu() {

    const searchWord = $("#search-word").val();

    $.ajax({
        url: "/menu/sold-out/replace/manage/search",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            searchWord: searchWord
        }),
        success: function(fragment) {
            $("#menu-sold-out-list").replaceWith(fragment);
        },
        error: function(xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}

function createMenuSoldOut() {

    const meId = $("#create-soldout-pop").data("id");

    const $msStartDate = $("#msStartDate input");
    const $msStartTime = $("#msStartTime input");
    const $msEndDate = $("#msEndDate input");
    const $msEndTime = $("#msEndTime input");

    if (!meId) {
        alert("잘못된 접근입니다. 다시 시도하세요");
        $("#create-soldout-pop").addClass("d-none")
        return;
    }

    if (!$msStartDate.val()) {
        alert("품절 시작 날짜를 입력하세요");
        $msStartDate.focus();
        return;
    }

    if (!$msStartTime.val()) {
        alert("품절 시작 시간을 입력하세요");
        $msStartTime.focus();
        return;
    }

    if (!$msEndDate.val()) {
        alert("품절 종료 날짜를 입력하세요");
        $msEndDate.focus();
        return;
    }

    if (!$msEndTime.val()) {
        alert("품절 종료 시간을 입력하세요");
        $msEndTime.focus();
        return;
    }

    const startDateTimeStr = $msStartDate.val() + 'T' + $msStartTime.val();
    const endDateTimeStr = $msEndDate.val() + 'T' + $msEndTime.val();

    if (startDateTimeStr >= endDateTimeStr) {
        alert('품절 시작 시각은 종료 시각보다 앞서야 합니다.');
        $msStartDate.focus();
        return;
    }

    $.ajax({
        url: "/api/menu/sold-out/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "msMeId": meId,
            "msStart": startDateTimeStr,
            "msEnd": endDateTimeStr
        }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function(xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}

function modifyMenuStatusAvailable(meId) {

    if(!confirm("메뉴를 판매중으로 처리하겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/api/menu/modify/available/" + meId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            "meId": meId
        }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function(xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}