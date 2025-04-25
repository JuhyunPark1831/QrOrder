$(function () {
    $("#add-menu-option-group-select-btn").on("click", function () {
        addMenuOptionGroupSelect();
    });
    $(document).on("click", ".remove-option-group-btn", function () {
        $(this).closest(".d-flex").remove();
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

    $("#create-btn").on("click", function () {
        createMenu();
    });
});

function addMenuOptionGroupSelect() {
    const index = $("#menu-option-group-list > div").length;
    const templateHtml = $("#menu-option-group-template").html();
    const optionItem = templateHtml.replace(/__index__/g, index);
    $("#menu-option-group-list").append(optionItem);
}

function createMenu() {
    const $meName = $("#meName");
    const $meCaId = $("#meCaId");
    const $mePrice = $("#mePrice");
    const $meDescription = $("#meDescription");
    const $meImage = $("#meImage")[0].files[0];

    if (!$meName.val()) {
        alert("메뉴명을 입력하세요");
        $meName.focus();
        return;
    }

    if (!$meCaId.val()) {
        alert("카테고리를 선택하세요");
        $meCaId.focus();
        return;
    }

    if (!$mePrice.val()) {
        alert("메뉴가격을 입력하세요");
        $mePrice.focus();
        return;
    }

    let options = [];
    let hasInvalidOption = false;

    $("#menu-option-group-list > div").not("#menu-option-group-template").each(function (i, el) {
        const $ogId = $(el).find(".menu-option-group");

        if (!$ogId.val()) {
            alert(`${i + 1}번째 옵션의 이름을 입력해주세요.`);
            $ogId.focus();
            hasInvalidOption = true;
            return false;
        }

        options.push($ogId.val());
    });

    if (hasInvalidOption) {
        return;
    }

    const formData = new FormData();
    formData.append("meName", $meName.val());
    formData.append("meCaId", $meCaId.val());
    formData.append("mePrice", parseInt($mePrice.val().replace(/,/g, ''), 10));
    formData.append("meDescription", $meDescription.val());

    if ($meImage) {
        formData.append("meImage", $meImage);
    }

    formData.append("ogIdList", options);

    $.ajax({
        url: "/api/menu/create",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function (response) {
            alert(response.data);
            location.href = "/menu/create";
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}

//todo: 메뉴 사진 수정 시 처리 방법 설계