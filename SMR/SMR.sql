create schema SecureMedicalRecords;

use SecureMedicalRecords;
create table info(
    Age int NOT NULL,
    Weight int not null,
    Height int NOT NULL,
    Gender varchar(10),
    Doctor Varchar(50)
);
ALTER TABLE SecureMedicalRecords.info
ADD Medical_History VARCHAR(50);

ALTER TABLE info
MODIFY Age VARCHAR(150) NOT NULL,
MODIFY Weight VARCHAR(150) NOT NULL,
MODIFY Height VARCHAR(150) NOT NULL,
MODIFY Gender VARCHAR(50),
MODIFY Doctor VARCHAR(150),
MODIFY Medical_History VARCHAR(500);