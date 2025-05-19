package com.sideProject.qrOrder.repository.closingFixed;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.entity.ClosingFixed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QClosingFixed.closingFixed;

@Repository
@RequiredArgsConstructor
public class ClosingFixedCustomRepositoryImpl implements ClosingFixedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<ClosingFixed> findClosingFixedByDateTimeBetweenStartAndEnd(LocalDateTime dateTime) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(closingFixed)
                .fetchOne());
//        return null;
    }
}
