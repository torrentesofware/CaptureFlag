package com.reto.torrent.repository;

import com.reto.torrent.domain.Aboutus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Aboutus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AboutusRepository extends JpaRepository<Aboutus, Long> {
}
