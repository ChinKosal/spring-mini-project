    package com.example.minispring.service.Implementation;

    import org.springframework.mail.SimpleMailMessage;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.stereotype.Service;

    import com.example.minispring.service.EmailSenderService;
    @Service
    public class EmailSenderServiceImp implements EmailSenderService { 
        private JavaMailSender mailSender;

        public EmailSenderServiceImp(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }
        public void sendEmail(String toEmail, String subject, String text){
            SimpleMailMessage message  = new SimpleMailMessage();
            message.setFrom("springminiproject@gmail.com");
            message.setTo(toEmail);
            message.setText(text);
            message.setSubject(subject);

            mailSender.send(message);

            System.out.println("message send ...");
            
        }

    }
