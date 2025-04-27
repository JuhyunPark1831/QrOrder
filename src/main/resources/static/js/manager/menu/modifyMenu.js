let isDeleted = false;

$(function () {
    $("#modify-btn").on("click", function () {
        modifyMenu();
    });

    $('#delete-img-btn').on('click', function() {
        // 삭제 처리
        $('.img-div img').hide();
        $('#delete-img-btn').hide();
        isDeleted = true;
        $('.img-div').append('<img class="w-100 h-100">');
    });
});

function modifyMenu() {

    const $meId = $("#meId");
    const $meName = $("#meName");
    const $meCaId = $("#meCaId");
    const $mePrice = $("#mePrice");
    const $meDescription = $("#meDescription");
    const $meImage = $("#meImage")[0].files[0];

    if (!$meId) {
        alert("잘못된 접근입니다");
        location.href = "/menu/manage";
        return;
    }

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

    const formData = new FormData();

    formData.append("meId", $meId.val());
    formData.append("meName", $meName.val());
    formData.append("meCaId", $meCaId.val());
    formData.append("mePrice", parseInt($mePrice.val().replace(/,/g, ''), 10));
    formData.append("meDescription", $meDescription.val().replace(/\n/g, '<br/>'));

    if ($meImage) {
        formData.append("meImage", $meImage);
    }

    formData.append("isDeleteImage", isDeleted);

    let hasInvalidOption = false;

    $("#menu-option-group-list > div").not("#menu-option-group-template").each(function (i, el) {
        const $ogId = $(el).find(".menu-option-group");

        if (!$ogId.val()) {
            alert(`${i + 1}번째 옵션의 이름을 입력해주세요.`);
            $ogId.focus();
            hasInvalidOption = true;
            return false;
        }

        formData.append(`menuOptionGroupJunctionDtoList[${i}].ogId`, $ogId.val());

        const mjId = $(el).data("id");
        if (mjId !== undefined) {
            formData.append(`menuOptionGroupJunctionDtoList[${i}].mjId`, mjId);
        }
    });

    formData.append("deleteOpIds", deleteMjIds);

    if (hasInvalidOption) {
        return;
    }

    $.ajax({
        url: "/api/menu/modify",
        type: "PUT",
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
//todo: 메뉴 사진 수정 시 처리 방법 설계