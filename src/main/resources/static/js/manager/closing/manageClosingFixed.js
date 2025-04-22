$(function () {

    new tempusDominus.TempusDominus($('#cfStartTime')[0],timePickerOptions);
    new tempusDominus.TempusDominus($('#cfEndTime')[0],timePickerOptions);

    $("#open-create-btn").on("click", function () {
        $("#create-div").removeClass("d-none");
        $(this).addClass("d-none");
    });
    $("#close-create-btn").on("click", function () {
        $("#create-div").addClass("d-none");
        $("#open-create-btn").removeClass("d-none");
    });

    $("#create-btn").on("click", function () {
        createClosingFixed();
    });
    $(document).on("click", ".cfUseStatus-btn", function () {

        if (!confirm("사용여부를 변경하겠습니까?")) {
            return;
        }

        const cfId = $(this).closest('tr').data('id');

        $.ajax({
            url: "/api/closing-fixed/modify/toggle/use-status",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                "cfId": cfId
            }),
            success: function (response) {
                alert(response.data);
                location.reload();
            },
            error: function (xhr, status, error) {
                commonErrorCallBack(xhr, status, error);
            }
        })
    });
    $("#delete-btn").on("click", function () {
        deleteClosingFixed();
    });
});

function createClosingFixed() {

    const $cfWeekNum = $("#cfWeekNum");
    const $cfWeekDay = $("#cfWeekDay");
    const $cfStartTime = $("#cfStartTime input");
    const $cfEndTime = $("#cfEndTime input");

    if (!$cfWeekNum.val()) {
        alert("번째를 입력해주세요")
        $cfWeekNum.focus();
        return;
    }

    if (!$cfWeekDay.val()) {
        alert("요일을 입력해주세요")
        $cfWeekDay.focus();
        return;
    }

    if (!$cfStartTime.val()) {
        alert("시작시간을 입력해주세요")
        $cfStartTime.focus();
        return;
    }

    if (!$cfEndTime.val()) {
        alert("종료시간을 입력해주세요")
        $cfEndTime.focus();
        return;
    }

    if ($cfStartTime.val() >= $cfEndTime.val()) {
        alert('종료시간이 시작시간보다 빠릅니다');
        $cfStartTime.focus();
        return;
    }

    $.ajax({
        url: "/api/closing-fixed/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "cfWeekNum": $cfWeekNum.val(),
            "cfWeekDay": $cfWeekDay.val(),
            "cfStartTime": $cfStartTime.val(),
            "cfEndTime": $cfEndTime.val()
        }),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function viewPage(pageNum) {

    $.ajax({
        url: "/closing-fixed/replace/manage/search?page=" + (pageNum-1),
        type: "GET",
        contentType: "application/json",
        success: function (fragment) {
            $("#closing-fixed-list").replaceWith(fragment);
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function toggleCfUseStatus() {

}

function deleteClosingFixed() {
    const selectedIds = [];

    $("input.closing-fixed-check:checked").each(function () {
        const cfId = $(this).closest("tr").data("id");
        if (cfId !== undefined) {
            selectedIds.push(cfId);
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
        url: "/api/closing-fixed/delete",
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