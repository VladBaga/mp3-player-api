package org.fasttrackit.mp3playerapi.persistence;

import org.fasttrackit.mp3playerapi.domain.Mp3File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mp3FileRepository extends JpaRepository<Mp3File, Long> {

    Page<Mp3File> findById(long mp3FileId, Pageable pageable);
    Page<Mp3File> findByName(String name, Pageable pageable);
}