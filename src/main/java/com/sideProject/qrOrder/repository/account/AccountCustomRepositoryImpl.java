package com.sideProject.qrOrder.repository.account;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.account.request.AccountRequestDto;
import com.sideProject.qrOrder.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class AccountCustomRepositoryImpl implements AccountCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<Account> findAccount(Pageable pageable, AccountRequestDto requestDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (requestDto != null && requestDto.getSearchWord() != null && !requestDto.getSearchWord().isEmpty()) {
            builder.and(account.acLoginId.containsIgnoreCase(requestDto.getSearchWord())
                    .or(account.acName.containsIgnoreCase(requestDto.getSearchWord())));
        }

        List<Account> content = jpaQueryFactory
                .selectFrom(account)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(account.count())
                        .from(account)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }
}
