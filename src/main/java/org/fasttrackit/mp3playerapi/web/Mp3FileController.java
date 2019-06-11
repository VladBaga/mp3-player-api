package org.fasttrackit.mp3playerapi.web;

import org.fasttrackit.mp3playerapi.domain.Mp3File;
import org.fasttrackit.mp3playerapi.exception.ResourceNotFoundException;
import org.fasttrackit.mp3playerapi.service.Mp3FileService;
import org.fasttrackit.mp3playerapi.transfer.CreateMp3FileRequest;
import org.fasttrackit.mp3playerapi.transfer.GetMp3FilesRequest;
import org.fasttrackit.mp3playerapi.transfer.Mp3FileResponse;
import org.fasttrackit.mp3playerapi.transfer.UpdateMp3FileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mp3files")
@CrossOrigin
public class Mp3FileController {

    private final Mp3FileService mp3FileService;

    @Autowired
    public Mp3FileController(Mp3FileService mp3FileService) {
        this.mp3FileService = mp3FileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity <Mp3File> getMp3File(@PathVariable("id") long id) throws ResourceNotFoundException {
        Mp3File response = mp3FileService.getMp3File(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <Mp3File> createMp3File(@RequestBody @Valid CreateMp3FileRequest request){
        Mp3File response = mp3FileService.createMp3File(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMp3File (@PathVariable("id") long id, @RequestBody @Valid UpdateMp3FileRequest request) throws ResourceNotFoundException {
        mp3FileService.updateMp3File(id,request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMp3File(@PathVariable("id") long id){
        mp3FileService.deleteMp3File(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Mp3FileResponse>> getMp3Files(@Valid GetMp3FilesRequest request, Pageable pageable){
        Page<Mp3FileResponse> mp3FileResponse = mp3FileService.getMp3Files(request, pageable);
        return new ResponseEntity<>(mp3FileResponse, HttpStatus.OK);
    }
}