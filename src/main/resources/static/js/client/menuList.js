$(function () {

    // 하단 바 처리
    let lastScrollTop = 0;
    const $bottomBar = $('.bottom-bar');

    const handleScroll = _.throttle(function () {
        const scrollTop = $(window).scrollTop();
        const windowHeight = $(window).height();
        const documentHeight = $(document).height();

        const isAtBottom = scrollTop + windowHeight >= documentHeight - 1; // 약간의 여유

        if (isAtBottom) {
            $bottomBar.removeClass('hide');
        } else if (scrollTop > lastScrollTop) {
            // 아래로 스크롤 → 바 숨김
            $bottomBar.addClass('hide');
        } else {
            // 위로 스크롤 → 바 표시
            $bottomBar.removeClass('hide');
        }

        lastScrollTop = scrollTop <= 0 ? 0 : scrollTop;
    }, 100); // 100ms 간격

    $(window).on('scroll', handleScroll);


    $(".menu-card").on("click", function () {

        const meId = $(this).data("id");

        $.ajax({
            url: "/client/replace/menu/pop/" + meId,
            type: "GET",
            success: function(fragment) {
                $("#menu-detail-pop").replaceWith(fragment);
                const $popup = $(".menu-detail-pop");
                $popup.removeClass("show");

                requestAnimationFrame(() => {
                    $popup.addClass("show");
                });
            },
            error: function(xhr, status, error) {
                commonErrorCallBack(xhr, status, error);
            }
        });
    });

    $(document).on("click", "#close-menu-detail-pop", function () {
        $(".menu-detail-pop").removeClass("show");
    });
});