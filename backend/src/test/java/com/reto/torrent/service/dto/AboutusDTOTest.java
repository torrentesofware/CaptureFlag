package com.reto.torrent.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.reto.torrent.web.rest.TestUtil;

public class AboutusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AboutusDTO.class);
        AboutusDTO aboutusDTO1 = new AboutusDTO();
        aboutusDTO1.setId(1L);
        AboutusDTO aboutusDTO2 = new AboutusDTO();
        assertThat(aboutusDTO1).isNotEqualTo(aboutusDTO2);
        aboutusDTO2.setId(aboutusDTO1.getId());
        assertThat(aboutusDTO1).isEqualTo(aboutusDTO2);
        aboutusDTO2.setId(2L);
        assertThat(aboutusDTO1).isNotEqualTo(aboutusDTO2);
        aboutusDTO1.setId(null);
        assertThat(aboutusDTO1).isNotEqualTo(aboutusDTO2);
    }
}
