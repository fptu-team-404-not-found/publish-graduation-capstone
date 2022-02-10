﻿USE master
GO 


IF EXISTS (SELECT * FROM sys.databases WHERE name='SWP391')
DROP DATABASE SWP391
GO 
CREATE DATABASE SWP391
GO 
USE SWP391

------------------------------------------------------CREATE TABLE------------------------------------------------
CREATE TABLE Comment(
	CommentId INT IDENTITY(1,1),
	CommentDate datetime DEFAULT GetDate() NOT NULL,
	CommentContent NText,
	---------------
	UserId CHAR(21),
	PostId INT,
	ProjectId CHAR(8),
	PRIMARY KEY(CommentID)
) 
GO

CREATE TABLE User_Table(
	UserId CHAR(21),
	Email VARCHAR(50),
	Name NVARCHAR(50),
	Picture TEXT,
	----
	RoleId INT,
	PRIMARY KEY(UserId)
)
GO

CREATE TABLE Roles(
	RoleId INT IDENTITY(1,1),
	RoleName VARCHAR(20),
	PRIMARY KEY(RoleId)
)
GO

CREATE TABLE SharePost(
	PostId INT IDENTITY(1,1),
	Details NTEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	AuthorName NVARCHAR(50),
	Note NTEXT,
	-------------
	StateId INT,
	ProjectId CHAR(8),
	PRIMARY KEY(PostID)
)
GO

CREATE TABLE Project(
	ProjectId CHAR(8),
	ProjectName NVARCHAR(100),
	IntroductionContent NTEXT,
	Details TEXT,
	Semester CHAR(11),
	ProductURL TEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	ViewNumber INT,
	AuthorName NVARCHAR(50),
	Note NTEXT,
	-------------
	TeamID int,
	StateId INT,
	PRIMARY KEY(ProjectID)
)
GO
CREATE TABLE ProjectVideo(
	ProjectVideoId INT IDENTITY(1,1),
	VideoName NVARCHAR(100),
	Video TEXT,
	---------------------
	ProjectId CHAR(8),
	PRIMARY KEY(ProjectVideoId)
)
GO
CREATE TABLE ProjectImage(
	ProjectImageID INT IDENTITY(1,1),
	ImagesName NVARCHAR(100),
	Images varbinary(MAX),
	--------------------
	ProjectId CHAR(8),
	PRIMARY KEY(ProjectImageID)
)
GO

CREATE TABLE Team(
	TeamID INT IDENTITY(1,1),
	TeamName NVARCHAR(30),
	PRIMARY KEY(TeamID)
)
GO

CREATE TABLE TeamMember(
	MemberID CHAR(8),
	MemberName NVARCHAR(30),
	MemberAvatar varbinary(MAX),
	Email NVARCHAR(50),
	Phone CHAR(10),
	BackupEmail NVARCHAR(50),
	---------------------
	TeamID INT,
	PRIMARY KEY(MemberID)
)
GO

CREATE TABLE Team_Supervisor(
	TeamSupervisorID INT IDENTITY(1,1),
	TeamID INT,
	SupervisorID CHAR(5),
	PRIMARY KEY(TeamSupervisorID)
)
GO

CREATE TABLE Supervisor(
	SupervisorID CHAR(5),
	SupervisorName NVARCHAR(30),
	SupervisorImage varbinary(MAX),
	Information NVARCHAR(100),
	Position VARCHAR(20),
	PRIMARY KEY (SupervisorID)
)
GO

CREATE TABLE Favorite(
	FavoriteID 	INT IDENTITY(1,1),
	UserId		CHAR(21) ,
	ProjectId  	CHAR(8),
	PRIMARY KEY (FavoriteID)
)

CREATE TABLE Sensitive_word(
	wordID INT IDENTITY(1,1),
	banned_word NTEXT,
	PRIMARY KEY (wordID)
)
CREATE TABLE UpcomingProject(
	Id INT IDENTITY(1,1),
	ProjectName NVARCHAR(100),
	Location NVARCHAR(50),
	Date NVARCHAR(100),
	Description NVARCHAR(100),
	Image varbinary(MAX),
	PRIMARY KEY (Id)
)
CREATE TABLE States(
	StateId INT IDENTITY(1,1),
	StateName VARCHAR(10),
	PRIMARY KEY (StateId)
)


----------------------------------------ADD CONSTRAINT-----------------------------------------
---USER---
ALTER TABLE User_Table ADD CONSTRAINT has_Role
FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
GO 

----COMMNET----
ALTER TABLE Comment ADD CONSTRAINT has_User
FOREIGN KEY (UserId) REFERENCES User_Table(UserId)
GO 
ALTER TABLE Comment ADD CONSTRAINT has_Comment
FOREIGN KEY (PostId) REFERENCES SharePost(PostId)
GO
ALTER TABLE Comment ADD CONSTRAINT has_Project
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO

---SharePost---
ALTER TABLE SharePost ADD CONSTRAINT FK_PostState
FOREIGN KEY (StateId) REFERENCES States(StateId)
GO 
ALTER TABLE SharePost ADD CONSTRAINT FK_HsProject
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 

---PROJECT---
ALTER TABLE Project ADD CONSTRAINT has_ProjectState
FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
GO 
ALTER TABLE Project ADD CONSTRAINT FKTeam
FOREIGN KEY (StateId) REFERENCES States(StateId)
GO 

---PROJECT IMAGES---
ALTER TABLE ProjectImage ADD CONSTRAINT has_Images
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 

---PROJECT VIDEO---
ALTER TABLE ProjectVideo ADD CONSTRAINT has_VIDEO
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 

---TEAM MEMBER---
ALTER TABLE TeamMember ADD CONSTRAINT has_Team
FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
GO 
---TEAM SUPERVISOR---
ALTER TABLE Team_Supervisor ADD CONSTRAINT has_Supervisor
FOREIGN KEY (SupervisorID) REFERENCES Supervisor(SupervisorID)
GO 
ALTER TABLE Team_Supervisor ADD CONSTRAINT suport_Team
FOREIGN KEY (TeamID) REFERENCES Team(TeamID)
GO 

---FAVORITE---
ALTER TABLE Favorite ADD CONSTRAINT has_FKUser
FOREIGN KEY (UserId) REFERENCES User_Table(UserId)
GO 
ALTER TABLE Favorite ADD CONSTRAINT has_FKPost
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 

------------------------------------------------------------------
----------------------------Add value-----------------------------
insert into Roles(RoleName) values('Guest')
insert into Roles(RoleName) values('Editor')
insert into Roles(RoleName) values('Contributor')
insert into Roles(RoleName) values('Admin')

insert into User_Table(UserId,Name,Email) values(111111111111111111111,'Adam','adam@gmail.com')
insert into User_Table(UserId,Name,Email) values(333333333333333333333,'Eva','eva@gmail.com')
insert into User_Table(UserId,Name,Email) values(222222222222222222222,'Con ran','conran@gmail.com')

insert into Team(TeamName) values ('404 team')
insert into Team(TeamName) values ('happy feet team')


insert into TeamMember(MemberID,MemberName,Email,Phone,TeamID) values ('SE111111', 'Rolls-Royce', 'rollsroyce@gmail.com','0123456789',1)
insert into TeamMember(MemberID,MemberName,Email,Phone,TeamID) values ('SE222222', 'Land Rover', 'LandRover@gmail.com','0123456789',1)
insert into TeamMember(MemberID,MemberName,Email,Phone,TeamID) values ('SE333333', 'Audi', 'audi@gmail.com','0123456789',1)


insert into Supervisor(SupervisorID,SupervisorName) values ('KTK','Kieu Trong Khanh')
insert into Supervisor(SupervisorID,SupervisorName) values ('LHKP','Lam Huu Khanh Phuong')
insert into Supervisor(SupervisorID,SupervisorName) values ('NTN','Nguyen The Hoang')


insert into Team_Supervisor(TeamID, SupervisorID) values (1,'KTK')
insert into Team_Supervisor(TeamID, SupervisorID) values (1,'LHKP')
insert into Team_Supervisor(TeamID, SupervisorID) values (2,'NTN')


insert into States(StateName) values ('Approving')
insert into States(StateName) values ('Approved')
insert into States(StateName) values ('Rejected')

insert into UpcomingProject(ProjectName, [Location], [Date], [Description]) values (N'Timekeeping management by face recognition in LUG company', N'HỘI TRƯỜNG A', N'15/12/2021', N'Quản lý chấm công bằng nhận diện khuôn mặt trong công ty LUG')
insert into UpcomingProject(ProjectName, [Location], [Date], [Description]) values (N'Warehouse management system for Sang Tam', N'HỘI TRƯỜNG A', N'15/12/2021', N'Hệ thống quản lý kho hàng cho Sang Tâm')


/*
CREATE TABLE Project(
	ProjectId CHAR(8),
	ProjectName NVARCHAR(100),
	IntroductionContent NTEXT,
	Details TEXT,
	Semester CHAR(11),
	ProductURL TEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	ViewNumber INT,
	AuthorName NVARCHAR(50),
	Note NTEXT,
	TeamID int,
	StateId INT,
	PRIMARY KEY(ProjectID)
)*/
GO
insert into Project(ProjectId ,ProjectName ,IntroductionContent,Details ,Semester,ViewNumber,AuthorName,TeamID,StateId) 
values ('SP22HW', 'Project Siu Dinh',N'Đây là một project tuyệt vời mang đến hạnh phúc',null,'2022-Spring', 2,'Dustin',2,1)
insert into Project(ProjectId ,ProjectName ,IntroductionContent,Details ,Semester,ViewNumber,AuthorName,TeamID,StateId) 
values ('FA22SE01', 'Website public thành quả đồ án tốt nghiệp', N'Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự. Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự. Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự. Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự.',
null, '2022-Spring', 5, 'Thanh Dat', 1, 2)




------------------------------------------------------------------------
----------------------------Create fulltext-----------------------------
