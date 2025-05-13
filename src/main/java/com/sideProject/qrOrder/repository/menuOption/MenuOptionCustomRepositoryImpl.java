package com.sideProject.qrOrder.repository.menuOption;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.client.MenuOptionClientDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.sideProject.qrOrder.entity.QMenuOption.menuOption;
import static com.sideProject.qrOrder.entity.QMenuOptionSoldOut.menuOptionSoldOut;

@Repository
@RequiredArgsConstructor
public class MenuOptionCustomRepositoryImpl implements MenuOptionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MenuOptionSoldOutDto> findMenuOptionSoldOutDtoListByOgId(Long ogId) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        MenuOptionSoldOutDto.class,
                        menuOption.opId,
                        menuOption.opName,
                        menuOptionSoldOut.osId,
                        menuOptionSoldOut.osStart,
                        menuOptionSoldOut.osEnd
                ))
                .from(menuOption)
                .leftJoin(menuOptionSoldOut)
                .on(menuOptionSoldOut.osOp.opId.eq(menuOption.opId)
                        .and(menuOptionSoldOut.osEnd.gt(LocalDateTime.now()))
                )
                .where(menuOption.opOg.ogId.eq(ogId))
                .fetch();
    }

    @Override
    public List<MenuOptionClientDto> findMenuOptionClientDtoListByOgId(Long ogId) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        MenuOptionClientDto.class,
                        menuOption.opId,
                        menuOption.opName,
                        menuOption.opPrice
                ))
                .from(menuOption)
                .where(menuOption.opOg.ogId.eq(ogId))
                .fetch();
    }
}
