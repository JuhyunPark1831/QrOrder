package com.sideProject.qrOrder.service.menuOptionGroupJunction;

import com.sideProject.qrOrder.entity.MenuOptionGroupJunction;
import com.sideProject.qrOrder.repository.menuOptionGroupJunction.MenuOptionGroupJunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuOptionGroupJunctionServiceImpl implements MenuOptionGroupJunctionService {

    private final MenuOptionGroupJunctionRepository menuOptionGroupJunctionRepository;

    @Override
    public List<String> findMenuByMenuOptionGroup(Long mjOgId) {
        return menuOptionGroupJunctionRepository.findByMjOg_OgId(mjOgId).stream()
                .map(menuOptionGroupJunction -> menuOptionGroupJunction.getMjMe().getMeName())
                .collect(Collectors.toList());
    }
}
