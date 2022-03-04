USE master
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
	Note NTEXT,
	-------------
	MemberID CHAR(8),
	SupervisorID CHAR(5),
	StateId INT,
	ProjectId CHAR(8),
	PRIMARY KEY(PostID)
)
GO

CREATE TABLE Project(
	ProjectId CHAR(8),
	ProjectName NVARCHAR(100),
	ProjectAva TEXT,
	Semester CHAR(11),
	IntroductionContent NTEXT,
	Details NTEXT,
	Recap NTEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	ViewNumber INT,
	AuthorName NVARCHAR(50),
	Note NTEXT,
	-------------
	TeamID INT,
	StateId INT,
	PRIMARY KEY(ProjectID)
)
GO

/*
CREATE TABLE ProjectVideo(
	ProjectVideoId INT IDENTITY(1,1),
	VideoUrl TEXT,
	---------------------
	ProjectId CHAR(8),
	PRIMARY KEY(ProjectVideoId)
)
GO
*/

CREATE TABLE ProjectImage(
	ProjectImageID INT IDENTITY(1,1),
	ImageUrl TEXT,
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
	MemberAvatar TEXT,
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
	SupervisorImage TEXT,
	Information NTEXT,
	Position NVARCHAR(20),
	PRIMARY KEY (SupervisorID)
)
GO

CREATE TABLE Favorite(
	FavoriteID 	INT IDENTITY(1,1),
	UserId		CHAR(21) ,
	ProjectId  	CHAR(8),
	PRIMARY KEY (FavoriteID)
)
GO

CREATE TABLE Sensitive_word(
	wordID INT IDENTITY(1,1),
	banned_word NTEXT,
	PRIMARY KEY (wordID)
)
GO 

CREATE TABLE UpcomingProject(
	Id CHAR(8),
	ProjectName NVARCHAR(100),
	Location NVARCHAR(50),
	Date NVARCHAR(100),
	Description NVARCHAR(500),
	TeamName NVARCHAR(100),
	Image TEXT,
	PRIMARY KEY (Id)
)
GO

CREATE TABLE States(
	StateId INT IDENTITY(1,1),
	StateName VARCHAR(10),
	PRIMARY KEY (StateId)
)
GO

----------------------------------------ADD CONSTRAINT-----------------------------------------
---USER---
ALTER TABLE User_Table ADD CONSTRAINT has_Role
FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
GO 

----COMMENT----
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
ALTER TABLE SharePost ADD CONSTRAINT FK_WritePost
FOREIGN KEY (MemberID) REFERENCES TeamMember(MemberID)
GO 
ALTER TABLE SharePost ADD CONSTRAINT FK_SupervisorWrite
FOREIGN KEY (SupervisorID) REFERENCES Supervisor(SupervisorID)
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

/*
---PROJECT VIDEO---
ALTER TABLE ProjectVideo ADD CONSTRAINT has_VIDEO
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
*/

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
----------------------------INSERT VALUE--------------------------
INSERT INTO Roles(RoleName) VALUES('Guest')
INSERT INTO Roles(RoleName) VALUES('Editor')
INSERT INTO Roles(RoleName) VALUES('Contributor')
INSERT INTO Roles(RoleName) VALUES('Admin')

INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111111, 'adam@gmail.com', 'Adam', 'https://i-giaitri.vnecdn.net/2019/03/28/adamlambert-1553749427-7614-1553749454_680x0.jpg', 4)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(222222222222222222222, 'eva@gmail.com', 'Eva', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Eva_Simons_.jpg/1200px-Eva_Simons_.jpg', 3)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(333333333333333333333, 'conran@gmail.com', N'Con rắn', 'https://2.bp.blogspot.com/-9963WEZg_Oc/Wv0geGW-fiI/AAAAAAABTTQ/ykJ-GleRB64vOx6yg2YUrJc2gNtCROwTwCLcBGAs/s1600/snake-con-ran-compressor.png', 3)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(444444444444444444444, 'bebo@gmail.com', N'Bé Bơ', 'https://lh3.googleusercontent.com/lvatShFcVI9zx3xmESqIbudZOW_lsG5WwnnyQnybYFLTRBCPurE1z1L3jUVQlCKDCbuw6QSJ9l7B886o6K6SzKIPe06nDcqfkeLDRhcB7i26m579hjD1-wlV1LzLTaQ6L3cI9YZX', 3)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(555555555555555555555, 'meowmeow@gmail.com', N'Con mèo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtCLUHMov7xIQpDc7Wl8t2k34-AswYwSQeOQ&usqp=CAU', 2)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(666666666666666666666, 'gaugau@gmail.com', N'Gâu Gâu', 'https://top5kythu.com/wp-content/uploads/Ch%C3%B3-Corgi.jpg', 2)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(777777777777777777777, 'capcap@gmail.com', N'Con vịt', 'https://product.hstatic.net/1000191320/product/vit-co-hoa-binh2.jpg', 2)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(888888888888888888888, 'ooooo@gmail.com', N'Con gà của thầy Hoàng', 'https://salt.tikicdn.com/cache/550x550/ts/product/84/ae/e3/04380681358b98e121682476ff685c00.jpg', 2)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(999999999999999999999, 'chipchip@gmail.com', N'Gà con', 'https://congtybinhquan.com/wp-content/uploads/2019/09/ga-con.jpg', 1)
INSERT INTO User_Table(UserId, Email, Name, Picture, RoleId) 
VALUES(101010101010101010101, 'ecec@gmail.com', N'Con heo', 'https://apc-health.vn/wp-content/uploads/2021/01/con-heo-compressed.jpg', 1)

INSERT INTO Team(TeamName) VALUES('404 team')
INSERT INTO Team(TeamName) VALUES('happy feet team')
INSERT INTO Team(TeamName) VALUES('Sleepy 3 Friends')
INSERT INTO Team(TeamName) VALUES('Sleepy 5 Friends')
INSERT INTO Team(TeamName) VALUES(N'Gẫy')
INSERT INTO Team(TeamName) VALUES('Forever Young')
INSERT INTO Team(TeamName) VALUES('Team 7')
INSERT INTO Team(TeamName) VALUES(N'5 Anh em siu nhơn')
INSERT INTO Team(TeamName) VALUES(N'Team xanh lá')
INSERT INTO Team(TeamName) VALUES(N'Nhóm 10')
INSERT INTO Team(TeamName) VALUES(N'Team 7 Anh em Cầu Vồng')
INSERT INTO Team(TeamName) VALUES(N'Cùng nhau kiếm tiền')
INSERT INTO Team(TeamName) VALUES(N'Quăng Lựu Đạn')
INSERT INTO Team(TeamName) VALUES(N'Đi về nhà Team')
INSERT INTO Team(TeamName) VALUES(N'Thích đi nhậu')

INSERT INTO Team(TeamName) VALUES(N'Wonderful Team')--16

INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) 
VALUES('KTK', N'Kiều Trọng Khánh','https://scontent.fsgn5-8.fna.fbcdn.net/v/t31.18172-8/13662207_272322603140453_8701666325899781861_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=abc084&_nc_ohc=jElVkoSIFrAAX8olxWU&_nc_ht=scontent.fsgn5-8.fna&oh=00_AT_TQ5Tg_4qOBBhr8vS4P6hQXAq3n7l5u6FD-DEFNa9sSg&oe=62480A7A', 
N'Quote: Môn này thì kỳ nào cũng có', 
N'Bộ môn: Bộ môn SE')
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('LHKP', N'Lâm Hữu Khánh Phương', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) 
VALUES('NTN', N'Nguyễn Thế Hoàng', 
'https://scontent.fsgn5-6.fna.fbcdn.net/v/t39.30808-6/270961679_10159870536636108_2642967668131478092_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=-Pfr0VuhNOQAX_KtePu&_nc_ht=scontent.fsgn5-6.fna&oh=00_AT-NEYdg4aaAbsR2vF_sutf7KjCh3kGoA7mstdZdRJZjzA&oe=62260997',
N'Quote: Hãy suy nghĩ đổi ngành ngay từ ngày hôm nay',
N'Bộ môn: Bộ môn SE')
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position)
VALUES('TTNV', N'Thân Thị Ngọc Vân', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('NTT', N'Nguyễn Trọng Tài', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('VTP', N'Vũ Thanh Phong', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('LVT', N'Lê Vũ Trường', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('TVS', N'Thân Văn Sử', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('TTMN', N'Trương Thị Mỹ Ngọc', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('DNTH', N'Đoàn Nguyễn Thành Hòa', NULL, NULL, NULL)

INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(1, 'KTK')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(1, 'LHKP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(2, 'TTNV')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(3, 'LVT')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(4, 'LHKP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(4, 'DNTH')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(5, 'NTT')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(6, 'LHKP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(7, 'VTP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(7, 'NTN')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(8, 'NTT')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(9, 'LHKP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(10, 'VTP')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(16, 'NTN')
INSERT INTO Team_Supervisor(TeamID, SupervisorID) VALUES(16, 'KTK')

INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE111111', 'Rolls-Royce', '', 'rollsroyce@gmail.com', '0123456789', '', 1)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE222222', 'Land Rover', '', 'LandRover@gmail.com', '0123456789', '', 1)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333333', 'Audi', '', 'audi@gmail.com', '0123456789', '', 1)

INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333334', N'Trần Thành Đạt', 'https://scontent.fsgn5-14.fna.fbcdn.net/v/t1.6435-9/103791127_1336281569909415_2852411701540184908_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=174925&_nc_ohc=N5AgfHcAzGEAX8fQc3P&_nc_ht=scontent.fsgn5-14.fna&oh=00_AT9VFckJyvaKglkHPbNnch_wxBuII2RCamcf46ygmb9y-w&oe=6246186B', 'datttse151472@gmail.com', '0123456789', '', 16)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333335', N'Nguyễn Đào Đức Quân', 'https://scontent.fsgn5-12.fna.fbcdn.net/v/t1.6435-9/135462321_2851510705085238_5255117934326662452_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=8wwT3gmeelIAX86M7q4&_nc_ht=scontent.fsgn5-12.fna&oh=00_AT9IqQhVuV7bypRPtpfwXIyPZuDTYHwJfse42bp7fbGa7Q&oe=62454AE2', 'quanndse121478@gmail.com', '0123456789', '', 16)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333336', N'Trần Ngọc Thắng', 'https://scontent.fsgn5-13.fna.fbcdn.net/v/t1.6435-9/176403404_1653771231495813_8737993120589149592_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=DooWfU-rf2QAX_Jy20x&_nc_ht=scontent.fsgn5-13.fna&oh=00_AT99ptzS_uhfVIZIOSMR6pxXegMXvb3_bo8RN-U1MvVu7Q&oe=6246857B', 'thangtnse151478@gmail.com', '0123456789', '', 16)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333337', N'Huỳnh Lê Thủy Tiên', 'https://scontent.fsgn5-9.fna.fbcdn.net/v/t39.30808-6/272908202_3227262997503338_854943145488623253_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=BcQ4p--qx7IAX_XoS6p&_nc_ht=scontent.fsgn5-9.fna&oh=00_AT-eRncD6z9nSabrEo_IPSb2ziXyl4guFwy7JLxVA8mm6A&oe=6226A8C4', 'tienhlse151478@gmail.com', '0123456789', '', 16)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333338', N'Nguyễn Lâm Thúy Phượng', 'https://scontent.fsgn5-14.fna.fbcdn.net/v/t39.30808-6/260333374_1574465062887225_3344656928184114976_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=ODGB9Qv-i4kAX-ItanF&_nc_ht=scontent.fsgn5-14.fna&oh=00_AT_dQjKt8jTMqwfV8psje09upBB1AHkUBGFqKUpd4ANRVw&oe=6226A866', 'phuongnlse151478@gmail.com', '0123456789', '', 16)

INSERT INTO States(StateName) VALUES('Approving')
INSERT INTO States(StateName) VALUES('Approved')
INSERT INTO States(StateName) VALUES('Rejected')

INSERT INTO Project(ProjectId, ProjectName, ProjectAva, Semester, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, TeamID, StateId) 
VALUES('SP22SE02', 'Project Siu Dinh', 'https://media.sohuutritue.net.vn/files/huongmi/2021/10/06/tri-tue-nhan-tao-1606.jpeg', '2022-Spring', 
N'Đây là một project tuyệt vời mang đến hạnh phúc', N'Đây là một project tuyệt vời mang đến hạnh phúc', N'Đây là một project tuyệt vời mang đến hạnh phúc', 8, 
'Dustin', NULL, 2, 1)
INSERT INTO Project(ProjectId, ProjectName, ProjectAva, Semester, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, TeamID, StateId) 
VALUES('FA22SE01', 'The public website of graduation project results', 'http://dreamworld.edu.vn/uploads/Du%20h%E1%BB%8Dc%20ng%C3%A0nh%20Technology%20t%E1%BA%A1i%20M%E1%BB%B9.jpg', 
'2022-Spring', N'Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự.', 'Details', 'Recap', 
5, 'Thanh Dat', NULL, 1, 2)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SU22SE03', 'Robot Housework', N'Đây là sản phẩm Robot giúp việc nhà giúp cho con người', Null, '2022-Summer', 10, 'Josh', Null, 'https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.6435-9/66086960_324180855138412_4642165623210639360_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=OQpz3qUDCUcAX_lCxfX&tn=vVav3Ax8uG2OLcGR&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT8rThBFT9ghmQD17F0jBcD7pXYr9WKWVKBkDXtleb2qhg&oe=623429C3',3,1)

INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details,Recap,Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SU22SE19', 
'A Project Wonderfull',
N'Đây là sản phẩm Robot giúp việc nhà giúp cho con người',
'<p><img alt="" src="https://th-thumbnailer.cdn-si-edu.com/vSnitgUqCQCRSx7mkHZtHZHry4U=/1072x720/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/04/8e/048ed839-a581-48af-a0ae-fac6fec00948/gettyimages-168346757_web.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishin</p>

<p>&nbsp;</p>

<p><img alt="" src="data:image/jpeg;base64,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" style="float:right; height:216px; margin-left:10px; margin-right:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing&nbsp;</p>
',
'<p><img alt="" src="https://th-thumbnailer.cdn-si-edu.com/vSnitgUqCQCRSx7mkHZtHZHry4U=/1072x720/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/04/8e/048ed839-a581-48af-a0ae-fac6fec00948/gettyimages-168346757_web.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishin</p>

<p>&nbsp;</p>

<p><img alt="" src="data:image/jpeg;base64,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" style="float:right; height:216px; margin-left:10px; margin-right:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing&nbsp;</p>
',
'2022-Summer', 
100,
'TNT Panda', 
Null,
'https://cdn.pixabay.com/photo/2019/04/06/05/17/wallpaper-4106667_960_720.jpg',
16,
2)


INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('https://cdn.pixabay.com/photo/2017/02/08/17/24/fantasy-2049567_960_720.jpg', 'SU22SE19')
INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('https://cdn.pixabay.com/photo/2016/09/18/14/21/swimmer-1678307_960_720.jpg', 'SU22SE19')
INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823_960_720.jpg', 'SU22SE19')
/*
INSERT INTO ProjectVideo(VideoUrl, ProjectId) VALUES('', 'SP22SE02')
INSERT INTO ProjectVideo(VideoUrl, ProjectId) VALUES('', 'FA22SE01')
INSERT INTO ProjectVideo(VideoUrl, ProjectId) VALUES('', 'SU22SE03')
*/

INSERT INTO SharePost(Details, Note, MemberID, SupervisorID, StateId, ProjectId)
VALUES(N'Đây là bài sharing của Trần Ngọc Thắng', NULL, 'SE111111', 'KTK', 2, 'SP22SE02')

INSERT INTO Favorite(UserId, ProjectId) VALUES('111111111111111111111', 'SP22SE02')

INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Đồ án rất hay, mình học hỏi rất được rất nhiều từ đồ án này', '111111111111111111111', NULL, 'SP22SE02')
INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Nhóm rất xuất sắc', '222222222222222222222', NULL, 'SP22SE02')

INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Siêu đỉnh', '333333333333333333333', 1, NULL)
INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Hay ghê', '444444444444444444444', 1, NULL)

INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [TeamName], [Image]) 
VALUES('SP23SE01', 'Timekeeping management by face recognition in LUG company', N'HỘI TRƯỜNG A', '15/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', N'Nhóm 5 chú thỏ','https://www.ebillity.com/wp-content/uploads/2020/09/post-time-clock-kiosk.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [TeamName], [Image]) 
VALUES('SP23SE02', 'Smart city: Manage autonomous vehicle in resort', N'HỘI TRƯỜNG B', '16/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', N'Nhóm năng suất','https://www.verdict.co.uk/wp-content/uploads/2021/08/shutterstock_1177506811.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [TeamName], [Image]) 
VALUES('SP23SE03', 'Smoking People Detection', N'HỘI TRƯỜNG B', '16/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', N'Nhóm Cân Team','https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322526/woman-vaping.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [TeamName], [Image]) 
VALUES('SP23SE04', 'Influencer Marketing Platform', N'HỘI TRƯỜNG A', '18/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', N'Nhóm MF (Moto Forever)','https://www.botreetechnologies.com/blog/wp-content/uploads/2019/07/influencer-marketing-platform-1200x675.jpg')

INSERT INTO Sensitive_word(banned_word)
VALUES
	('no'), ('yes'), ('yah'), ('nope')
--------------------------------------
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SU22SE04','Project Siu Chats',N'Đây là một project mang đến chất lượng về mặt thiết kế lẫn nội dung và performance',null,'2022-Summer',
4, 'Ngoc Thang', Null, 'https://vti-solutions-assets.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2021/10/08095533/Human-Intelligence-Can-Fix-AI-Shortcomings-1.jpg',4,1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SP22SE05','Robot Police', N'Một dự án về cảnh sát robot điều tiết giao thông giúp con người',Null,'2022-Spring', 15, 'Mark zuckerberg', null,
'https://cdnmedia.baotintuc.vn/Upload/e9GdNZvHDFi8lZSWc6ubA/files/2019/08/robot8819.jpeg',5,1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('FA22SE06', 'Medical Robot', N'Một dự án về robot giúp đỡ chăm sóc bệnh nhân trong bệnh viện', null, '2022-Fall', 7, 'Rooney', null,
'https://www.medicaldevice-network.com/wp-content/uploads/sites/23/2019/12/MD-1.jpg', 6, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('FA22SE07', 'Online Shopping Website Project', N'Đây là một website về buôn bán giày dép thuộc dòng web ecommerce', null, '2022-Fall', 11,
'Ronaldo', null, 'https://itinfonity.com/wp-content/uploads/2020/10/Ecommerce-Website-Development-1024x1024.png', 7, 1)
---
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SP22SE08', 'Shoes Shopping Website Project Version Sieu Cap ',N'Đây là một website về buôn bán giày dép thuộc dòng web ecommerce',null,
'2022-Spring', 20, 'G.Bale', null, 'https://cdn.pixabay.com/photo/2016/11/19/15/58/camera-1840054_960_720.jpg', 8, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SU22SE09', 'Book Shopping Website Project', N'Đây là một website về buôn bán tất cả loại sách thuộc dòng web ecommerce', null,
'2022-Summer', 14, 'Peter', null, 'https://images.unsplash.com/photo-1543002588-bfa74002ed7e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',9,1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('FA22SE10', 'Go Travel', N'Đây là một website giúp chúng ta có thể đặt vé đi du lịch khắp thế giới chỉ cần lên xe thì sẽ có người đèo ', null,
'2022-Fall', 50, 'Justin Beer', null, 'https://images.unsplash.com/photo-1530789253388-582c481c54b0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',10,1)

INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('FA22SE11', 'Events In FPT University', N'Đây là một dự án giúp cho toàn bộ sinh viên và học sinh có thể theo dõi được các hoạt động của trường FPT', null,
'2022-Fall', 17, 'Allison', null, 'https://daihoc.fpt.edu.vn/media/2020/02/event.jpg', 11, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SU22SE12', 'Go English Breaking', N'Dự án này là một app giúp cho học sinh học tiếng anh dễ dàng với người nước ngoài', null,
'2022-Summer', 22, 'Q.Kha', null, 'https://www.explosion.com/wp-content/uploads/2020/03/english-online-class.jpg', 12, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values('SP22SE13', 'Student Management In University', N'Đây là một app quản lý toàn bộ sinh viên học sinh trong 1 trường đại học', null,
'2022-Spring', 19, 'Maguire', null, 'https://edu-happy.com/wp-content/uploads/2021/01/student-management-system-2.jpg', 13, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SP22SE14', 'Coffee Management', N'Đây là một dự án quản lý toàn bộ hoạt động buôn bán cà phê của một cửa hàng', null,
'2022-Spring', 16, 'Aka Thanh Dat', null, 'https://play-lh.googleusercontent.com/OhLUOF6mZAC4HmM4rfldRNq_cVSKCNBkNI9TrpZmr9A2gQo6jsAvLEWkoh02o_UfIA',14,1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SU22SE15', 'Robot Making Cars', N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi', null,
'2022-Summer', 26, 'Rashford', null, 'https://asiame.vn/wp-content/uploads/2019/09/Robot-c%C3%B4ng-nghi%E1%BB%87p-610x400.jpg', 15, 1)
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SU22SE16', 'Poker Blackjack', N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi', null,
'2022-Summer', 26, 'Rashford', null, 'https://cdn.pixabay.com/photo/2017/08/12/09/17/industry-2633878_960_720.jpg', 15, 1)
---------tới đây
INSERT INTO Project(ProjectId, ProjectName, IntroductionContent, Details, Semester, ViewNumber, AuthorName, Note, ProjectAva, TeamID, StateId) 
values ('SU22SE17', 'Poker Blueberry', N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi', null,
'2022-Summer', 26, 'Rashford', null, 'https://cdn.pixabay.com/photo/2016/08/07/14/59/blueberries-1576398_960_720.jpg', 15, 1)

------------------------------------------------------------------------
----------------------------Create fulltext-----------------------------
create fulltext catalog SEARCH WITH ACCENT_SENSITIVITY = OFF

CREATE UNIQUE INDEX FK_SearchProject ON Project(ProjectId) ;  
create fulltext index on Project
(	
		ProjectName							--Full-text index column name     
        Language 1066

)
	KEY INDEX FK_SearchProject ON SEARCH 
	WITH CHANGE_TRACKING AUTO;
	GO
--ALTER FULLTEXT INDEX ON district START FULL POPULATION;  

CREATE UNIQUE INDEX FK_SearchTeamMember ON TeamMember(MemberID) ;  
create fulltext index on TeamMember
(	
		MemberName							--Full-text index column name     
        Language 1066

)
	KEY INDEX FK_SearchTeamMember ON SEARCH 
	WITH CHANGE_TRACKING AUTO;
	GO

CREATE UNIQUE INDEX FK_SearchSupervisor ON Supervisor(SupervisorID) ;  
create fulltext index on Supervisor
(	
		SupervisorName							--Full-text index column name     
        Language 1066

)
	KEY INDEX FK_SearchSupervisor ON SEARCH 
	WITH CHANGE_TRACKING AUTO;
	GO


CREATE PROC SearchHome
@SearchValue NVARCHAR(100)
AS
BEGIN 
	SET NOCOUNT ON
	DECLARE @TABLE TABLE (
		ProjectId CHAR(8),
		accuracy int
	)
	INSERT INTO @TABLE
	SELECT ProjectId,RANK
	FROM Project P
	INNER JOIN FREETEXTTABLE(Project, *, @SearchValue) AS FT
	ON P.ProjectId = FT.[KEY]
	UNION
	SELECT ProjectId,FTX.RANK
	FROM Project P 
	INNER JOIN Team T ON P.TeamID = T.TeamID
	INNER JOIN TeamMember TM ON TM.TeamID = T.TeamID,
	(SELECT [KEY] MemberID,RANK
	FROM FREETEXTTABLE(TeamMember, *, @SearchValue)) AS FTX
	where TM.MemberID  = FTX.MemberID
	UNION 
	SELECT ProjectId,FTX.RANK
	FROM Project P  
	INNER JOIN Team T ON P.TeamID = T.TeamID
	INNER JOIN Team_Supervisor TS ON TS.TeamID = T.TeamID
	INNER JOIN Supervisor S ON S.SupervisorID = TS.SupervisorID,
	(SELECT [KEY] SupervisorID ,RANK
	FROM FREETEXTTABLE(Supervisor, *, @SearchValue)) AS FTX
	WHERE FTX.SupervisorID = TS.SupervisorID
	ORDER BY RANK DESC;
	SELECT P.ProjectId,ProjectName,P.ProjectAva,Semester
	FROM Project P,@TABLE T
	WHERE P.ProjectId = T.ProjectId
END
--DROP PROC SearchHome
--EXECUTE SearchHome @SearchValue = N'Cadillac'
--EXECUTE SearchHome @SearchValue = N'Siu Đỉnh'
--EXECUTE SearchHome @SearchValue = N'Đinh'