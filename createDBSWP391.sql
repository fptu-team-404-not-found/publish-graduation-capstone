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

INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('KTK', N'Kiều Trọng Khánh', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('LHKP', N'Lâm Hữu Khánh Phương', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('NTN', N'Nguyễn Thế Hoàng', NULL, NULL, NULL)
INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position) VALUES('TTNV', N'Thân Thị Ngọc Vân', NULL, NULL, NULL)
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

INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE111111', 'Rolls-Royce', '', 'rollsroyce@gmail.com', '0123456789', '', 1)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE222222', 'Land Rover', '', 'LandRover@gmail.com', '0123456789', '', 1)
INSERT INTO TeamMember(MemberID, MemberName, MemberAvatar, Email, Phone, BackupEmail, TeamID) 
VALUES('SE333333', 'Audi', '', 'audi@gmail.com', '0123456789', '', 1)

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


INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('', 'SP22SE02')
INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('', 'FA22SE01')
INSERT INTO ProjectImage(ImageUrl, ProjectId) VALUES('', 'SU22SE03')
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
