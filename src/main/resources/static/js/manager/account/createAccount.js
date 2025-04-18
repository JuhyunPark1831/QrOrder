let isLoginIdChecked = false;

$(document).ready(function () {

    $("#check-acLoginId-btn").on("click", function () {
        checkAcLoginId();
    });

    $("#btn-submit").on("click", function () {
       createAccount();
    });
})

function checkAcLoginId() {

    const $acLoginId = $("#acLoginId");

    if (!$acLoginId.val()) {
        alert("아이디를 입력하세요");
        $acLoginId.focus();
        return;
    }

    $.ajax({
        url: "/api/account/check",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "acLoginId": $acLoginId.val(),
        }),
        success: function () {
            const $checkAcLoginIdBtn = $("#check-acLoginId-btn")
            $checkAcLoginIdBtn.prop("disabled", true);
            $acLoginId.prop("disabled", true);
            alert("중복 확인 완료");
            isLoginIdChecked = true;
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function createAccount() {

    const $acName = $("#acName");
    const $acLoginId = $("#acLoginId");
    const $acPassword = $("#acPassword");
    const $acRePassword = $("#acRePassword");

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

    if (!$acName.val()) {
        alert("이름을 입력하세요");
        $acName.focus();
        return;
    }

    if (!$acLoginId.val()) {
        alert("아이디를 입력하세요");
        $acLoginId.focus();
        return;
    }

    if (!isLoginIdChecked) {
        alert("아이디 중복을 확인해주세요");
        $acLoginId.focus();
        return;
    }

    if (!$acPassword.val()) {
        alert("비밀번호를 입력하세요");
        $acPassword.focus();
        return;
    }

    if (!passwordRegex.test($acPassword.val())) {
        alert("비밀번호는 8자 이상, 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        $acPassword.focus();
        return;
    }

    if (!$acRePassword.val()) {
        alert("비밀번호 재입력을 입력하세요");
        $acRePassword.focus();
        return;
    }

    if ($acPassword.val() !== $acRePassword.val()) {
        alert("비밀번호가 다릅니다");
        $acRePassword.focus();
        return;
    }

    $.ajax({
        url: "/api/account/create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "acName": $acName.val(),
            "acLoginId": $acLoginId.val(),
            "acPassword": $acPassword.val()
        }),
        success: function (response) {
            alert(response.data);
            location.href = "/account/manage";
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}
