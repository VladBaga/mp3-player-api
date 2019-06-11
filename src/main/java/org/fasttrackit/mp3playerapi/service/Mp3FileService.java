package org.fasttrackit.mp3playerapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.mp3playerapi.domain.Mp3File;
import org.fasttrackit.mp3playerapi.exception.ResourceNotFoundException;
import org.fasttrackit.mp3playerapi.persistence.Mp3FileRepository;
import org.fasttrackit.mp3playerapi.transfer.CreateMp3FileRequest;
import org.fasttrackit.mp3playerapi.transfer.GetMp3FilesRequest;
import org.fasttrackit.mp3playerapi.transfer.Mp3FileResponse;
import org.fasttrackit.mp3playerapi.transfer.UpdateMp3FileRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mp3FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mp3FileService.class);

    private final Mp3FileRepository mp3FileRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public Mp3FileService(Mp3FileRepository mp3FileRepository, ObjectMapper objectMapper) throws ResourceNotFoundException {
        this.mp3FileRepository = mp3FileRepository;
        this.objectMapper = objectMapper;
    }

    public Mp3File createMp3File(CreateMp3FileRequest request) {
        LOGGER.info("Uploading mp3 {}", request);
        Mp3File mp3File = objectMapper.convertValue(request, Mp3File.class);
        return mp3FileRepository.save(mp3File);
    }

    public Mp3File getMp3File(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving mp3 {}", id);
        return mp3FileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File " + id + "not found"));
    }

    public Page<Mp3FileResponse> getMp3Files(GetMp3FilesRequest request, Pageable pageable) {
        LOGGER.info("Retrieving mp3's >> {}", request);

        Page<Mp3File> mp3Files;

        if (request.getName() != null) {
            mp3Files = mp3FileRepository.findByName(request.getName(), pageable);
        }else{
            mp3Files = mp3FileRepository.findAll(pageable);
        }

        List<Mp3FileResponse> mp3FileResponses = new ArrayList<>();

        for (Mp3File mp3File : mp3Files.getContent()){

            Mp3FileResponse mp3FileResponse = new Mp3FileResponse();
            mp3FileResponse.setId(mp3File.getId());
            mp3FileResponse.setName(mp3File.getName());

            mp3FileResponses.add(mp3FileResponse);
        }
        return new PageImpl<>(mp3FileResponses, pageable, mp3Files.getTotalElements());
    }

    public Mp3File updateMp3File(long id, UpdateMp3FileRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating mp3 {}, {}", id, request);
        Mp3File mp3File = getMp3File(id);

        BeanUtils.copyProperties(request, mp3File);

        return mp3FileRepository.save(mp3File);
    }

    public void deleteMp3File(long id) {
        LOGGER.info("Deleting mp3 {}", id);

        mp3FileRepository.deleteById(id);

        LOGGER.info("Deleted mp3 {}", id);
    }
}
