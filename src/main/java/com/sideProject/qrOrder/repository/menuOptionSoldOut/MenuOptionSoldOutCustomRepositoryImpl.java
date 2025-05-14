package com.sideProject.qrOrder.repository.menuOptionSoldOut;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
import com.sideProject.qrOrder.entity.MenuOptionSoldOut;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.sideProject.qrOrder.entity.QMenuOptionSoldOut.menuOptionSoldOut;
import static com.sideProject.qrOrder.entity.QMenuSoldOut.menuSoldOut;

@Repository
@RequiredArgsConstructor
public class MenuOptionSoldOutCustomRepositoryImpl implements MenuOptionSoldOutCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MenuOptionSoldOut> findByOsStatusAndDateTimeAfter(SoldOutProgressStatus osStatus) {

        BooleanBuilder builder = new BooleanBuilder();

        if (osStatus == SoldOutProgressStatus.WAITING) {

            builder.and(menuOptionSoldOut.osStatus.eq(osStatus))
                    .and(menuOptionSoldOut.osStart.loe(LocalDateTime.now()));
        } else if (osStatus == SoldOutProgressStatus.SOLD_OUT) {

            builder.and(menuOptionSoldOut.osStatus.eq(osStatus))
                    .and(menuOptionSoldOut.osEnd.lt(LocalDateTime.now()));
        }

        return jpaQueryFactory.selectFrom(menuOptionSoldOut)
                .where(builder)
                .fetch();
    }
}
