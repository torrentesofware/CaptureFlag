package com.reto.torrent.web.rest;

import com.reto.torrent.BackendApp;
import com.reto.torrent.domain.Aboutus;
import com.reto.torrent.repository.AboutusRepository;
import com.reto.torrent.service.AboutusService;
import com.reto.torrent.service.dto.AboutusDTO;
import com.reto.torrent.service.mapper.AboutusMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AboutusResource} REST controller.
 */
@SpringBootTest(classes = BackendApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AboutusResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private AboutusRepository aboutusRepository;

    @Autowired
    private AboutusMapper aboutusMapper;

    @Autowired
    private AboutusService aboutusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAboutusMockMvc;

    private Aboutus aboutus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aboutus createEntity(EntityManager em) {
        Aboutus aboutus = new Aboutus()
            .title(DEFAULT_TITLE)
            .image(DEFAULT_IMAGE)
            .description(DEFAULT_DESCRIPTION);
        return aboutus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aboutus createUpdatedEntity(EntityManager em) {
        Aboutus aboutus = new Aboutus()
            .title(UPDATED_TITLE)
            .image(UPDATED_IMAGE)
            .description(UPDATED_DESCRIPTION);
        return aboutus;
    }

    @BeforeEach
    public void initTest() {
        aboutus = createEntity(em);
    }

    @Test
    @Transactional
    public void createAboutus() throws Exception {
        int databaseSizeBeforeCreate = aboutusRepository.findAll().size();
        // Create the Aboutus
        AboutusDTO aboutusDTO = aboutusMapper.toDto(aboutus);
        restAboutusMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isCreated());

        // Validate the Aboutus in the database
        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeCreate + 1);
        Aboutus testAboutus = aboutusList.get(aboutusList.size() - 1);
        assertThat(testAboutus.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAboutus.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testAboutus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAboutusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aboutusRepository.findAll().size();

        // Create the Aboutus with an existing ID
        aboutus.setId(1L);
        AboutusDTO aboutusDTO = aboutusMapper.toDto(aboutus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAboutusMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aboutus in the database
        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkImageIsRequired() throws Exception {
        int databaseSizeBeforeTest = aboutusRepository.findAll().size();
        // set the field null
        aboutus.setImage(null);

        // Create the Aboutus, which fails.
        AboutusDTO aboutusDTO = aboutusMapper.toDto(aboutus);


        restAboutusMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isBadRequest());

        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = aboutusRepository.findAll().size();
        // set the field null
        aboutus.setDescription(null);

        // Create the Aboutus, which fails.
        AboutusDTO aboutusDTO = aboutusMapper.toDto(aboutus);


        restAboutusMockMvc.perform(post("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isBadRequest());

        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAboutuses() throws Exception {
        // Initialize the database
        aboutusRepository.saveAndFlush(aboutus);

        // Get all the aboutusList
        restAboutusMockMvc.perform(get("/api/aboutuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aboutus.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getAboutus() throws Exception {
        // Initialize the database
        aboutusRepository.saveAndFlush(aboutus);

        // Get the aboutus
        restAboutusMockMvc.perform(get("/api/aboutuses/{id}", aboutus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aboutus.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingAboutus() throws Exception {
        // Get the aboutus
        restAboutusMockMvc.perform(get("/api/aboutuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAboutus() throws Exception {
        // Initialize the database
        aboutusRepository.saveAndFlush(aboutus);

        int databaseSizeBeforeUpdate = aboutusRepository.findAll().size();

        // Update the aboutus
        Aboutus updatedAboutus = aboutusRepository.findById(aboutus.getId()).get();
        // Disconnect from session so that the updates on updatedAboutus are not directly saved in db
        em.detach(updatedAboutus);
        updatedAboutus
            .title(UPDATED_TITLE)
            .image(UPDATED_IMAGE)
            .description(UPDATED_DESCRIPTION);
        AboutusDTO aboutusDTO = aboutusMapper.toDto(updatedAboutus);

        restAboutusMockMvc.perform(put("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isOk());

        // Validate the Aboutus in the database
        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeUpdate);
        Aboutus testAboutus = aboutusList.get(aboutusList.size() - 1);
        assertThat(testAboutus.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAboutus.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testAboutus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAboutus() throws Exception {
        int databaseSizeBeforeUpdate = aboutusRepository.findAll().size();

        // Create the Aboutus
        AboutusDTO aboutusDTO = aboutusMapper.toDto(aboutus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAboutusMockMvc.perform(put("/api/aboutuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aboutusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aboutus in the database
        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAboutus() throws Exception {
        // Initialize the database
        aboutusRepository.saveAndFlush(aboutus);

        int databaseSizeBeforeDelete = aboutusRepository.findAll().size();

        // Delete the aboutus
        restAboutusMockMvc.perform(delete("/api/aboutuses/{id}", aboutus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Aboutus> aboutusList = aboutusRepository.findAll();
        assertThat(aboutusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
