package com.sideProject.qrOrder.repository.menuSoldOut;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.entity.Menu;
import com.sideProject.qrOrder.entity.MenuSoldOut;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QCategory.category;
import static com.sideProject.qrOrder.entity.QMenu.menu;
import static com.sideProject.qrOrder.entity.QMenuSoldOut.menuSoldOut;

@Repository
@RequiredArgsConstructor
public class MenuSoldOutCustomRepositoryImpl implements MenuSoldOutCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MenuSoldOut> findByMsStatusAndDateTimeAfter(SoldOutStatus msStatus) {

        BooleanBuilder builder = new BooleanBuilder();

        if (msStatus == SoldOutStatus.WAITING) {

            builder.and(menuSoldOut.msStatus.eq(msStatus))
                    .and(menuSoldOut.msStart.loe(LocalDateTime.now()));
        } else if (msStatus == SoldOutStatus.SOLD_OUT) {

            builder.and(menuSoldOut.msStatus.eq(msStatus))
                    .and(menuSoldOut.msEnd.lt(LocalDateTime.now()));
        }

        return jpaQueryFactory.selectFrom(menuSoldOut)
                .where(builder)
                .fetch();
    }
}
