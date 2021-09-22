package edu.miu.group3.appointment.system.integration.email;

public enum EmailType {
    APPOINTMENT_CONFIRMATION{
        @Override
        public String getDefaultSubject() {
            return "Appointment Confirmed";
        }
    }, RESERVATION_CONFIRMATION {
        @Override
        public String getDefaultSubject() {
            return "Reservation Booked";
        }
    }, APPOINTMENT_REMINDER{
        @Override
        public String getDefaultSubject() {
            return "Reminder for your appointment";
        }
    }, RESERVATION_CANCELED {
        @Override
        public String getDefaultSubject() {
            return "Appointment canceled";}
    };

    public abstract String getDefaultSubject();
}
