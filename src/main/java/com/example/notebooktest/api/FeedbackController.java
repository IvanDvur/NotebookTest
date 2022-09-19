package com.example.notebooktest.api;

import com.example.notebooktest.api.Dto.FeedbackDto;
import com.example.notebooktest.mail.FeedbackSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;

@RestController
@CrossOrigin
@RequestMapping("/api/feedback")
public class FeedbackController {
    private FeedbackSender feedbackSender;
    public FeedbackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }
    @PostMapping
    public void sendFeedback(@RequestBody FeedbackDto feedbackDto, BindingResult bindingResult)
            throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback has Errors");
        }

        this.feedbackSender.sendFeedback(
                feedbackDto.getEmail(),
                feedbackDto.getName(),
                feedbackDto.getFeedback());

    }
}
