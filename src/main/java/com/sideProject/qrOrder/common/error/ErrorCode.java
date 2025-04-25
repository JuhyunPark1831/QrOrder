package com.sideProject.qrOrder.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
    - 첫번째 자리: 도메인
        0: Account
        1: Closing
        2: Menu
        3: MenuSoldOut
        4: MenuOrder
        5: Order
        6: Category
        7: ClosingFixed
        8: MenuOptionGroup
        9: Etc
        10: MenuOption

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

    NOT_UNIQUE_CATEGORY_NAME(404, "610", "카테고리명이 이미 존재합니다"),
    CATEGORY_HAS_MENU(404, "611", "해당 카테고리에 메뉴가 있습니다. 메뉴를 먼저 이동하여 카테고리를 비워주세요"),
    NOT_FOUND_CATEGORY(404, "630", "존재하지 않는 카테고리 입니다"),

    NOT_FOUND_CLOSING_FIXED(404, "730", "존재하지 않는 정기휴무 입니다"),

    NOT_UNIQUE_MENU_OPTION_GROUP_NAME(404, "810", "메뉴옵션그룹명이 이미 존재합니다"),
    NOT_FOUND_MENU_OPTION_GROUP(404, "830", "존재하지 않는 메뉴옵션그룹입니댜"),

    DATA_INTEGRITY_VIOLATION(401, "900", "필수값이 비어있습니다"),
    ECT_ERROR(500, "950", "기타 서버 에러"),
    NOT_FOUND(404, "951", "기본 404 에러"),
    NOT_SUPPORTED(405, "952", "기본 405 에러"),

    NOT_FOUND_MENU_OPTION(403, "1030", "존재하지 않는 메뉴 옵션입니다");

    private final int httpStatus;
    private final String code;
    private final String message;
}
