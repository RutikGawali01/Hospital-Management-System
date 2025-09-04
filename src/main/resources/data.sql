insert into patient(name, gender, birth_date, email, blood_group) values
('Aarav Sharma','MALE','1990-05-10','aarav.sharma@example.com','O_POSSITIVE'),
('Diya Patel','FEMALE','1995-08-20','diya.patel@example.com','A_POSSITIVE'),
('Dishant Verma','MALE','1988-03-15','dishant.verma@example.com','A_POSSITIVE'),
('Neha Iyer','FEMALE','1992-12-01','neha.iyer@example.com','AB_POSSITIVE'),
('Kabir Singh','MALE','1993-07-11','kabir.singh@example.com','O_POSSITIVE');

-- Doctor table inserts
INSERT INTO doctor (name, email, specialization)
VALUES
('DR. Rakesh', 'rakesh1@gmail.com', 'Cardiology'),
('Dr. Sneah', 'sneha35@gmail.com', 'Dermatology'),
('Dr. Arjun', 'arjun67@gmail.com', 'Orthopedics');


insert into appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
('2025-07-01 10:30:00','General Checkup',1,2),
('2025-07-02 11:30:00','Skin Rash',2,2),
('2025-07-03 12:30:00','Knee pain',3,2),
('2025-07-04 13:30:00','follow-up visit',1,1),
('2025-07-05 14:30:00','Consultation',1,4),
('2025-07-06 15:30:00','allergy treatment',2,5);
