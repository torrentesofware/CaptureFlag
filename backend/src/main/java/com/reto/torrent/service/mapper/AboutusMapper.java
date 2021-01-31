package com.reto.torrent.service.mapper;


import com.reto.torrent.domain.*;
import com.reto.torrent.service.dto.AboutusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Aboutus} and its DTO {@link AboutusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AboutusMapper extends EntityMapper<AboutusDTO, Aboutus> {



    default Aboutus fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aboutus aboutus = new Aboutus();
        aboutus.setId(id);
        return aboutus;
    }
}
