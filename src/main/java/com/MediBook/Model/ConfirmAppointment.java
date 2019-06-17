package com.MediBook.Model;

import java.sql.Date;
//Sarmad Noor
//This layer will have all the model for application.
public class ConfirmAppointment
{
    public String appt_Status;
    public String address;
    public String appt_Date;
    public Date appointmentDate;
    public int slot_Id;
    public int patient_Id;
    public int doctor_Id;
    public String problemDesc;
    public DocAvailabilty starting_Slot;

    public class DocAvailabilty 
    {
        public String startTimeSlot;
    }
}

