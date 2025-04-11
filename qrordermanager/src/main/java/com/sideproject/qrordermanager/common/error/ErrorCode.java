package com.sideproject.qrordermanager.common.error;

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
        6: Etc

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
    ACCESS_TOKEN_AUTHENTICATION_FAILED(401, "020", "ACCESS TOKEN 인증 실패"),
    REFRESH_TOKEN_AUTHENTICATION_FAILED(401, "021", "ACCESS TOKEN 인증 실패"),
    TOKEN_REGENERATE(401, "022", "TOKEN 재발급"),
    NOT_FOUND_ACCOUNT(404, "030", "아이디, 비밀번호를 다시 확인해주세요"),

    ECT_ERROR(500, "650", "기타 서버 에러"),
    NOT_FOUND(404, "651", "기본 404 에러"),
    NOT_SUPPORTED(405, "652", "기본 405 에러");

    private final int httpStatus;
    private final String code;
    private final String message;
}
