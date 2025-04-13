$(function () {
    // 카테고리 추가
    $("#create-btn").on("click", function () {
        $("#create-row").removeClass("d-none");
        $("#caName").focus();
    });
    $("#close-create-row-btn").on("click", function () {
        $("#create-row").addClass("d-none");
    })

    // 카테고리명 수정
    $(".category-name").on("dblclick", function () {
        const $div = $(this);
        const $td = $div.closest("td");

        const currentText = $.trim($div.text());

        const $input = $("<input>")
            .attr({
                type: "text",
                id: "change-div",
                class: "col-8 border-0",
                placeholder: "카테고리명"
            })
            .val(currentText)
            .on("blur", function () {
                $div.text(currentText).show();
                $(this).remove();
                $td.find("button").addClass("d-none");
            });

        $div.hide().after($input);
        $input.focus();

        $td.find("button").removeClass("d-none");
    });

    // 카테고리 순서 수정 팝업 열고 닫기
    $("#open-seq-pop-btn").on("click", function() {
        $('#seq-pop').removeClass("d-none");
    });
    $("#close-seq-pop-btn, #seq-pop-dark-area").on("click", function() {
        closeSeqPop();
    });

    $("#category-list").sortable({
        handle: '.handle',
        axis: 'y',
        containment: 'parent',
        update: function (event, ui) {
            // 순서 바뀐 후 실행할 로직
            const newOrder = [];
            $('#category-list > div').each(function (index) {
                const name = $(this).find('.ms-2').text().trim();
                newOrder.push({ index: index + 1, name: name });
            });

            console.log("바뀐 순서:", newOrder);
            // 필요하면 AJAX로 서버에 전송 가능
        }
    });
});

function closeSeqPop() {
    $('#seq-pop').addClass("d-none");
}