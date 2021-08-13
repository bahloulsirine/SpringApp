//package com.springapp.firstapp.service;
//
//import com.springapp.firstapp.module.NotificationEmail;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
//
//
//@Service
//@AllArgsConstructor
//
//public class MailService {
//    private final JavaMailSender mailSender;
//    private final MailContentBuilder mailContentBuilder;
//
//    @Async
//    void sendMail(NotificationEmail notificationEmail) {
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setFrom("springreddit@email.com");
//            messageHelper.setTo(notificationEmail.getRecipient());
//            messageHelper.setSubject(notificationEmail.getSubject());
//            messageHelper.setText(notificationEmail.getBody());
//        };
//        try {
//            mailSender.send(messagePreparator);
//            log.info("Activation email sent!!");
//        } catch (MailException e) {
//            log.error("Exception occurred when sending mail", e);
//            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
//        }
//    }
//}
