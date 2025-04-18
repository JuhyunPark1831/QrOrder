package com.sideProject.qrOrder.repository.category;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.account.AccountRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QAccount.account;
import static com.sideProject.qrOrder.entity.QCategory.category;
import static com.sideProject.qrOrder.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CategoryResponseDto> findCategory(Pageable pageable, CategoryRequestDto requestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto != null && requestDto.getSearchWord() != null && !requestDto.getSearchWord().isEmpty()) {
            builder.and(category.caName.containsIgnoreCase(requestDto.getSearchWord()));
        }

        List<CategoryResponseDto> content = jpaQueryFactory
                .select(Projections.constructor(
                        CategoryResponseDto.class,
                        category.caId,
                        category.caName,
                        category.caSeq,
                        menu.count().intValue().coalesce(0)
                ))
                .from(category)
                .leftJoin(menu).on(menu.meCa.eq(category))
                .where(builder)
                .groupBy(category.caId, category.caName, category.caSeq)
                .orderBy(category.caSeq.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(category.count())
                        .from(category)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public void shiftDownCaSeq(List<Long> caIds) {

        if (caIds == null || caIds.isEmpty()) return;

        List<Integer> deletedSeqList = jpaQueryFactory
                .select(category.caSeq)
                .from(category)
                .where(category.caId.in(caIds))
                .orderBy(category.caSeq.desc())
                .fetch();

        for (Integer deletedSeq : deletedSeqList) {
            jpaQueryFactory
                    .update(category)
                    .set(category.caSeq, category.caSeq.subtract(1))
                    .where(category.caSeq.gt(deletedSeq))
                    .execute();
        }
    }
}
