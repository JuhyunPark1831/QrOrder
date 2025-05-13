package com.sideProject.qrOrder.repository.menuSoldOut;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.sideProject.qrOrder.entity.QMenuSoldOut.menuSoldOut;

@Repository
@RequiredArgsConstructor
public class MenuSoldOutCustomRepositoryImpl implements MenuSoldOutCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MenuSoldOut> findByMsStatusAndDateTimeAfter(SoldOutProgressStatus msStatus) {

        BooleanBuilder builder = new BooleanBuilder();

        if (msStatus == SoldOutProgressStatus.WAITING) {

            builder.and(menuSoldOut.msStatus.eq(msStatus))
                    .and(menuSoldOut.msStart.loe(LocalDateTime.now()));
        } else if (msStatus == SoldOutProgressStatus.SOLD_OUT) {

            builder.and(menuSoldOut.msStatus.eq(msStatus))
                    .and(menuSoldOut.msEnd.lt(LocalDateTime.now()));
        }

        return jpaQueryFactory.selectFrom(menuSoldOut)
                .where(builder)
                .fetch();
    }
}
