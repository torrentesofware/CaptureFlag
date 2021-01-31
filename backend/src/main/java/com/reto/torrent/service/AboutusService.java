package com.reto.torrent.service;

import com.reto.torrent.domain.Aboutus;
import com.reto.torrent.repository.AboutusRepository;
import com.reto.torrent.service.dto.AboutusDTO;
import com.reto.torrent.service.mapper.AboutusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Aboutus}.
 */
@Service
@Transactional
public class AboutusService {

    private final Logger log = LoggerFactory.getLogger(AboutusService.class);

    private final AboutusRepository aboutusRepository;

    private final AboutusMapper aboutusMapper;

    public AboutusService(AboutusRepository aboutusRepository, AboutusMapper aboutusMapper) {
        this.aboutusRepository = aboutusRepository;
        this.aboutusMapper = aboutusMapper;
    }

    /**
     * Save a aboutus.
     *
     * @param aboutusDTO the entity to save.
     * @return the persisted entity.
     */
    public AboutusDTO save(AboutusDTO aboutusDTO) {
        log.debug("Request to save Aboutus : {}", aboutusDTO);
        Aboutus aboutus = aboutusMapper.toEntity(aboutusDTO);
        aboutus = aboutusRepository.save(aboutus);
        return aboutusMapper.toDto(aboutus);
    }

    /**
     * Get all the aboutuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AboutusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Aboutuses");
        return aboutusRepository.findAll(pageable)
            .map(aboutusMapper::toDto);
    }


    /**
     * Get one aboutus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AboutusDTO> findOne(Long id) {
        log.debug("Request to get Aboutus : {}", id);
        return aboutusRepository.findById(id)
            .map(aboutusMapper::toDto);
    }

    /**
     * Delete the aboutus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Aboutus : {}", id);
        aboutusRepository.deleteById(id);
    }
}
