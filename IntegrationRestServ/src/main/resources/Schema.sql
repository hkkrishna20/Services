
CREATE TABLE `enfiletable` (
	`fileid` int(15) NOT NULL DEFAULT 100,
	`formid` varchar(255) NOT NULL,
	`formtitle` varchar(255) NOT NULL,
	`versionno` varchar(255) NOT NULL,
 	 PRIMARY KEY (fileid)
);
 
CREATE TABLE `encontenttable` (
	`contentid` int(15) NOT NULL,
	`paraseq` varchar(20) NOT NULL,
	`content` TEXT NOT NULL,
	`fileid` int(15) NOT NULL DEFAULT 100,
	
	PRIMARY KEY (`contentid`),	
	KEY pkey (fileid),
	CONSTRAINT FOREIGN KEY  (`fileid`) REFERENCES `enfiletable`(`fileid`) ON DELETE CASCADE ON UPDATE CASCADE
);
  


CREATE TABLE formDetails (
  templateID VARCHAR(100) NOT NULL,
  formID VARCHAR(100) NOT NULL,
  formTitle VARCHAR(100) NOT NULL,
  formSeq VARCHAR(100) NOT NULL,
  PRIMARY KEY (templateID));
  
  
CREATE TABLE `login`(
`userId`  VARCHAR(45) NOT NULL, 
`password`  VARCHAR(45) NULL,                
PRIMARY KEY (`userId`));
                 


 CREATE TABLE `register` ( 
 `user_id`  VARCHAR(45) NOT NULL, 
 `name`  VARCHAR(45) NOT NULL, 
 `email`     VARCHAR(45) NULL, 
 `password`   VARCHAR(45) NULL, 
 PRIMARY KEY (`user_id`) 
 FOREIGN KEY (user_id) REFERENCES login(userId));
   

create table Audit (
   reqid VARCHAR(255) NOT NULL ,
   created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   sourcecorelid  VARCHAR(255) NOT NULL,
    processfamily  VARCHAR(255) NOT NULL,
	 content  LONGTEXT NOT NULL,
   PRIMARY KEY (reqid)
);
 
create table AuditPoint (
   id VARCHAR(255)  NOT NULL AUTO_INCREMENT,
   created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   reqid_FK VARCHAR(255) NOT NULL,
   transXML LONGTEXT NOT NULL,
   auditstagecode  VARCHAR(255) NOT NULL,
   status   VARCHAR(255) NOT NULL,
   PRIMARY KEY (id),
     FOREIGN KEY (reqid_FK) REFERENCES Audit(reqid)
);

create table metadata (
   reqid VARCHAR(255) NOT NULL ,
   created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   sourcecorelid  VARCHAR(255) NOT NULL,
   processfamily VARCHAR(255) NOT NULL,
    accountNo  VARCHAR(255) NOT NULL,
	 userID  VARCHAR(255) NOT NULL,
   PRIMARY KEY (reqid)
);
 
create table metadataDocument (
   docID VARCHAR(255)  NOT NULL ,
   created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   updated_at  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   reqid_FK VARCHAR(255) NOT NULL,
   docpath TEXT NOT NULL,
   ecmpath  TEXT NOT NULL,
   filenetpath  TEXT NOT NULL,
   PRIMARY KEY (docID),
     FOREIGN KEY (reqid_FK) REFERENCES metadata(reqid)
   
);