package com.sideProject.qrOrder.repository.closingFixed;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
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

import static com.sideProject.qrOrder.entity.QClosing.closing;

@Repository
@RequiredArgsConstructor
public class ClosingFixedCustomRepositoryImpl implements ClosingFixedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
}
