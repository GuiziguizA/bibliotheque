package sid.org.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ListenerImpl extends
JobExecutionListenerSupport {

private static final Logger log = LoggerFactory.getLogger(ListenerImpl.class);

@Autowired
private JavaMailSender javaMailSender;

@Autowired
private SimpleMailMessage templateMessage;

@Override
public void afterJob(JobExecution jobExecution) {
if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
    log.info("!!! JOB FINISHED! Time to verify the results");
    SimpleMailMessage mailMessage = new SimpleMailMessage(
            templateMessage);
    mailMessage.setText("Job Success");
    javaMailSender.send(mailMessage);
}
}
}
