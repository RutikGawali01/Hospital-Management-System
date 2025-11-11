INSERT INTO patient(name, gender, birth_date, email, blood_group, phone, age, address) VALUES
('Aarav Sharma', 'MALE', '1990-05-10', 'aarav.sharma@example.com', 'O_POSSITIVE', 9876543210, 35, 'Pune, Maharashtra'),
('Diya Patel', 'FEMALE', '1995-08-20', 'diya.patel@example.com', 'A_POSSITIVE', 9123456780, 30, 'Mumbai, Maharashtra'),
('Dishant Verma', 'MALE', '1988-03-15', 'dishant.verma@example.com', 'A_POSSITIVE', 9988776655, 37, 'Delhi, India'),
('Neha Iyer', 'FEMALE', '1992-12-01', 'neha.iyer@example.com', 'AB_POSSITIVE', 9011223344, 32, 'Chennai, Tamil Nadu'),
('Kabir Singh', 'MALE', '1993-07-11', 'kabir.singh@example.com', 'O_POSSITIVE', 9090909090, 31, 'Bengaluru, Karnataka');



INSERT INTO hospital (username, name, email, password, license_no, owner_name)
VALUES
('hospital1', 'City Hospital', 'cityhospital@example.com', 'encoded_password_1', 'LIC12345', 'Dr. Smith'),
('hospital2', 'Green Valley Hospital', 'greenvalley@example.com', 'encoded_password_2', 'LIC67890', 'Dr. John'),
('hospital3', 'Sunrise Hospital', 'sunrise@example.com', 'encoded_password_3', 'LIC54321', 'Dr. Mary'),
('hospital4', 'Moonlight Hospital', 'moonlight@example.com', 'encoded_password_4', 'LIC98765', 'Dr. Alice');

-- Doctor table inserts
INSERT INTO doctor (name, specialization, email, age, gender, phone, experience, address, qualifications, hospital_id)
VALUES
('Dr. Rakesh', 'Cardiology', 'rakesh1@gmail.com', 45, 'Male', 9876543210, '15 years', 'Pune, India', 'MBBS, MD', 1),
('Dr. Sneha', 'Dermatology', 'sneha35@gmail.com', 38, 'Female', 9876543211, '10 years', 'Mumbai, India', 'MBBS, DVD', 1),
('Dr. Arjun', 'Orthopedics', 'arjun67@gmail.com', 50, 'Male', 9876543212, '20 years', 'Delhi, India', 'MBBS, MS Ortho', 2);



insert into appointment (appointment_time, reason, status, doctor_id, patient_id)
VALUES
('2025-07-01 10:30:00','General Checkup', 'sheduled',1,2),
('2025-07-02 11:30:00','Skin Rash' , 'sheduled',2,2),
('2025-07-03 12:30:00','Knee pain' , 'sheduled' ,3,2),
('2025-07-04 13:30:00','follow-up visit' , 'sheduled',1,1),
('2025-07-05 14:30:00','Consultation', 'sheduled',1,4),
('2025-07-06 15:30:00','allergy treatment', 'sheduled',2,5);
