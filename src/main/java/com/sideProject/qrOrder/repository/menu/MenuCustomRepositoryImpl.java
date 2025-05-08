package com.sideProject.qrOrder.repository.menu;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Closing;
import com.sideProject.qrOrder.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QCategory.category;
import static com.sideProject.qrOrder.entity.QClosing.closing;
import static com.sideProject.qrOrder.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MenuCustomRepositoryImpl implements MenuCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Menu> findMenu(Pageable pageable, MenuDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto != null && requestDto.getSearchWord() != null && !requestDto.getSearchWord().isEmpty()) {
            builder.and(menu.meName.containsIgnoreCase(requestDto.getSearchWord()));
        }

        JPAQuery<Menu> query = jpaQueryFactory
                .selectFrom(menu)
                .where(builder)
                .orderBy(menu.meCa.caSeq.asc());

        if (pageable.isPaged()) {
            query.offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        }

        List<Menu> content = query.fetch();

        long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(menu.count())
                        .from(menu)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }
}
