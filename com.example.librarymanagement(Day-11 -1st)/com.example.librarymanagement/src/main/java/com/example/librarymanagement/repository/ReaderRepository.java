// ReaderRepository.java
package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {}
