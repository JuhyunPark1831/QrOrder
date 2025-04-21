package com.sideProject.qrOrder.repository.closing;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Closing;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QAccount.account;
import static com.sideProject.qrOrder.entity.QCategory.category;
import static com.sideProject.qrOrder.entity.QClosing.closing;
import static com.sideProject.qrOrder.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class ClosingCustomRepositoryImpl implements ClosingCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Closing> findClosing(Pageable pageable, ClosingRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto != null && requestDto.getClStartSearch() != null && requestDto.getClEndSearch() != null) {
            LocalDateTime startSearch = requestDto.getClStartSearch().atStartOfDay();
            LocalDateTime endSearch = requestDto.getClEndSearch().atTime(LocalTime.MAX);

            builder.and(closing.clEnd.goe(startSearch));
            builder.and(closing.clStart.loe(endSearch));
        }

        List<Closing> content = jpaQueryFactory
                .selectFrom(closing)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(closing.count())
                        .from(closing)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }
}
