package com.nikolaev.document.dto;

import com.nikolaev.document.Document;
import com.nikolaev.review.dto.ReviewMapper;
import com.nikolaev.submission.status.SubmissionStatus;

import java.util.List;
import java.util.stream.Collectors;


public final class DocumentMapper {
    private DocumentMapper() {
    }

    public static DocumentDto toDto(Document entity) {
        return new DocumentDto(
                entity.getId(),
                entity.getFilename(),
                mapStatus(entity.getStatus()),
                ReviewMapper.toListBriefDto(entity.getReviews()),
                entity.getSubmission().getId()
        );
    }

    private static int mapStatus(SubmissionStatus status) {
        return status.getName().getValue();
    }


    public static List<DocumentDto> toListDto(List<Document> documents) {
        return documents.stream().map(DocumentMapper::toDto).collect(Collectors.toList());
    }
}
