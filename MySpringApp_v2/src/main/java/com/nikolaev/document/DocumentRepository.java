package com.nikolaev.document;


import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    @Override
    Document getOne(Long id);
}
