$(function () {
    // 카테고리 추가 Div 처리
    $("#open-create-btn").on("click", function () {
        $("#create-row").removeClass("d-none");
        $("#caName").focus();
    });
    $("#close-create-btn").on("click", function () {
        $("#create-row").addClass("d-none");
    });

    // 카테고리명 수정 Div 처리
    $(document).on("dblclick", ".category-name", function () {
        const $div = $(this);
        const $td = $div.closest("td");

        const $input = $("<input>")
            .attr({
                type: "text",
                class: "category-name-input col-6 border-0",
                placeholder: "카테고리명"
            })
            .val($.trim($div.text()));

        $div.hide().after($input);
        $input.focus();

        $td.find("button").removeClass("d-none");
    });
    $(document).on("click", ".changeAcName-cancel-btn", function () {
        const $td = $(this).closest("td");

        $td.find("input[type='text'].category-name-input").remove();
        $td.find(".category-name").show();
        $td.find("button").addClass("d-none");
    })

    // 카테고리 순서 변경 UI
    $("#open-seq-pop-btn").on("click", function () {
        $("#seq-pop").removeClass("d-none");
    })
    $("#close-seq-pop-btn, #seq-pop-dark-area").on("click", function () {
        $("#seq-pop").addClass("d-none");
    })
    $("#category-order-list").sortable({
        handle: ".handle",
        axis: "y",
        containment: "parent"
    });

    // 추가
    $(document).on("click", "#create-btn", function () {
        createCategory();
    });
    // 검색
    $("#search-word").on("input", function () {
        viewPage(1);
    });
    // 이름 수정
    $(document).on("click", ".changeAcName-btn", function () {
        const $td = $(this).closest("td");
        const $tr = $(this).closest("tr");

        const $input = $td.find(".category-name-input");
        const caId = $tr.data("id");

        if (!$.trim($input.val())) {
            alert("수정할 카테고리명을 입력하세요");
            $input.focus();
            return;
        }

        $.ajax({
            url: "/api/category/modify",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                "caId": caId,
                "caName": $input.val()
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
    // 순서 수정
    $("#changeAcSeq-btn").on("click", function () {
        modifyCaSeq();
    });
    // 삭제
    $("#delete-btn").on("click", function () {
        deleteCategory();
    });
});

function createCategory() {

    const $caName = $("#caName");

    if (!$caName.val()) {
        alert("카테고리명을 입력해주세요");
        $caName.focus();
        return;
    }

    $.ajax({
        url: "/api/category/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "caName": $caName.val()
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

    const searchWord = $("#search-word").val();

    $.ajax({
        url: "/category/replace/manage/search?page=" + (pageNum-1),
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "searchWord": searchWord
        }),
        success: function (fragment) {
            $("#category-list").replaceWith(fragment);
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function modifyCaSeq() {
    const newOrder = [];
    $("#category-order-list > div").each(function (index) {
        const id = $(this).data("id");
        newOrder.push({ "caId": id, "caSeq": index + 1 });
    });

    $.ajax({
        url: "/api/category/modify/order",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(newOrder),
        success: function (response) {
            alert(response.data);
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}

function deleteCategory() {

    const selectedIds = [];

    $("input.category-check:checked").each(function () {
        const caId = $(this).closest("tr").data("id");
        if (caId !== undefined) {
            selectedIds.push(caId);
        }
    });

    if (selectedIds.length === 0) {
        alert("삭제할 카테고리를 선택하세요.");
        return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/api/category/delete",
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