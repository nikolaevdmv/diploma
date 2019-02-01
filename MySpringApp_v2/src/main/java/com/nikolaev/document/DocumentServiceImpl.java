package com.nikolaev.document;

import com.nikolaev.document.dto.DocumentDto;
import com.nikolaev.document.dto.DocumentMapper;
import com.nikolaev.submission.SubmissionRepository;
import com.nikolaev.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public DocumentDto getDocument(Long id) {
        return DocumentMapper.toDto(documentRepository.getOne(id));
    }

    @Override
    public Document downloadDocument(Long id) {
        return documentRepository.getOne(id);
    }

    @Override
    public void deleteDocument(Long documentId) {
        if (!documentRepository.getOne(documentId).getSubmission().isReviewable()) {
            documentRepository.deleteById(documentId);
        }
    }
}
