-- create a new database named SecureMedicalRecords
CREATE SCHEMA SecureMedicalRecords;

-- uses SecureMedicalRecords database
USE SecureMedicalRecords;

-- Create the info table to store data
CREATE TABLE info (
    Age INT NOT NULL,                -- Age of the patient // not null to ensure its not left empty
    Weight INT NOT NULL,             -- Weight of the patient 
    Height INT NOT NULL,             -- Height of the patient 
    Gender VARCHAR(10),              -- Gender of the patient 
    Doctor VARCHAR(50)               -- Name of the doctor 
);

-- Add a new column to the info table for Medical History
ALTER TABLE SecureMedicalRecords.info
ADD Medical_History VARCHAR(50);  -- Medical history of the patient 

-- changes existing columns to change their data types and sizes to store the encrypted data
ALTER TABLE info
MODIFY Age VARCHAR(150) NOT NULL,             
MODIFY Weight VARCHAR(150) NOT NULL,          
MODIFY Height VARCHAR(150) NOT NULL,          
MODIFY Gender VARCHAR(50),                    
MODIFY Doctor VARCHAR(150),                  
MODIFY Medical_History VARCHAR(500);         

-- Disable foreign key checks to avoid issues while truncating tables
SET FOREIGN_KEY_CHECKS = 0;

-- Empty the data from the info table by removing all rows but keeping the table structure // for testing purposes
TRUNCATE TABLE info;

-- Enable foreign key checks again after truncating the table
SET FOREIGN_KEY_CHECKS = 1;
