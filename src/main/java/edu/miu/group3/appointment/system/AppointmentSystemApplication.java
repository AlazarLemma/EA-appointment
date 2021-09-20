package edu.miu.group3.appointment.system;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.integration.Job.AppointmentReminder;
import edu.miu.group3.appointment.system.integration.Job.Status;
import edu.miu.group3.appointment.system.integration.email.EmailRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.Date;

@EnableJms
@SpringBootApplication
@EnableScheduling
public class AppointmentSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppointmentSystemApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending an email message.");
		jmsTemplate.convertAndSend("mailbox",
				new AppointmentReminder(1L, "yordan.desta@gmail.com",
						new Appointment(), Status.PENDING,(LocalDateTime.now()) ,
						new EmailRequest(), 12));
	}

}
