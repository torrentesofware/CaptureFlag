package com.reto.torrent.web.rest;

import com.reto.torrent.service.AboutusService;
import com.reto.torrent.web.rest.errors.BadRequestAlertException;
import com.reto.torrent.service.dto.AboutusDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.reto.torrent.domain.Aboutus}.
 */
@RestController
@RequestMapping("/api")
public class AboutusResource {

    private final Logger log = LoggerFactory.getLogger(AboutusResource.class);

    private static final String ENTITY_NAME = "aboutus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AboutusService aboutusService;

    public AboutusResource(AboutusService aboutusService) {
        this.aboutusService = aboutusService;
    }

    /**
     * {@code POST  /aboutuses} : Create a new aboutus.
     *
     * @param aboutusDTO the aboutusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aboutusDTO, or with status {@code 400 (Bad Request)} if the aboutus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aboutuses")
    public ResponseEntity<AboutusDTO> createAboutus(@Valid @RequestBody AboutusDTO aboutusDTO) throws URISyntaxException {
        log.debug("REST request to save Aboutus : {}", aboutusDTO);
        if (aboutusDTO.getId() != null) {
            throw new BadRequestAlertException("A new aboutus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AboutusDTO result = aboutusService.save(aboutusDTO);
        return ResponseEntity.created(new URI("/api/aboutuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aboutuses} : Updates an existing aboutus.
     *
     * @param aboutusDTO the aboutusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aboutusDTO,
     * or with status {@code 400 (Bad Request)} if the aboutusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aboutusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aboutuses")
    public ResponseEntity<AboutusDTO> updateAboutus(@Valid @RequestBody AboutusDTO aboutusDTO) throws URISyntaxException {
        log.debug("REST request to update Aboutus : {}", aboutusDTO);
        if (aboutusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AboutusDTO result = aboutusService.save(aboutusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, aboutusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aboutuses} : get all the aboutuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aboutuses in body.
     */
    @GetMapping("/aboutuses")
    public ResponseEntity<List<AboutusDTO>> getAllAboutuses(Pageable pageable) {
        log.debug("REST request to get a page of Aboutuses");
        Page<AboutusDTO> page = aboutusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aboutuses/:id} : get the "id" aboutus.
     *
     * @param id the id of the aboutusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aboutusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aboutuses/{id}")
    public ResponseEntity<AboutusDTO> getAboutus(@PathVariable Long id) {
        log.debug("REST request to get Aboutus : {}", id);
        Optional<AboutusDTO> aboutusDTO = aboutusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aboutusDTO);
    }

    /**
     * {@code DELETE  /aboutuses/:id} : delete the "id" aboutus.
     *
     * @param id the id of the aboutusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aboutuses/{id}")
    public ResponseEntity<Void> deleteAboutus(@PathVariable Long id) {
        log.debug("REST request to delete Aboutus : {}", id);
        aboutusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
