$(function () {
    initScrollHandler();
    initMenuCardClick();
    initOptionSelectHandler();
    initAddMenuHandler();
    initPopStateHandler();
    setCartInfo();
    initCartButtonHandler();
});

function initPopStateHandler() {
    window.addEventListener('popstate', function () {
        const $popup = $('.menu-detail-pop');
        if ($popup.hasClass('show')) {
            $popup.addClass('no-transition').removeClass('show');
            requestAnimationFrame(() => $popup.removeClass('no-transition'));
        } else {
            history.back();
        }
    });
}

function initMenuCardClick() {
    $(document).on("click", ".menu-card", function () {
        const meId = $(this).data("id");

        $.ajax({
            url: `/client/replace/menu/pop/${meId}`,
            type: "GET",
            success: function (fragment) {
                $("#menu-detail-pop").replaceWith(fragment);
                const $popup = $(".menu-detail-pop").removeClass("show");
                requestAnimationFrame(() => {
                    $popup.addClass("show");
                    history.pushState({ popup: true }, '', '');
                });
                calculateMePrice();
            },
            error: commonErrorCallBack
        });
    });

    $(document).on("click", "#close-menu-detail-pop", function () {
        $(".menu-detail-pop").removeClass("show");
    });
}

function initScrollHandler() {
    let lastScrollTop = 0;
    const $bottomBar = $('.bottom-bar');

    const handleScroll = _.throttle(function () {
        const scrollTop = $(window).scrollTop();
        const isAtBottom = scrollTop + $(window).height() >= $(document).height() - 1;

        if (isAtBottom) {
            $bottomBar.removeClass('hide');
        } else if (scrollTop > lastScrollTop) {
            $bottomBar.addClass('hide');
        } else {
            $bottomBar.removeClass('hide');
        }

        lastScrollTop = Math.max(scrollTop, 0);
    }, 100);

    $(window).on('scroll', handleScroll);
}

function initOptionSelectHandler() {
    $(document).on("click", 'input[type="radio"], input[type="checkbox"]', function (e) {
        const $input = $(this);
        const $group = $input.closest('.menu-option-group');
        const maxSelect = parseInt($group.data('max-select'), 10) || 0;

        if ($input.attr('type') === 'checkbox' && maxSelect > 0) {
            const checkedCount = $group.find('input[type="checkbox"]:checked').length;

            if ($input.prop('checked') && checkedCount > maxSelect) {
                e.preventDefault();
                return;
            }
        }

        calculateMePrice();
    });
}

function initAddMenuHandler() {
    $(document).on("click", '#add-menu-btn', addMenu);
}

function initCartButtonHandler() {
    $(document).on("click", "#submit-btn", function () {
        window.location.href = "/client/orderList";
    });
}

function calculateMePrice() {
    let totalPrice = 0;

    $('input[type="radio"]:checked, input[type="checkbox"]:checked').each(function () {
        const priceText = $(this).closest('.d-flex').find('.fs-5.fw-semibold').text();
        const price = parseInt(priceText.replace(/[^\d]/g, ''), 10) || 0;
        totalPrice += price;
    });

    const $priceElement = $('.total-me-price');
    const currentPrice = parseInt($priceElement.text().replace(/[^\d]/g, ''), 10) || 0;
    animateNumber($priceElement, currentPrice, totalPrice);

    let allValid = true;
    $('.menu-option-group').each(function () {
        const minSelect = parseInt($(this).data('min-select'), 10) || 0;
        const selectedCount = $(this).find('input:checked').length;

        if (selectedCount < minSelect) {
            allValid = false;
            return false;
        }
    });

    const $addBtn = $('#add-menu-btn');
    if (allValid) {
        $addBtn.removeClass('btn-secondary').addClass('btn-primary').prop('disabled', false);
    } else {
        $addBtn.removeClass('btn-primary').addClass('btn-secondary').prop('disabled', true);
    }
}

function animateNumber($element, start, end, duration = 500) {
    const startTime = performance.now();

    function update(currentTime) {
        const progress = Math.min((currentTime - startTime) / duration, 1);
        const currentValue = Math.floor(start + (end - start) * progress);
        $element.text(currentValue.toLocaleString() + '원');

        if (progress < 1) requestAnimationFrame(update);
    }

    requestAnimationFrame(update);
}

function addMenu() {
    const meId = $("#meId").val();

    const selectedRadios = $('input[type="radio"]:checked').slice(1).map(function () {
        return $(this).val();
    }).get();

    const selectedCheckboxes = $('input[type="checkbox"]:checked').map(function () {
        return $(this).val();
    }).get();

    const allSelectedOptionIds = [...selectedRadios, ...selectedCheckboxes];

    localStorage.setItem(meId, JSON.stringify(allSelectedOptionIds));

    $(".menu-detail-pop").removeClass("show");

    calculateTotalPrice();
    setCartInfo();
}

function calculateTotalPrice() {

    const newPrice = parseInt($('.total-me-price').text().replace(/[^\d]/g, ''), 10) || 0;
    const existingPrice = parseInt(localStorage.getItem("totalPrice"), 10) || 0;

    localStorage.setItem("totalPrice", existingPrice + newPrice);

    const currentCount = parseInt(localStorage.getItem("totalCount"), 10) || 0;
    localStorage.setItem("totalCount", currentCount + 1);
}

function setCartInfo() {
    const currentPrice = parseInt(localStorage.getItem("totalPrice"), 10) || 0;
    const currentCount = parseInt(localStorage.getItem("totalCount"), 10) || 0;

    animateNumber($("#total-price"), 0, currentPrice);
    $("#total-count").text(currentCount);
}