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

CREATE TABLE Account(
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
	Title NTEXT,
	Details NTEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	Note NTEXT,
	-------------
	StudentId CHAR(8),
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
	IntroductionContent NTEXT,
	Details NTEXT,
	Recap NTEXT,
	CreateDate datetime DEFAULT GetDate() NOT NULL,
	ViewNumber INT,
	AuthorName NVARCHAR(50),
	Note NTEXT,
	VideoUrl TEXT,
	-------------
	StateId INT,
	SemesterId INT,
	PRIMARY KEY(ProjectID)
)
GO
CREATE TABLE Semester(
	SemesterId INT IDENTITY(1,1) PRIMARY KEY,
	SemesterName CHAR(11),
)

CREATE TABLE ProjectImage(
	ProjectImageID INT IDENTITY(1,1),
	ImageUrl TEXT,
	--------------------
	ProjectId CHAR(8),
	PRIMARY KEY(ProjectImageID)
)
GO


CREATE TABLE TeamMember(
	StudentId CHAR(8),
	MemberName NVARCHAR(30),
	MemberAvatar TEXT,
	Phone CHAR(10),
	BackupEmail NVARCHAR(50),
	---------------------
	UserId CHAR(21) UNIQUE,
	ProjectId CHAR(8),
	PRIMARY KEY(StudentId)
)
GO

CREATE TABLE Project_Supervisor(
	Id INT IDENTITY(1,1),
	ProjectId  	CHAR(8),
	SupervisorID CHAR(5),
	PRIMARY KEY(Id)
)
GO

CREATE TABLE Supervisor(
	SupervisorID CHAR(5),
	SupervisorName NVARCHAR(30),
	SupervisorImage TEXT,
	Information NVARCHAR(100),
	Position VARCHAR(20),
	Status BIT,
	----
	UserId CHAR(21) UNIQUE,
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
---ACCOUNT---
ALTER TABLE Account ADD CONSTRAINT has_Role
FOREIGN KEY (RoleId) REFERENCES Roles(RoleId)
GO 
----COMMENT----
ALTER TABLE Comment ADD CONSTRAINT has_User
FOREIGN KEY (UserId) REFERENCES Account(UserId)
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
FOREIGN KEY (StudentId) REFERENCES TeamMember(StudentId)
GO 
ALTER TABLE SharePost ADD CONSTRAINT FK_SupervisorWrite
FOREIGN KEY (SupervisorID) REFERENCES Supervisor(SupervisorID)
GO 
---PROJECT---
ALTER TABLE Project ADD CONSTRAINT FKTeam
FOREIGN KEY (StateId) REFERENCES States(StateId)
GO 
ALTER TABLE Project ADD CONSTRAINT FKSemester
FOREIGN KEY (SemesterId) REFERENCES Semester(SemesterId)
GO 
---PROJECT IMAGES---
ALTER TABLE ProjectImage ADD CONSTRAINT has_Images
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
---PROJECT VIDEO---
/*
ALTER TABLE ProjectVideo ADD CONSTRAINT has_VIDEO
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO */
---TEAM MEMBER---
ALTER TABLE TeamMember ADD CONSTRAINT has_AProject
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
ALTER TABLE TeamMember ADD CONSTRAINT is_a_Account
FOREIGN KEY (UserId) REFERENCES Account(UserId)
GO
---PROJECT SUPERVISOR---
ALTER TABLE Project_Supervisor ADD CONSTRAINT has_Supervisor0213
FOREIGN KEY (SupervisorID) REFERENCES Supervisor(SupervisorID)
GO 
ALTER TABLE Project_Supervisor ADD CONSTRAINT suport_Project
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
---FAVORITE---
ALTER TABLE Favorite ADD CONSTRAINT has_FKUser
FOREIGN KEY (UserId) REFERENCES Account(UserId)
GO 
ALTER TABLE Favorite ADD CONSTRAINT has_FKPost
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
---SUPERVISOR---
ALTER TABLE Supervisor ADD CONSTRAINT is_also_a_account
FOREIGN KEY (UserId) REFERENCES Account(UserId)
GO 
------------------------------------------------------------------
----------------------------INSERT VALUE--------------------------
INSERT INTO Roles(RoleName) VALUES('User')
INSERT INTO Roles(RoleName) VALUES('Sharer') -- sinh viên làm đồ án, giảng viên, công ty
INSERT INTO Roles(RoleName) VALUES('Poster') --  người có thể sửa đổi cán bộ nhà trường, cộng tác viên
INSERT INTO Roles(RoleName) VALUES('Admin') -- người có thể duyệt bài 



INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111111, 'adam@gmail.com', 'Adam', 
'https://i-giaitri.vnecdn.net/2019/03/28/adamlambert-1553749427-7614-1553749454_680x0.jpg', 3)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(222222222222222222222, 'eva@gmail.com', 'Eva', 
'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Eva_Simons_.jpg/1200px-Eva_Simons_.jpg', 2)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(333333333333333333333, 'conran@gmail.com', N'Con rắn', 
'https://2.bp.blogspot.com/-9963WEZg_Oc/Wv0geGW-fiI/AAAAAAABTTQ/ykJ-GleRB64vOx6yg2YUrJc2gNtCROwTwCLcBGAs/s1600/snake-con-ran-compressor.png', 4)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(444444444444444444444, 'bebo@gmail.com', N'Bé Bơ', 
'https://lh3.googleusercontent.com/lvatShFcVI9zx3xmESqIbudZOW_lsG5WwnnyQnybYFLTRBCPurE1z1L3jUVQlCKDCbuw6QSJ9l7B886o6K6SzKIPe06nDcqfkeLDRhcB7i26m579hjD1-wlV1LzLTaQ6L3cI9YZX', 3)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(555555555555555555555, 'meowmeow@gmail.com', N'Con mèo', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtCLUHMov7xIQpDc7Wl8t2k34-AswYwSQeOQ&usqp=CAU', 2)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(666666666666666666666, 'gaugau@gmail.com', N'Gâu Gâu', 
'https://top5kythu.com/wp-content/uploads/Ch%C3%B3-Corgi.jpg', 4)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(777777777777777777777, 'capcap@gmail.com', N'Con vịt', 
'https://product.hstatic.net/1000191320/product/vit-co-hoa-binh2.jpg', 3)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(888888888888888888888, 'ooooo@gmail.com', N'Con gà của thầy Hoàng', 
'https://salt.tikicdn.com/cache/550x550/ts/product/84/ae/e3/04380681358b98e121682476ff685c00.jpg', 2)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(999999999999999999999, 'chipchip@gmail.com', N'Gà con', 
'https://congtybinhquan.com/wp-content/uploads/2019/09/ga-con.jpg', 4)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(101010101010101010101, 'ecec@gmail.com', N'Con heo', 
'https://apc-health.vn/wp-content/uploads/2021/01/con-heo-compressed.jpg', 3)

INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111112, 'khanhkt@gmail.com', N'KhanhKT', 
'https://scontent.fsgn13-1.fna.fbcdn.net/v/t31.18172-8/13662207_272322603140453_8701666325899781861_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=abc084&_nc_ohc=jElVkoSIFrAAX8pXBUI&_nc_ht=scontent.fsgn13-1.fna&oh=00_AT-vYxg2D5mbOT8dz3tRF3AEc4LiOyaeBEWwgbSGg4W1DA&oe=62480A7A', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111113, 'phuonglhk@gmail.com', N'PhuongLVK', 
'https://f37-org-zp.zdn.vn/ed84b4ff94c1799f20d0.jpg', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111114, 'hoangNT@gmail.com', N'HoangNT', 
'https://scontent.fsgn13-2.fna.fbcdn.net/v/t39.30808-6/270961679_10159870536636108_2642967668131478092_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=-Pfr0VuhNOQAX99qFt3&_nc_ht=scontent.fsgn13-2.fna&oh=00_AT9fy7cQQwi1VAjFXu7jQ95bIuQ2kp3kDAmI4VS0eY4bVg&oe=62260997', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111115, 'vanTTN@gmail.com', N'VanTTN', 
'https://scontent.fsgn8-2.fna.fbcdn.net/v/t39.30808-6/269863818_4454456994677258_94781311489606188_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=wnIG3y4cqK8AX_rSMxw&_nc_oc=AQmGn3hAz4Ph2zWS_yoJnCF3h5NydNG1aNLZEO-Jd39y2e6MY3Sz9OaWACL2gLT6QnlnbhMAANcv8Uj69ZdVsQOe&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT_kmcCkmVx6A3iOswvdyfShD7jHrc1mQSdeSKFnat5WSg&oe=62255A04', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111116, 'taiNT@gmail.com', N'TaiNT', 
'https://lh3.googleusercontent.com/a-/AOh14GiG-AjMkw2LfRrqtjyBJGssiIA1x6mLuJCxqYYDCA=s64-c', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111117, 'phongVT@gmail.com', N'PhongVT', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111118, 'truongLV@gmail.com', N'TruongLV', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111119, 'suTV@gmail.com', N'SuTV', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111120, 'ngocTTM@gmail.com', N'NgocTTM', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111121, 'hoaDNT@gmail.com', N'HoaDNT', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)

INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111122, 'rollsroyce@gmail.com', N'Rolls-Royce', 
'https://vcdn-vnexpress.vnecdn.net/2020/09/02/2021-Rolls-ROyce-Ghost-3-3617-1599022529.jpg', 2)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111123, 'landrover@gmail.com', N'Land Rover', 
'https://tuvanmuaxe.vn/upload/upload_img/images/bang-gia-xe-land-rover-2019-viet-nam-tuvanmuaxe-3.jpg', 2)
INSERT INTO Account(UserId, Email, Name, Picture, RoleId) 
VALUES(111111111111111111124, 'audi@gmail.com', N'Audi R8', 
'https://giaxeoto.vn/admin/upload/images/resize/640-Audi-R8-2021-co-gia-bao-nhieu.jpg', 2)



INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position, UserId) 
VALUES('KTK', N'Kiều Trọng Khánh', 'https://scontent.fsgn13-1.fna.fbcdn.net/v/t31.18172-8/13662207_272322603140453_8701666325899781861_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=abc084&_nc_ohc=jElVkoSIFrAAX8pXBUI&_nc_ht=scontent.fsgn13-1.fna&oh=00_AT-vYxg2D5mbOT8dz3tRF3AEc4LiOyaeBEWwgbSGg4W1DA&oe=62480A7A', NULL, NULL, 111111111111111111112),
('LHKP', N'Lâm Hữu Khánh Phương', 'https://f37-org-zp.zdn.vn/ed84b4ff94c1799f20d0.jpg', NULL, NULL, 111111111111111111113),
('NTH', N'Nguyễn Thế Hoàng', 'https://scontent.fsgn13-2.fna.fbcdn.net/v/t39.30808-6/270961679_10159870536636108_2642967668131478092_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=-Pfr0VuhNOQAX99qFt3&_nc_ht=scontent.fsgn13-2.fna&oh=00_AT9fy7cQQwi1VAjFXu7jQ95bIuQ2kp3kDAmI4VS0eY4bVg&oe=62260997', NULL, NULL, 111111111111111111114),
('TTNV', N'Thân Thị Ngọc Vân', 'https://scontent.fsgn8-2.fna.fbcdn.net/v/t39.30808-6/269863818_4454456994677258_94781311489606188_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=wnIG3y4cqK8AX_rSMxw&_nc_oc=AQmGn3hAz4Ph2zWS_yoJnCF3h5NydNG1aNLZEO-Jd39y2e6MY3Sz9OaWACL2gLT6QnlnbhMAANcv8Uj69ZdVsQOe&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT_kmcCkmVx6A3iOswvdyfShD7jHrc1mQSdeSKFnat5WSg&oe=62255A04', NULL, NULL, 111111111111111111115),
('NTT', N'Nguyễn Trọng Tài', 'https://lh3.googleusercontent.com/a-/AOh14GiG-AjMkw2LfRrqtjyBJGssiIA1x6mLuJCxqYYDCA=s64-c', NULL, NULL, 111111111111111111116),
('VTP', N'Vũ Thanh Phong', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', NULL, NULL, 111111111111111111117),
('LVT', N'Lê Vũ Trường', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', NULL, NULL, 111111111111111111118),
('TVS', N'Thân Văn Sử', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', NULL, NULL, 111111111111111111119),
('TTMN', N'Trương Thị Mỹ Ngọc', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', NULL, NULL, 111111111111111111120),
('DNTH', N'Đoàn Nguyễn Thành Hòa', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', NULL, NULL, 111111111111111111121)

INSERT INTO States(StateName) VALUES('Approving')
INSERT INTO States(StateName) VALUES('Approved')
INSERT INTO States(StateName) VALUES('Rejected')

INSERT INTO Semester(SemesterName) VALUES('2019-Spring')
INSERT INTO Semester(SemesterName) VALUES('2019-Summer')
INSERT INTO Semester(SemesterName) VALUES('2019-Fall')
INSERT INTO Semester(SemesterName) VALUES('2020-Spring')
INSERT INTO Semester(SemesterName) VALUES('2020-Summer')
INSERT INTO Semester(SemesterName) VALUES('2020-Fall')
INSERT INTO Semester(SemesterName) VALUES('2021-Spring')
INSERT INTO Semester(SemesterName) VALUES('2021-Summer')
INSERT INTO Semester(SemesterName) VALUES('2021-Fall')
INSERT INTO Semester(SemesterName) VALUES('2022-Spring')
---Project 1
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId, SemesterId) 
values
(
'SP19SE04',
'Project Siu Chats',
N'Đây là một project mang đến chất lượng về mặt thiết kế lẫn nội dung và performance',
null,
null,
140, 
'Ngoc Thang',
Null,
'https://vti-solutions-assets.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2021/10/08095533/Human-Intelligence-Can-Fix-AI-Shortcomings-1.jpg',
2,
1)
---Project 2
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU19SE05',
'Robot Police',
N'Một dự án về cảnh sát robot điều tiết giao thông giúp con người',
null,
Null, 
15, 
'Mark zuckerberg', 
null,
'https://cdnmedia.baotintuc.vn/Upload/e9GdNZvHDFi8lZSWc6ubA/files/2019/08/robot8819.jpeg',
1,
2)
--- Project 3 
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('FA19SE06',
'Medical Robot',
N'Một dự án về robot giúp đỡ chăm sóc bệnh nhân trong bệnh viện',
null,
null,
7, 
'Rooney',
null,
'https://www.medicaldevice-network.com/wp-content/uploads/sites/23/2019/12/MD-1.jpg',
1,
3)
--- Project 4
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SP19SE07', 
'Online Shopping Website Project',
N'Đây là một website về buôn bán giày dép thuộc dòng web ecommerce',
null, 
null,
11,
'Ronaldo', 
null, 
'https://itinfonity.com/wp-content/uploads/2020/10/Ecommerce-Website-Development-1024x1024.png',
1,
1)
--- Project 5 
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU20SE08',
'Shoes Shopping Website Project Version Sieu Cap ',
N'Đây là một website về buôn bán giày dép thuộc dòng web ecommerce',
null, 
null,
20, 
'G.Bale',
null, 'https://cdn.pixabay.com/photo/2016/11/19/15/58/camera-1840054_960_720.jpg',
1,
5)
--- Project 6
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('FA20SE09',
'Book Shopping Website Project', 
N'Đây là một website về buôn bán tất cả loại sách thuộc dòng web ecommerce',
null,
null,
14, 
'Peter', 
null, 
'https://images.unsplash.com/photo-1543002588-bfa74002ed7e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80',
1,
6)
-- PJ7
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SP21SE10', 
'Go Travel', 
N'Đây là một website giúp chúng ta có thể đặt vé đi du lịch khắp thế giới chỉ cần lên xe thì sẽ có người đèo ',
null,
null,
50,
'Justin Beer',
null, 
'https://images.unsplash.com/photo-1530789253388-582c481c54b0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80',
1,
7)
--PJ8
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU21SE11',
'Events In FPT University',
N'Đây là một dự án giúp cho toàn bộ sinh viên và học sinh có thể theo dõi được các hoạt động của trường FPT',
null,
null,
17,
'Allison',
null,
'https://daihoc.fpt.edu.vn/media/2020/02/event.jpg',
1,
8)
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('FA21SE12',
'Go English Breaking',
N'Dự án này là một app giúp cho học sinh học tiếng anh dễ dàng với người nước ngoài',
null,
null,
22, 
'Q.Kha',
null,
'https://www.explosion.com/wp-content/uploads/2020/03/english-online-class.jpg',
1,
9)
--PJ9
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values('SP22SE13',
'Student Management In University',
N'Đây là một app quản lý toàn bộ sinh viên học sinh trong 1 trường đại học',
null,
null,
19, 
'Maguire',
null,
'https://edu-happy.com/wp-content/uploads/2021/01/student-management-system-2.jpg',
 1,
 10)
--pj10
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values 
('SP19SE14',
'Coffee Management',
N'Đây là một dự án quản lý toàn bộ hoạt động buôn bán cà phê của một cửa hàng',
null, 
null,
16, 
'Aka Thanh Dat', 
null,
'https://play-lh.googleusercontent.com/OhLUOF6mZAC4HmM4rfldRNq_cVSKCNBkNI9TrpZmr9A2gQo6jsAvLEWkoh02o_UfIA',
1,
1)
--pj11
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU19SE15', 
'Robot Making Cars', 
N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi',
null, 
null,
26,
'Rashford',
null, 
'https://asiame.vn/wp-content/uploads/2019/09/Robot-c%C3%B4ng-nghi%E1%BB%87p-610x400.jpg', 
1,
2)
--pj12
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values 
('FA19SE16',
'Poker Blackjack',
N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi',
null, 
null,
26,
'Rashford',
null, 
'https://cdn.pixabay.com/photo/2017/08/12/09/17/industry-2633878_960_720.jpg', 
1,
3)
--pj13
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values 
('SP20SE17', 
'Poker Blueberry',
N'Đây là một dự án về tự động hóa lắp ráp xe hơi trong cộng nghiệp làm xe hơi', 
null, 
null,
26,
'Rashford',
null, 
'https://cdn.pixabay.com/photo/2016/08/07/14/59/blueberries-1576398_960_720.jpg', 
1,
4)
--pj14
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values('SU20SE02',
'Project Siu Dinh',
N'Đây là một project tuyệt vời mang đến hạnh phúc',
null,
null,
8,
'Dustin',
null,
'https://media.sohuutritue.net.vn/files/huongmi/2021/10/06/tri-tue-nhan-tao-1606.jpeg',
1,
5)
--pj15
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU20SE01',
'The public website of graduation project results',
N'Phần này sẽ chứa đoạn giới thiệu về Project, có thể bao gồm lý do làm Project, sơ lược về việc phát triển của những phần mềm tương tự.',
null,
null, 
5, 
'Thanh Dat', 
null,
'http://dreamworld.edu.vn/uploads/Du%20h%E1%BB%8Dc%20ng%C3%A0nh%20Technology%20t%E1%BA%A1i%20M%E1%BB%B9.jpg',  
2, 
5)
--pj16
INSERT INTO Project
(ProjectId, ProjectName, IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU20SE03',
'Robot Housework',
N'Đây là sản phẩm Robot giúp việc nhà giúp cho con người', 
Null, 
Null,
10, 
'Josh',
Null, 
'https://scontent.fsgn8-2.fna.fbcdn.net/v/t1.6435-9/66086960_324180855138412_4642165623210639360_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=OQpz3qUDCUcAX_lCxfX&tn=vVav3Ax8uG2OLcGR&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT8rThBFT9ghmQD17F0jBcD7pXYr9WKWVKBkDXtleb2qhg&oe=623429C3',
1,
5)
--pj17
INSERT INTO Project_Supervisor(ProjectId, SupervisorID) 
VALUES('SP19SE04', 'NTH'),
('SP19SE04', 'TTNV'),
('SP21SE10', 'TTNV'),
('SU21SE11', 'LVT'),
('FA21SE12', 'LHKP'),
('SP22SE13', 'DNTH'),
('SP19SE14', 'NTT'),
('SP19SE14', 'LHKP'),
('SU19SE15', 'VTP'),
('SU19SE15', 'NTH'),
('FA19SE16', 'NTT'),
('FA19SE16', 'LHKP'),
('SP20SE17', 'VTP'),
('SU21SE11', 'VTP'),
('SU20SE02', 'KTK')
-- tới đây rồi 
INSERT INTO TeamMember(StudentId, MemberName, MemberAvatar, Phone, BackupEmail,ProjectId,UserId) 
VALUES
('SE111111', 'Rolls-Royce', 'https://vcdn-vnexpress.vnecdn.net/2020/09/02/2021-Rolls-ROyce-Ghost-3-3617-1599022529.jpg', '0123456789', 'rollsroyce@gmail.com', 'SU20SE02', 111111111111111111122),
('SE222222', 'Land Rover', 'https://tuvanmuaxe.vn/upload/upload_img/images/bang-gia-xe-land-rover-2019-viet-nam-tuvanmuaxe-3.jpg',  '0123456789', 'LandRover@gmail.com', 'SU20SE02', 111111111111111111123), 
('SE333333', 'Audi', 'https://giaxeoto.vn/admin/upload/images/resize/640-Audi-R8-2021-co-gia-bao-nhieu.jpg', '0123456789', 'audi@gmail.com', 'SU20SE02', 111111111111111111124)



INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2017/06/05/07/58/butterfly-2373175_960_720.png', 'SU20SE02')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2021/02/23/09/26/cat-6042858_960_720.jpg', 'SU20SE02')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2022/02/14/20/09/bird-7013754_960_720.jpg', 'SU20SE02')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Cách để trở thành một leader vui vẻ mà vẫn hiệu quả', N'Đây là bài sharing của Rolls-Royce', NULL, 'SE111111', null, 2, 'SU20SE02')
INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Hãy luôn luôn tìm hiểu thứ mới. Với châm ngôn code không chạy hãy đốt nhang cầu ông bà.', N'Đây là bài sharing của Kiều Trọng Khánh', NULL, Null, 'KTK', 2, 'SU20SE02')

INSERT INTO Favorite(UserId, ProjectId) VALUES('111111111111111111111', 'SU20SE02')

INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Đồ án rất hay, mình học hỏi rất được rất nhiều từ đồ án này', '111111111111111111111', NULL, 'SU20SE02')
INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Nhóm rất xuất sắc', '222222222222222222222', NULL, 'SU20SE02')

INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Siêu đỉnh', '333333333333333333333', 1, 'FA19SE06')
INSERT INTO Comment(CommentContent, UserId, PostId, ProjectId) 
VALUES (N'Hay ghê', '444444444444444444444', 1, 'FA19SE06')

INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE01', 'Timekeeping management by face recognition in LUG company', N'HỘI TRƯỜNG A', '15/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://www.ebillity.com/wp-content/uploads/2020/09/post-time-clock-kiosk.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE02', 'Smart city: Manage autonomous vehicle in resort', N'HỘI TRƯỜNG B', '16/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://www.verdict.co.uk/wp-content/uploads/2021/08/shutterstock_1177506811.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE03', 'Smoking People Detection', N'HỘI TRƯỜNG B', '16/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322526/woman-vaping.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE04', 'Influencer Marketing Platform', N'HỘI TRƯỜNG A', '18/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://www.botreetechnologies.com/blog/wp-content/uploads/2019/07/influencer-marketing-platform-1200x675.jpg')

INSERT INTO Sensitive_word(banned_word)
VALUES
	('no'), ('yes'), ('yah'), ('nope')
--------------------------------------


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

CREATE UNIQUE INDEX FK_SearchTeamMember ON TeamMember(StudentId) ;  
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
	SELECT P.ProjectId,FTX.RANK
	FROM Project P 
	INNER JOIN TeamMember TM ON P.ProjectId=TM.ProjectId,
	(SELECT [KEY] StudentId,RANK
	FROM FREETEXTTABLE(TeamMember, *, @SearchValue)) AS FTX
	where TM.StudentId  = FTX.StudentId
	UNION 
	SELECT P.ProjectId,FTX.RANK
	FROM Project P  
	INNER JOIN Project_Supervisor TS ON TS.ProjectId = P.ProjectId
	INNER JOIN Supervisor S ON S.SupervisorID = TS.SupervisorID,
	(SELECT [KEY] SupervisorID ,RANK
	FROM FREETEXTTABLE(Supervisor, *, @SearchValue)) AS FTX
	WHERE FTX.SupervisorID = TS.SupervisorID
	ORDER BY RANK DESC;
	SELECT P.ProjectId,ProjectName,P.ProjectAva, S.SemesterName,S.SemesterId
	FROM Project P,@TABLE T, Semester S
	WHERE P.ProjectId = T.ProjectId  AND S.SemesterId= P.SemesterId
END
--DROP PROC SearchHome
--EXECUTE SearchHome @SearchValue = N'Audi'
--EXECUTE SearchHome @SearchValue = N'Siu Đỉnh'
--EXECUTE SearchHome @SearchValue = N'Đinh'
--EXECUTE SearchHome @SearchValue = N'The Hoang'

Select *
From Project p join TeamMember tm
on p.ProjectId = tm.ProjectId
join Project_Supervisor ps
on ps.ProjectId = p.ProjectId 
join Supervisor su 
on ps.SupervisorID = su.SupervisorID
join ProjectImage [pi]
on [pi].ProjectId = p.ProjectId
Where p.ProjectId = 'SU20SE02'

Select sp.PostId, sp.Title, tm.StudentId, tm.MemberAvatar, su.SupervisorID, su.SupervisorImage, p.ProjectId
From Project p inner join SharePost sp
on p.ProjectId = sp.ProjectId
inner join TeamMember tm 
on sp.StudentId = tm.StudentId
inner join States st 
on st.StateId = sp.StateId
inner join Supervisor su
on su.SupervisorID = sp.SupervisorID
Where p.ProjectId = 'SU20SE02'