package edu.miu.group3.appointment.system.integration.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class EmailTemplate implements Serializable {
    private static final long serialVersionUID = 3L;

    private String from;

    private String to;

    private String body;
}
