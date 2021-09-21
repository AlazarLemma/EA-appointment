package edu.miu.group3.appointment.system.integration.email;

public enum Type {
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
    };

    public abstract String getDefaultSubject();
}
