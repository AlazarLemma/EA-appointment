package edu.miu.group3.appointment.system.integration.email;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EmailLogs")
public class EmailTemplate implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column (name = "from_address")
    private String from;

    @Column (name = "to_address")
    private String to;

    @Column (name = "email_content")
    private String content;

    @Column (name = "subject")
    private String subject;

    @Column (name = "email_type")
    @Enumerated(EnumType.STRING)
    private Type emailType;

    @Column (name = "email_status")
    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    public EmailTemplate(String subject, String from, String to, String body, Type emailType){
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.content = body;
        this.emailType = emailType;
        this.status = EmailStatus.PENDING;
    }
}
