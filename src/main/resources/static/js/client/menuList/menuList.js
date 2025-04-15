$(function () {
    let lastScrollTop = 0;
    const $bottomBar = $('.bottom-bar');

    const handleScroll = _.throttle(function () {
        const scrollTop = $(window).scrollTop();

        if (scrollTop > lastScrollTop) {
            // 아래로 스크롤 → 바 숨김
            $bottomBar.addClass('hide');
        } else {
            // 위로 스크롤 → 바 표시
            $bottomBar.removeClass('hide');
        }

        lastScrollTop = scrollTop <= 0 ? 0 : scrollTop;
    }, 100); // 100ms 간격

    $(window).on('scroll', handleScroll);
})