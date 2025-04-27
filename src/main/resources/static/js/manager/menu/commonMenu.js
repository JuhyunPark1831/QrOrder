const deleteMjIds = [];

$(function () {
    $("#add-menu-option-group-select-btn").on("click", function () {
        addMenuOptionGroupSelect();
    });
    $(document).on("click", ".remove-option-group-btn", function () {
        const $item = $(this).closest(".d-flex");

        const id = $item.data("id");

        if (id !== undefined) {
            deleteMjIds.push(id);
        }

        $item.remove();
    });

    $("#upload-file-btn").on("click", function () {
        $("#meImage").click();
    });
    $("#meImage").on("change", function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $(".img-div img").attr("src", e.target.result);
                $(".upload-file-btn").next().text(file.name);
            };
            reader.readAsDataURL(file);
        }
    });
});

function addMenuOptionGroupSelect() {
    const index = $("#menu-option-group-list > div").length;
    const templateHtml = $("#menu-option-group-template").html();
    const optionItem = templateHtml.replace(/__index__/g, index);
    $("#menu-option-group-list").append(optionItem);
}