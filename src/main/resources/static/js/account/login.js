$(document).ready(function () {

    $('#acLoginId, #acPassword').on("keydown", function (e) {
        if (e.key === "Enter") {
            login();
        }
    });

    $('#login-submit').on("click", function() {
        login();
    });
})

function login() {

    const $acLoginId = $("#acLoginId");
    const $acPassword = $("#acPassword");

    if (!$acLoginId.val()) {
        alert("아이디를 입력하세요");
        $acLoginId.focus();
        return;
    }

    if (!$acPassword.val()) {
        alert("비밀번호를 입력하세요");
        $acPassword.focus();
        return;
    }

    $.ajax({
        url: '/api/account/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            "acLoginId": $acLoginId.val(),
            "acPassword": $acPassword.val()
        }),
        success: function () {
            location.href = "/";
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}