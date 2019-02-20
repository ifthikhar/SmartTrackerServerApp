package com.mwsgateway.apigateway.api;

import com.mwsgateway.apigateway.api.viewmodel.FeedbackViewModel;
import com.mwsgateway.apigateway.mail.FeedbackSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;

/*
Requests can be tested using the built in HTTP Rest Client. Use the
examples found in 'noteit.http' file.
 */

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {
    private FeedbackSender feedbackSender;

    public FeedbackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }
// RequestBody converts the data in json format to java objects
    @PostMapping
    public void sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback has errors; Can not send feedback;");
        }

        this.feedbackSender.sendFeedback(
                feedbackViewModel.getEmail(),
                feedbackViewModel.getName(),
                feedbackViewModel.getFeedback());
    }
}
