package com.sideProject.qrOrder.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
    - 첫번째 자리: 도메인
        0: Account
        1: Category
        2: Closing
        3: ClosingFixed
        4: Menu
        5: MenuOption
        6. MenuOptionGroup
        7. MenuOptionGroupJunction
        8. MenuOptionSoldOut
        9. MenuOrder
        10. MenuOrderOption
        11. MenuSoldOut
        12. Order
        20: Etc

    - 두번째 자리: 에러 종류
        0: 도메인 / Dto 객체 생성 오류 (MethodArgumentValidException, ConstraintViolationException 등)
        1: 도메인 로직 상의 오류
        2: 인증, 인가가 안된 오류
        3: 존재하지 않는 리소스에 대한 접근 오류
        4: 외부 API 관련 오류
        5: 기타 오류
    */

    TEST_ERROR(403, "999", "테스트 에러 처리"),

    INCORRECT_PASSWORD(401, "010", "아이디, 비밀번호를 다시 확인해주세요"),
    NOT_UNIQUE_LOGIN_ID(401, "011", "아이디가 중복되었습니다."),
    ACCESS_TOKEN_AUTHENTICATION_FAILED(401, "020", "ACCESS TOKEN 인증 실패"),
    REFRESH_TOKEN_AUTHENTICATION_FAILED(401, "021", "ACCESS TOKEN 인증 실패"),
    TOKEN_REGENERATE(401, "022", "TOKEN 재발급"),
    NOT_FOUND_ACCOUNT(404, "030", "아이디, 비밀번호를 다시 확인해주세요"),

    NOT_UNIQUE_CATEGORY_NAME(404, "110", "카테고리명이 이미 존재합니다"),
    CATEGORY_HAS_MENU(404, "111", "해당 카테고리에 메뉴가 있습니다. 메뉴를 먼저 이동하여 카테고리를 비워주세요"),
    NOT_FOUND_CATEGORY(404, "130", "존재하지 않는 카테고리 입니다"),

    NOT_FOUND_CLOSING_FIXED(404, "330", "존재하지 않는 정기휴무 입니다"),

    NOT_FOUND_MENU(404, "430", "존재하지 않는 메뉴 입니다"),

    NOT_FOUND_MENU_OPTION(403, "530", "존재하지 않는 메뉴 옵션입니다"),

    NOT_UNIQUE_MENU_OPTION_GROUP_NAME(404, "610", "메뉴옵션그룹명이 이미 존재합니다"),
    NOT_FOUND_MENU_OPTION_GROUP(404, "630", "존재하지 않는 메뉴옵션그룹입니댜"),

    NOT_FOUND_MENU_OPTION_GROUP_JUNCTION(404, "730", "존재하지 않는 메뉴옵션그룹관계입니댜"),

    DATA_INTEGRITY_VIOLATION(401, "2000", "필수값이 비어있습니다"),
    IMAGE_SAVE_FAILED(404, "2010", "이미지 파일 저장을 실패했습니다"),
    IMAGE_CHANGE_DELETE_FAILED(404, "2010", "이미지 파일 수정 혹은 삭제에 실패했습니다"),
    IMAGE_DOWNLOAD_FAILED(404, "2010", "이미지 파일을 가져오지 못했습니다"),
    NOT_FOUND_FILE(404, "2030", "파일을 찾지 못했습니다"),
    ECT_ERROR(500, "2050", "기타 서버 에러"),
    NOT_FOUND(404, "2051", "기본 404 에러"),
    NOT_SUPPORTED(405, "2052", "기본 405 에러");

    private final int httpStatus;
    private final String code;
    private final String message;
}
