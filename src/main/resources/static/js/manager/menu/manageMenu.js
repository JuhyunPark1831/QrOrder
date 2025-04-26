$(function () {
    $("#search-word").on("input", function () {
        window.scrollTo(0, 0);
        searchMenu();
    });

    $(document).on("click", ".modify-btn", function () {
        const meId = $(this).closest(".basic-card").data("id");

        location.href = "/menu/modify/" + meId;
    });
    $(document).on("click", ".delete-btn", function () {
        const meId = $(this).closest(".basic-card").data("id");

        if (!confirm("정말 삭제하시겠습니까?")) {
            return;
        }

        $.ajax({
            url: "/api/menu/delete",
            type: "DELETE",
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
});

function searchMenu() {

    const searchWord = $("#search-word").val();

    $.ajax({
        url: "/menu/replace/manage/search",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            searchWord: searchWord
        }),
        success: function(fragment) {
            $("#menu-list").replaceWith(fragment);
        },
        error: function(xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}