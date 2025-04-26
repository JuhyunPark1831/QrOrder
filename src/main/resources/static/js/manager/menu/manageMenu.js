$(function () {
    $("#search-word").on("input", function () {
        window.scrollTo(0, 0);
        searchMenu();
    });

    $(document).on("click", ".modify-btn", function () {
        const ogId = $(this).closest(".basic-card").data("id");

        location.href = "/menu-option-group/modify/" + ogId;
    });
    $(document).on("click", ".delete-btn", function () {
        const ogId = $(this).closest(".basic-card").data("id");
        $("#delete-pop").data("id", ogId);

        $.ajax({
            url: "/api/menu-option-group-junction/find/menu-name",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "ogId": ogId
            }),
            success: function (response) {
                openDeletePop(response.data);
            },
            error: function(xhr, status, error) {
                commonErrorCallBack(xhr, status, error);
                hideLoadingIndicator();
                isLoading = false;
            }
        });
    });
    $("#close-delete-pop-btn, #delete-pop-dark-area").on("click", function () {
        $("#delete-pop").addClass("d-none");
    });

    $("#delete-btn").on("click", function () {
        deleteMenuOptionGroup();
    })
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

function deleteMenuOptionGroup() {
    const ogId = $("#delete-pop").data("id");

    $.ajax({
        url: "/api/menu-option-group/delete",
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify({
            "ogId": ogId
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