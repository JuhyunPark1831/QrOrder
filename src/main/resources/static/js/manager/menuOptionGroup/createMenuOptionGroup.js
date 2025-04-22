$(function () {

});

function createMenuOptionGroup() {

    $.ajax({
        url: "/api/menu/create",
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