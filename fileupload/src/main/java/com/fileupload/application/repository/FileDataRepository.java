package com.fileupload.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.application.model.FileData;

public interface FileDataRepository extends JpaRepository<FileData,Long>{

	Optional<FileData> findByName(String fileName);
}
