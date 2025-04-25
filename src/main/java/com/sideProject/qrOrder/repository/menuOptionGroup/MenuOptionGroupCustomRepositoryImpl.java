package com.sideProject.qrOrder.repository.menuOptionGroup;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.entity.MenuOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QMenuOptionGroup.menuOptionGroup;

@Repository
@RequiredArgsConstructor
public class MenuOptionGroupCustomRepositoryImpl implements MenuOptionGroupCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MenuOptionGroup> findMenuOptionGroupList(Pageable pageable, MenuOptionGroupDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto != null && requestDto.getSearchWord() != null && !requestDto.getSearchWord().isEmpty()) {
            builder.and(menuOptionGroup.ogName.containsIgnoreCase(requestDto.getSearchWord()));
        }

        List<MenuOptionGroup> content = jpaQueryFactory
                .selectFrom(menuOptionGroup)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(menuOptionGroup.count())
                        .from(menuOptionGroup)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }
}
