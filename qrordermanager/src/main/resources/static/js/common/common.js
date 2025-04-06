/* JWT 인증 처리 */

var originalRequestSettings = null;

$.ajaxSetup({
    beforeSend: function(xhr, settings) {
        originalRequestSettings = settings;
    }
})

function commonErrorMessageCallBack(response) {

    if (!response) {
        alert("서버 오류. 고객센터에 문의하세요.");
    } else {
        if (response.code) {
            alert(response.message);
        } else {
            alert("서버 오류. 고객센터에 문의하세요."); // 서버에서 처리하지 못한 오류
        }
    }
}

function commonErrorCallBack(xhr, status, error) {

    var response = xhr.responseJSON;

    if (response && response.code === '021') {
        alert("로그인이 필요합니다.");
        location.href = "/account/login"
    } else if (response && response.code === '022') {
        if (originalRequestSettings) {
            $.ajax(originalRequestSettings);
        }
    } else {
        commonErrorMessageCallBack(response);
    }
}

/* JWT 인증 처리 끝*/

$(document).ready(function () {

     $("#leftMenu-icon").on("click", function () {
        let $this = $(this);
        let currentClass = $this.attr("class");

        if (currentClass.includes("active")) {
            $this.attr("class", currentClass.replace("active", "").trim());
            closeMenu();
        } else {
            $this.attr("class", currentClass + " active");
            openMenu();
        }
    })

    $("#darkArea").on("click", function () {
        let $leftMenuIcon = $("#leftMenu-icon");
        $leftMenuIcon.attr("class", $leftMenuIcon.attr("class").replace("active", "").trim());
        closeMenu();
    })
});

function openMenu () {
    $("#leftMenu").addClass("active");
    $("#darkArea").show();
}

function closeMenu () {
    $("#leftMenu").removeClass("active");
    $("#darkArea").hide();
}