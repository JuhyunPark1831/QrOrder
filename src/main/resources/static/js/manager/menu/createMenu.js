$(function () {
    $("#create-btn").on("click", function () {
        createMenu();
    });
});

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

    let menuOptionGroupList = [];
    let hasInvalidOption = false;

    $("#menu-option-group-list > div").not("#menu-option-group-template").each(function (i, el) {
        const $ogId = $(el).find(".menu-option-group");

        if (!$ogId.val()) {
            alert(`${i + 1}번째 옵션의 이름을 입력해주세요.`);
            $ogId.focus();
            hasInvalidOption = true;
            return false;
        }

        menuOptionGroupList.push({
            "ogId": $ogId.val()
        });
    });

    if (hasInvalidOption) {
        return;
    }

    const formData = new FormData();
    formData.append("meName", $meName.val());
    formData.append("meCaId", $meCaId.val());
    formData.append("mePrice", parseInt($mePrice.val().replace(/,/g, ''), 10));
    formData.append("meDescription", $meDescription.val().replace(/\n/g, '<br/>'));
    formData.append("isDeleteImage", false);

    if ($meImage) {
        formData.append("meImage", $meImage);
    }

    menuOptionGroupList.forEach((optionGroup, index) => {
        formData.append(`menuOptionGroupJunctionDtoList[${index}].ogId`, optionGroup.ogId);
    });

    $.ajax({
        url: "/api/menu/create",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function (response) {
            alert(response.data);
            location.href = "/menu/manage";
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}