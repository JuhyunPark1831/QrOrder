package com.sideProject.qrOrder.repository.closingFixed;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClosingFixedCustomRepositoryImpl implements ClosingFixedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
}
