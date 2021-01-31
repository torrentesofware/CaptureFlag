package com.reto.torrent.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AboutusMapperTest {

    private AboutusMapper aboutusMapper;

    @BeforeEach
    public void setUp() {
        aboutusMapper = new AboutusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(aboutusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(aboutusMapper.fromId(null)).isNull();
    }
}
