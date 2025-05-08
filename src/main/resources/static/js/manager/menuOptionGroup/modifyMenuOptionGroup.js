const deleteOpIds = [];

$(function () {

    $("#add-menu-option-btn").on("click", function () {
        addMenuOption();
    });
    $(document).on("click", ".remove-option-btn", function () {
        const $item = $(this).closest(".d-flex");
        const id = $item.data("id");

        if (id !== undefined) {
            deleteOpIds.push(id);
        }

        $item.remove();
    });

    $("#modify-btn").on("click", function () {
        modifyMenuOptionGroup();
    });
});

function addMenuOption() {
    const index = $("#menu-option-list > div").length;
    const optionItem = `
        <div class="d-flex justify-content-start align-items-center">
            <input type="text" name="menuOptions[${index}].name" class="basic-input me-4 option-name" placeholder="옵션이름"/>
            <input type="text" name="menuOptions[${index}].price" class="basic-input me-2 text-end option-price price-input" placeholder="옵션가격"/>
            <span class="fw-bold">원</span>
            <button type="button" class="btn btn-danger px-2 pt-1 pb-0 ms-4 remove-option-btn">
                <i class="bi bi-trash3-fill"></i>
            </button>
        </div>
    `;
    $("#menu-option-list").append(optionItem);
}

function modifyMenuOptionGroup() {

    const $ogId = $("#ogId");
    const $ogName = $("#ogName");
    const $ogMinSelectCheck = $("#ogMinSelect-check");
    const $ogMinSelect = $("#ogMinSelect");
    const $ogMaxSelectCheck = $("#ogMaxSelect-check");
    const $ogMaxSelect = $("#ogMaxSelect");

    if (!$ogId.val()) {
        alert("잘못된 접근입니다");
        location.href = "/menu-option-group/manage";
        return;
    }

    if (!$ogName.val()) {
        alert("옵션그룹명을 입력하세요");
        $ogName.focus();
        return;
    }

    if ($ogMinSelectCheck.is(":checked") && !$ogMinSelect.val()) {
        alert("최소 선택값을 입력해주세요.");
        $ogMinSelect.focus();
        return;
    }

    if ($ogMaxSelectCheck.is(":checked") && !$ogMaxSelect.val()) {
        alert("최대 선택값을 입력해주세요.");
        $ogMaxSelect.focus();
        return;
    }

    if ($ogMinSelectCheck.is(":checked") && $ogMaxSelectCheck.is(":checked")) {
        const minVal = parseInt($ogMinSelect.val(), 10);
        const maxVal = parseInt($ogMaxSelect.val(), 10);

        if (minVal > maxVal) {
            alert("최소 선택값이 최대 선택값보다 많을 수 없습니다");
            $ogMaxSelect.focus();
            return;
        }
    }

    if ($ogMinSelectCheck.is(":checked")) {
        const minVal = parseInt($ogMinSelect.val(), 10);
        if (minVal < 1) {
            alert("최대 선택값은 1개 이상이어야 합니다");
            $ogMinSelect.focus();
            return;
        }
    }

    if ($ogMaxSelectCheck.is(":checked")) {
        const maxVal = parseInt($ogMaxSelect.val(), 10);
        if (maxVal < 1) {
            alert("최대 선택값은 1개 이상이어야 합니다");
            $ogMaxSelect.focus();
            return;
        }
    }

    let options = [];
    let hasInvalidOption = false;

    $("#menu-option-list > div").each(function (i, el) {
        const $name = $(el).find(".option-name");
        const $price = $(el).find(".option-price");

        if (!$name.val()) {
            alert(`${i + 1}번째 옵션의 이름을 입력해주세요.`);
            $name.focus();
            hasInvalidOption = true;
            return false;
        }

        if (!$price.val()) {
            alert(`${i + 1}번째 옵션의 가격을 입력해주세요.`);
            $price.focus();
            hasInvalidOption = true;
            return false;
        }

        const option = {
            "opName": $name.val(),
            "opPrice": parseInt($price.val().replace(/,/g, ''), 10)
        };

        const id = $(el).data("id");
        if (id !== undefined) {
            option.opId = id;
        }

        options.push(option);

    });

    if (hasInvalidOption) {
        return;
    }

    $.ajax({
        url: "/api/menu-option-group/modify",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            "ogId": $ogId.val(),
            "ogName": $ogName.val(),
            "ogMinSelect": $ogMinSelectCheck.is(":checked") ? parseInt($ogMinSelect.val(), 10) : null,
            "ogMaxSelect": $ogMaxSelectCheck.is(":checked") ? parseInt($ogMaxSelect.val(), 10) : null,
            "menuOptionDtoList": options,
            "deleteOpIds": deleteOpIds
        }),
        success: function (response) {
            alert(response.data);
            location.href = "/menu-option-group/manage";
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}