package com.nikolaev.review;

import com.nikolaev.document.Document;
import com.nikolaev.document.DocumentRepository;
import com.nikolaev.review.dto.ReviewDto;
import com.nikolaev.review.dto.ReviewMapper;
import com.nikolaev.review.status.ReviewStatus;
import com.nikolaev.review.status.ReviewStatusName;
import com.nikolaev.review.status.ReviewStatusRepository;
import com.nikolaev.submission.Submission;
import com.nikolaev.submission.SubmissionRepository;
import com.nikolaev.submission.SubmissionServiceImpl;
import com.nikolaev.submission.status.SubmissionStatusName;
import com.nikolaev.submission.status.SubmissionStatusRepository;
import com.nikolaev.submission_role.SubmissionRoleName;
import com.nikolaev.user.User;
import com.nikolaev.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender emailSender;

    private ReviewRepository reviewRepository;
    private DocumentRepository documentRepository;
    private ReviewStatusRepository reviewStatusRepository;
    private UserRepository userRepository;
    private SubmissionStatusRepository submissionStatusRepository;
    private SubmissionRepository submissionRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             DocumentRepository documentRepository,
                             ReviewStatusRepository reviewStatusRepository,
                             UserRepository userRepository,
                             SubmissionStatusRepository submissionStatusRepository,
                             SubmissionRepository submissionRepository) {
        this.reviewRepository = reviewRepository;
        this.documentRepository = documentRepository;
        this.reviewStatusRepository = reviewStatusRepository;
        this.userRepository = userRepository;
        this.submissionStatusRepository = submissionStatusRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public ReviewDto getReview(Long id) {
        return ReviewMapper.toDto(this.reviewRepository.getOne(id));
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        Review review = this.reviewRepository.getOne(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setEvaluation(reviewDto.getEvaluation());
        review.setDate(reviewDto.getDate());
        review.setStatus(reviewStatusRepository.findByName(ReviewStatusName.fromInt(reviewDto.getStatus())));
        review.setSubmitted(reviewDto.isSubmitted());

        return ReviewMapper.toDto(this.reviewRepository.save(review));
    }

    @Override
    public ReviewDto submitReview(Long reviewId) {
        Review review = this.reviewRepository.getOne(reviewId);
        review.setSubmitted(true);
        ReviewDto reviewDto = ReviewMapper.toDto(this.reviewRepository.save(review));

        Document document = review.getDocument();
        int reviewerCount = (int) document.getSubmission().getSubmissionUserRoles().stream().filter(submissionUserRoles -> submissionUserRoles.getRole().getName().equals(SubmissionRoleName.REVIEWER)).count();
        if (document.getReviews().stream().allMatch(Review::isSubmitted) && document.getReviews().size() == reviewerCount) {
            Optional<Review> min = document.getReviews().stream().min((Comparator.comparingInt(o -> o.getStatus().getName().getValue())));
            if (min.isPresent() && min.get().getStatus().getName().getValue() <= ReviewStatusName.PROBABLY_ACCEPT.getValue()) {
                document.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.REJECT));
                Submission submission = document.getSubmission();
                submission.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.REJECT));


                submissionRepository.save(submission);
                documentRepository.save(document);
                this.sendRejectEmail(submission.getAuthor(), submission);
            } else if (min.isPresent() && min.get().getStatus().getName().getValue() == ReviewStatusName.ACCEPT.getValue()) {
                document.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.ACCEPT));
                Submission submission = document.getSubmission();
                submission.setStatus(submissionStatusRepository.findByName(SubmissionStatusName.ACCEPT));

                submissionRepository.save(submission);
                documentRepository.save(document);
                this.sendSubmitEmail(submission.getAuthor(), submission);
            }
        }

        return reviewDto;
    }

    @Override
    public void deleteReview(Long reviewId) {
        this.reviewRepository.deleteById(reviewId);
    }

    @Override
    public void createReview(Long documentId, ReviewDto reviewDto) {
        Document document = this.documentRepository.getOne(documentId);
        ReviewStatus status = this.reviewStatusRepository.findByName(ReviewStatusName.fromInt(reviewDto.getStatus()));
        User author = this.userRepository.findByUsername(reviewDto.getAuthor().getUsername());
        Review review = new Review();

        review.setDocument(document);
        review.setStatus(status);
        review.setAuthor(author);

        review.setSubmitted(false);
        review.setTitle("Review");
        review.setEvaluation(reviewDto.getEvaluation());
        review.setDate(reviewDto.getDate());

        this.reviewRepository.save(review);
    }

    private void sendSubmitEmail(User user, Submission submission) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Оповещение");
        message.setText("Здравствуйте. Ваш доклад " + submission.getTitle() + " был успешно принят.\n");
        emailSender.send(message);
    }

    private void sendRejectEmail(User user, Submission submission) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Оповещение");
        message.setText("Здравствуйте. Ваш доклад \"" + submission.getTitle() + "\" был отклонен. Вы можете загрузить новую версию.\n");
        emailSender.send(message);
    }

}
