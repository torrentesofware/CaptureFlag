package com.reto.torrent.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.reto.torrent.web.rest.TestUtil;

public class AboutusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aboutus.class);
        Aboutus aboutus1 = new Aboutus();
        aboutus1.setId(1L);
        Aboutus aboutus2 = new Aboutus();
        aboutus2.setId(aboutus1.getId());
        assertThat(aboutus1).isEqualTo(aboutus2);
        aboutus2.setId(2L);
        assertThat(aboutus1).isNotEqualTo(aboutus2);
        aboutus1.setId(null);
        assertThat(aboutus1).isNotEqualTo(aboutus2);
    }
}
