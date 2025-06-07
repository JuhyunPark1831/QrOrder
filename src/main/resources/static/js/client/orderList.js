$(document).ready(function () {

    setOrderList();
});

function setOrderList() {

    const cart = JSON.parse(localStorage.getItem("cart") || "[]");

    $.ajax({
        url: "/client/replace/order-list",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(cart),
        success: function (fragment) {
            $("#order-list").replaceWith(fragment);
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
            // window.history.back();
        }
    })
}