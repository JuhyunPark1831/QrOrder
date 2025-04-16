$(document).ready(function () {
    const $checkAll = $('thead input[type="checkbox"]');
    const $checkboxes = $('tbody input[type="checkbox"]');

    $checkAll.on('change', function () {
        $checkboxes.prop('checked', $(this).is(':checked'));
    });

    $checkboxes.on('change', function () {
        const allChecked = $checkboxes.length === $checkboxes.filter(':checked').length;
        $checkAll.prop('checked', allChecked);
    });

    $('#search-word').on('input', function () {
        viewPage(1);
    });

    $('#delete-btn').on('click', function () {
        deleteAccount();
    });
});

function viewPage(pageNum) {

    const searchWord = $('#search-word').val();

    $.ajax({
        url: '/account/replace/manage/search?page=' + (pageNum-1),
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            searchWord: searchWord
        }),
        success: function (fragment) {
            $('#account-list').replaceWith(fragment);
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    })
}

function deleteAccount() {

    const selectedIds = [];

    $('input.account-check:checked').each(function () {
        const acId = $(this).closest('tr').data('id');
        if (acId !== undefined) {
            selectedIds.push(acId);
        }
    });

    if (selectedIds.length === 0) {
        alert('삭제할 계정을 선택하세요.');
        return;
    }

    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }

    $.ajax({
        url: '/api/account/delete',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(selectedIds),
        success: function (response) {
            alert('삭제가 완료되었습니다.');
            location.reload();
        },
        error: function (xhr, status, error) {
            commonErrorCallBack(xhr, status, error);
        }
    });
}