package com.nikolaev.document;

import com.nikolaev.review.ReviewService;
import com.nikolaev.review.dto.ReviewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/api")
public class DocumentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ReviewService reviewService;

//    @RequestMapping(value = "/conferences/{conferenceId}/submissions/{submissionId}/documents", method = RequestMethod.POST,
//            consumes = {"multipart/form-data"})
//    public void submissionUpload(@RequestParam("file") MultipartFile file,
//                                 @PathVariable("submissionId") Long submissionId,
//                                 @PathVariable("conferenceId") Long conferenceId) {
//        logger.info("in submissionUpload()");
//        logger.info("in submissionUpload()" + file.getOriginalFilename());
//        documentService.save(file, submissionId);
//
//    }

    @RequestMapping(value = "/documents/{documentId}", method = RequestMethod.GET)
    public ResponseEntity getDocument(@PathVariable("documentId") Long documentId) {
        return ResponseEntity.ok(documentService.getDocument(documentId));
    }

    @RequestMapping(value = "/documents/{documentId}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable("documentId") Long documentId) {
        documentService.deleteDocument(documentId);
    }

    @RequestMapping(value = "/documents/{documentId}/download",
            method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public void downloadDocument(@PathVariable("documentId") Long documentId, HttpServletResponse response) throws IOException {
        Document document = documentService.downloadDocument(documentId);
//        String mimeType = URLConnection.guessContentTypeFromName(document.getFilename());
//        logger.debug("mimetype: " + mimeType);
        response.setContentType("application/octet-stream");
        logger.debug("download filename: " + document.getFilename());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + rusToEngTranlit(document.getFilename()) + "\""));
        InputStream inputStream = new ByteArrayInputStream(document.getData());
        FileCopyUtils.copy(inputStream, response.getOutputStream());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
//        headers.setContentDispositionFormData(document.getFilename(), document.getFilename());
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        return new ResponseEntity<>(document.getData(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/documents/{documentId}/reviews", method = RequestMethod.POST)
    public void createReview(@PathVariable("documentId") Long documentId,
                             @RequestBody ReviewDto reviewDto) {
        this.reviewService.createReview(documentId, reviewDto);
    }

    private static String rusToEngTranlit(String text) {
        char[] abcCyr = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', ' '};
        String[] abcLat = {"a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "kh", "ts", "ch", "sh", "sch", "", "y", "", "e", "yu", "ya", "_"};

        StringBuilder builder = new StringBuilder();
        text = text.toLowerCase();
        outer:
        for (int i = 0; i < text.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++) {
                if (text.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                    continue outer;
                }
            }
            builder.append(text.charAt(i));
        }
        return builder.toString();
    }


}
