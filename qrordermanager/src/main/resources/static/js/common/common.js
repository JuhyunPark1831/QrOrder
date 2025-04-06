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
    } else if (response && response.code === '650') { // 500 에러
        alert("서버에 문제가 발생했습니다.\n잠시 후 다시 시도해 주세요.\n문제가 계속될 시 고객센터에 문의해주세요.");
    } else if (response && response.code === '651') { // 404 에러
        alert("요청하신 정보를 찾을 수 없습니다.");
    } else if (response && response.code === '652') { // 405 에러
        alert("잘못된 요청 방식입니다.\n잠시 후 다시 시도해 주세요.");
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