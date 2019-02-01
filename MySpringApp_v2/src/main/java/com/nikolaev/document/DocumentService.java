package com.nikolaev.document;

import com.nikolaev.document.dto.DocumentDto;

public interface DocumentService {
    DocumentDto getDocument(Long id);
    Document downloadDocument(Long id);

    void deleteDocument(Long documentId);
}
