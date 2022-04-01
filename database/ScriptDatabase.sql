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
	CommentDate date DEFAULT GetDate() NOT NULL,
	CommentContent NText,
	---------------
	Account VARCHAR(50),
	PostId INT,
	ProjectId CHAR(8),
	PRIMARY KEY(CommentID)
) 
GO

CREATE TABLE Account(
	Email VARCHAR(50),
	Name NVARCHAR(50),
	Picture TEXT,
	----
	RoleId INT,
	PRIMARY KEY(Email)
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
	CreateDate date DEFAULT GetDate() NOT NULL,
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
	CreateDate date DEFAULT GetDate() NOT NULL,
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
	Account VARCHAR(50) UNIQUE,
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
	Position VARCHAR(50),
	Status BIT,
	----
	Account VARCHAR(50) UNIQUE,
	PRIMARY KEY (SupervisorID)
)
GO

CREATE TABLE Favorite(
	FavoriteID 	INT IDENTITY(1,1),
	Account VARCHAR(50) ,
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
FOREIGN KEY (Account) REFERENCES Account(Email)
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
FOREIGN KEY (Account) REFERENCES Account(Email)
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
FOREIGN KEY (Account) REFERENCES Account(Email)
GO 
ALTER TABLE Favorite ADD CONSTRAINT has_FKPost
FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
GO 
---SUPERVISOR---
ALTER TABLE Supervisor ADD CONSTRAINT is_also_a_account
FOREIGN KEY (Account) REFERENCES Account(Email)
GO 
------------------------------------------------------------------
----------------------------INSERT VALUE--------------------------
INSERT INTO Roles(RoleName) VALUES('User')
INSERT INTO Roles(RoleName) VALUES('Sharer') -- sinh viên làm đồ án, giảng viên, công ty
INSERT INTO Roles(RoleName) VALUES('Poster') --  người có thể sửa đổi cán bộ nhà trường, cộng tác viên
INSERT INTO Roles(RoleName) VALUES('Admin') -- người có thể duyệt bài 



INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'adam@gmail.com', 'Adam', 
'https://i-giaitri.vnecdn.net/2019/03/28/adamlambert-1553749427-7614-1553749454_680x0.jpg', 3)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'eva@gmail.com', 'Eva', 
'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Eva_Simons_.jpg/1200px-Eva_Simons_.jpg', 2)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'conran@gmail.com', N'Con rắn', 
'https://2.bp.blogspot.com/-9963WEZg_Oc/Wv0geGW-fiI/AAAAAAABTTQ/ykJ-GleRB64vOx6yg2YUrJc2gNtCROwTwCLcBGAs/s1600/snake-con-ran-compressor.png', 4)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'bebo@gmail.com', N'Bé Bơ', 
'https://lh3.googleusercontent.com/lvatShFcVI9zx3xmESqIbudZOW_lsG5WwnnyQnybYFLTRBCPurE1z1L3jUVQlCKDCbuw6QSJ9l7B886o6K6SzKIPe06nDcqfkeLDRhcB7i26m579hjD1-wlV1LzLTaQ6L3cI9YZX', 3)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'meowmeow@gmail.com', N'Con mèo', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtCLUHMov7xIQpDc7Wl8t2k34-AswYwSQeOQ&usqp=CAU', 2)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'gaugau@gmail.com', N'Gâu Gâu', 
'https://top5kythu.com/wp-content/uploads/Ch%C3%B3-Corgi.jpg', 4)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'capcap@gmail.com', N'Con vịt', 
'https://product.hstatic.net/1000191320/product/vit-co-hoa-binh2.jpg', 3)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'ooooo@gmail.com', N'Con gà của thầy Hoàng', 
'https://salt.tikicdn.com/cache/550x550/ts/product/84/ae/e3/04380681358b98e121682476ff685c00.jpg', 2)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'chipchip@gmail.com', N'Gà con', 
'https://congtybinhquan.com/wp-content/uploads/2019/09/ga-con.jpg', 4)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'ecec@gmail.com', N'Con heo', 
'https://apc-health.vn/wp-content/uploads/2021/01/con-heo-compressed.jpg', 3)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('khanhkt@gmail.com', N'KhanhKT', 
'https://scontent.fsgn13-1.fna.fbcdn.net/v/t31.18172-8/13662207_272322603140453_8701666325899781861_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=abc084&_nc_ohc=jElVkoSIFrAAX8pXBUI&_nc_ht=scontent.fsgn13-1.fna&oh=00_AT-vYxg2D5mbOT8dz3tRF3AEc4LiOyaeBEWwgbSGg4W1DA&oe=62480A7A', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('phuonglhk@gmail.com', N'PhuongLVK', 
'https://f37-org-zp.zdn.vn/ed84b4ff94c1799f20d0.jpg', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('hoangNT@gmail.com', N'HoangNT', 
'https://scontent.fsgn5-6.fna.fbcdn.net/v/t39.30808-6/270961679_10159870536636108_2642967668131478092_n.jpg?_nc_cat=108&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=q44qqnBRN8AAX9pC9Ak&_nc_ht=scontent.fsgn5-6.fna&oh=00_AT84riyGyTZx3Rd-mugwD_WgQTjvIzlkD1P2M3L5gnKI3g&oe=622FECD7', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('vanTTN@gmail.com', N'VanTTN', 
'https://scontent.fsgn8-2.fna.fbcdn.net/v/t39.30808-6/269863818_4454456994677258_94781311489606188_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=wnIG3y4cqK8AX_rSMxw&_nc_oc=AQmGn3hAz4Ph2zWS_yoJnCF3h5NydNG1aNLZEO-Jd39y2e6MY3Sz9OaWACL2gLT6QnlnbhMAANcv8Uj69ZdVsQOe&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT_kmcCkmVx6A3iOswvdyfShD7jHrc1mQSdeSKFnat5WSg&oe=62255A04', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('taiNT@gmail.com', N'TaiNT', 
'https://lh3.googleusercontent.com/a-/AOh14GiG-AjMkw2LfRrqtjyBJGssiIA1x6mLuJCxqYYDCA=s64-c', 1)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('phongVT@gmail.com', N'PhongVT', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'truongLV@gmail.com', N'TruongLV', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES( 'suTV@gmail.com', N'SuTV', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'ngocTTM@gmail.com', N'NgocTTM', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('hoaDNT@gmail.com', N'HoaDNT', 
'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 1)

INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('rollsroyce@gmail.com', N'Rolls-Royce', 
'https://vcdn-vnexpress.vnecdn.net/2020/09/02/2021-Rolls-ROyce-Ghost-3-3617-1599022529.jpg', 2)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('landrover@gmail.com', N'Land Rover', 
'https://tuvanmuaxe.vn/upload/upload_img/images/bang-gia-xe-land-rover-2019-viet-nam-tuvanmuaxe-3.jpg', 2)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('audi@gmail.com', N'Audi R8', 
'https://giaxeoto.vn/admin/upload/images/resize/640-Audi-R8-2021-co-gia-bao-nhieu.jpg', 2)

INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES( 'thang@gmail.com', N'Trần Ngọc Thắng', 
'https://scontent.fsgn5-14.fna.fbcdn.net/v/t1.6435-9/90442064_1347089058830700_353250228188479488_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=174925&_nc_ohc=fR-1GCyN3XAAX-cYWj5&_nc_ht=scontent.fsgn5-14.fna&oh=00_AT93cuxR34ymr2X3mGDb-J-creMTgw62KkX_Eq9qOsrWlg&oe=624C0691', 2)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES( 'quan@gmail.com', N'Nguyễn Đào Đức Quân', 
'https://scontent.fsgn5-10.fna.fbcdn.net/v/t1.6435-9/66439857_2382597965309850_8228950987432263680_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=174925&_nc_ohc=pU5U2y8EQdIAX_pKDR8&tn=AmENxgdPJzpEBtxj&_nc_ht=scontent.fsgn5-10.fna&oh=00_AT9OUvIKXoY1L-PP0VopRhqxBqpqle9Y_bP65FuyFFn9eg&oe=624AB915', 2)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('phuong@gmail.com', N'Nguyễn Lâm Thúy Phượng', 
'https://scontent.fsgn5-13.fna.fbcdn.net/v/t39.30808-6/272992556_1618574551809609_4963409416272511926_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=0Lz-nM-TtGsAX-E_n4o&_nc_ht=scontent.fsgn5-13.fna&oh=00_AT-4EoXx-86iVmy94EKl36cAOrrcF4VECNDM3LUIUTEFUg&oe=6229D080', 2)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('huynhlethuytien2001@gmail.com', N'Tiên Huỳnh Lê Thủy', 
'https://lh3.googleusercontent.com/a/AATXAJxOz2xq_cqUCQa0aMhJ0ufyQ52b8-m3N6OtCQc-=s96-c', 1)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('huynhtien29102001@gmail.com', N'Tiên Huỳnh Lê Thủy', 
'https://lh3.googleusercontent.com/a-/AOh14GivSG6qVUDb-vgJFXiv1eelRKFNp2lWG6e4V4hT1Q=s96-c', 2)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('tienhltse151104@fpt.edu.vn', N'Huỳnh Lê Thủy Tiên', 
'https://lh3.googleusercontent.com/a-/AOh14GjXB27s5UmIkWOXv51Ltl0FUNb1pZGd3sp6_y0jeg=s96-c', 3)
INSERT INTO Account(Email, Name, Picture, RoleId) 
VALUES('tien.huynhlt.tn@gmail.com', N'Tiên Huỳnh', 
'https://lh3.googleusercontent.com/a-/AOh14GioGvie0EocF6tbw7urOiQ9gFk8k-VaO1q889iG=s96-c', 4)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('dat@gmail.com', N'Trần Thành Đạt', 
'https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.6435-9/72557491_1132682380269336_870149971758809088_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=Hwfal_V5z8gAX_W19F4&_nc_ht=scontent.fsgn5-3.fna&oh=00_AT9XtwONf7tLhwG-8wk-eIVp9uMldEnSWGYiS-3Qh8UKxw&oe=624BBB33', 2)
INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('phuongnltse150999@fpt.edu.vn', N'Nguyễn Lâm Thúy Phượng', 
'https://lh3.googleusercontent.com/a-/AOh14Gj9UqqEmGy4qetk8NNBlr-l-EuhDmMUMmA9nhbI4A=s96-c', 4)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES('nguyenlamthuyphuong25@gmail.com', N'Thúy Phượng Nguyễn Lâm', 
'https://lh3.googleusercontent.com/a-/AOh14Gjj0GkGfeKuRDpY6IthUyo4-sY93CgGgFJaDetMBg=s96-c', 3)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES( 'lamthuyloan0303@gmail.com', N'Lâm Thúy Loan', 
'https://lh3.googleusercontent.com/a/AATXAJz5NVtaYbmz_bsgWJGab3QR41REuCWivrKFLO25=s96-c', 2)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES
('huongttdde150243@fpt.edu.vn', NULL, '', 2),
('haidtse130137@fpt.edu.vn', NULL, '', 2),
('khanhncse130527@fpt.edu.vn', NULL, '', 2),
('cuongncse140042@fpt.edu.vn', NULL, '', 2),
('khaipnse140528@fpt.edu.vn', NULL, '', 2)

INSERT INTO Account( Email, Name, Picture, RoleId) 
VALUES
('lehuynhduc@gmail.com', NULL, '', 2),
('vohuuloc@gmail.com', NULL, '', 2),
('nguyenduchuy@gmail.com', NULL, '', 2),
('nguyendoanquang@gmail.com', NULL, '', 2),
('dangtrannam@gmail.com', NULL, '', 2)

INSERT INTO Supervisor(SupervisorID, SupervisorName, SupervisorImage, Information, Position, [Status], Account ) 
VALUES
('KTK', N'Kiều Trọng Khánh', 'https://scontent.fsgn13-1.fna.fbcdn.net/v/t31.18172-8/13662207_272322603140453_8701666325899781861_o.jpg?_nc_cat=109&ccb=1-5&_nc_sid=abc084&_nc_ohc=jElVkoSIFrAAX8pXBUI&_nc_ht=scontent.fsgn13-1.fna&oh=00_AT-vYxg2D5mbOT8dz3tRF3AEc4LiOyaeBEWwgbSGg4W1DA&oe=62480A7A',
N'Môn này kỳ nào cũng có', N'Lecturer at FPT University HCM', 1, 'khanhkt@gmail.com'),

('LHKP', N'Lâm Hữu Khánh Phương', 'https://f37-org-zp.zdn.vn/ed84b4ff94c1799f20d0.jpg', 
NULL, NULL, 1, 'phuonglhk@gmail.com'),

('NTH', N'Nguyễn Thế Hoàng', 'https://uni.fpt.edu.vn/Data/Sites/1/media/ng%C6%B0%E1%BB%9Di-truy%E1%BB%81n-l%E1%BB%ADa/gi%C3%A1o-l%C3%A0ng-nguy%E1%BB%85n-th%E1%BA%BF-ho%C3%A0ng/123835423_107646594490275_3866158663278802293_o.jpg', 
N'HAPPY CODE - HAPPY MONEY - HAPPY LIFE',N'Lecturer at FPT University HCM', 1, 'hoangNT@gmail.com'),

('TTNV', N'Thân Thị Ngọc Vân', 'https://scontent.fsgn8-2.fna.fbcdn.net/v/t39.30808-6/269863818_4454456994677258_94781311489606188_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=730e14&_nc_ohc=wnIG3y4cqK8AX_rSMxw&_nc_oc=AQmGn3hAz4Ph2zWS_yoJnCF3h5NydNG1aNLZEO-Jd39y2e6MY3Sz9OaWACL2gLT6QnlnbhMAANcv8Uj69ZdVsQOe&_nc_ht=scontent.fsgn8-2.fna&oh=00_AT_kmcCkmVx6A3iOswvdyfShD7jHrc1mQSdeSKFnat5WSg&oe=62255A04',
'', N'Lecturer at FPT University HCM', 1, 'vanTTN@gmail.com'),

('NTT', N'Nguyễn Trọng Tài', 'https://lh3.googleusercontent.com/a-/AOh14GiG-AjMkw2LfRrqtjyBJGssiIA1x6mLuJCxqYYDCA=s64-c',
NULL, N'Lecturer at FPT University HCM', 1, 'taiNT@gmail.com'),

('VTP', N'Vũ Thanh Phong', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 
NULL, N'Lecturer at FPT University HCM', 1, 'phongVT@gmail.com'),

('LVT', N'Lê Vũ Trường', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png',
NULL, N'Lecturer at FPT University HCM', 0, 'truongLV@gmail.com'),

('TVS', N'Thân Văn Sử', 'https://scontent.fsgn5-14.fna.fbcdn.net/v/t1.6435-9/151858679_267986801521509_9002274583163747363_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=0debeb&_nc_ohc=5mSrCnjhnV4AX9Vq73T&_nc_ht=scontent.fsgn5-14.fna&oh=00_AT8LBvIekLLFPfeUtnD7TUQIyjA2VlXZF2_rRF2MvgNTVQ&oe=624B385F', 
N'Thầy chỉ là người giới thiệu thôi còn đi con đường nào là do các em tự vạch ra.', N'Lecturer at FPT University HCM', 1, 'suTV@gmail.com'),

('TTMN', N'Trương Thị Mỹ Ngọc', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png', 
NULL, N'Lecturer at FPT University HCM', 1, 'ngocTTM@gmail.com'),
('DNTH', N'Đoàn Nguyễn Thành Hòa', 'https://chanhviet.com/wp-content/themes/consultix/images/no-image-found-360x260.png',
NULL, N'Lecturer at FPT University HCM', 1, 'hoaDNT@gmail.com')

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


---Project hoan chinh
INSERT INTO Project
(ProjectId, ProjectName,VideoUrl,IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SP20001',
'Community COVID Market Application',
'https://www.youtube.com/embed/miTShqyWE64',

'The application was developed based on the requirements of District Doan District 1 - City. Ho Chi Minh City to serve the people of District 1 and small businesses in the traditional markets in the district during the time of social distancing as well as in the future. In this project, the implementation focuses onthe construction of a system that allows small traders to authorize the district union of district 1 to collect orders from people in district 1 placed and deliver goods according to the orders placed in the situation of social distancing or in the situation that the small traders of the traditional market are not convenient in using information technology in their operations. This project is oriented to maintain the traditional market and the small traders in the market with the ability to use information technology can receive and process orders directly through the application. The project facilitates people to buy and order online without needing to manage an account through phone number verification when transacting...',

'<h1 style="text-align: center;">Why to do this capstone project?</h1>
<p style="width:100%; text-align:center"><img alt="" src="https://hcmuni.fpt.edu.vn/Data/Sites/1/media/2021-huynh-anh/covid-market/screenshot_15.jpg" style="width:50%" /></p>
<p style="text-align:center; font-style: italic; width: 80%; margin:auto">The website is full of necessary goods for people during the epidemic season. Delivery time depends on the items people order.</p>

<p>FPT University faculty and students collaborated with District 1 (District 1, Ho Chi Minh City) to launch a Covid-19 season market shopping page for residents of District 1, solving the congestion of goods going to the market for the season.</p>

<p style="width:100%; text-align:center"><img alt="" src="https://hcmuni.fpt.edu.vn/Data/Sites/1/media/2021-huynh-anh/covid-market/team-covid.jpg" style="width:50%" /></p>

<p style="text-align:center; font-style: italic; width: 80%; margin:auto">The project implementation team includes Mr. Kieu Trong Khanh (project consultant) - Lecturer majoring in Software Engineering; and a group of software engineering students from FPT University: Le Huynh Duc (K12 - Team Leader), Vo Huu Loc (K13), Nguyen Duc Huy (K12), Nguyen Doan Quang (K13), Dang Tran Nam (K15). )</p>

<p style="text-indent:20px">Mr. Kieu Trong Khanh was the first to approach the project from District 1, then gathered FPT University students to implement. However, at first, you were still quite afraid because the implementation time was too short, and it was during the final exam. But after consulting each other and agreeing that this is a very urgent job for the people, you quickly accepted the project and tried to implement it on schedule.
<p style="text-indent:20px">During the implementation, the group also encountered a few difficulties because District 1 side changed its requirements continuously to adapt to the epidemic situation. Overcoming all obstacles, the project was finally completed and officially put into use after 3 weeks. Accompanying the project is the extremely important role of lecturer Kieu Trong Khanh, the teacher who made the connection. , generate ideas and directly mentor the student group on the right technologies for the project.<p>
<p style="text-indent:20px">After the project was put into operation, the group received many positive feedbacks from District 1 as well as an offer to cooperate for a long time. People are also very excited about this project.</p>
',
'<p style="float:left"><iframe allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" frameborder="0" height="300" src="https://www.youtube.com/embed/miTShqyWE64" title="YouTube video player" width="485"></iframe></p>
<p style="float:right; width: 44%; text-indent: 20px">In the future, the team plans to jump ahead with the idea of ​​an application called Community Covid Market Application . The system will collect the most accurate F0 data in district 1 and classify it by color representing severity. When accessing the application, people can know the status of their area to be proactive about health care. In addition, the system will help people to order consumer goods from many different places such as supermarkets, convenience stores and many stores associated with the system...</p>
',
100, 
'Thuy Phuong',
Null, 
'https://thumbs.dreamstime.com/b/ordering-food-home-using-mobile-application-pandemic-covid-woman-surfing-online-restaurant-ordering-food-home-198721277.jpg',
2,
6)

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
INSERT INTO Project
(ProjectId, ProjectName,VideoUrl,IntroductionContent, Details, Recap, ViewNumber, AuthorName, Note, ProjectAva, StateId,SemesterId) 
values
('SU78SE14',
'Project Research and design of Smart-Camera devices applied in
traffic',
'https://www.youtube.com/embed/wKyI2Mxna6w',

N'Trái với quan điểm chung của số đông, Lorem Ipsum không phải chỉ là một đoạn văn bản ngẫu nhiên. Người ta tìm thấy nguồn gốc của nó từ những tác phẩm văn học la-tinh cổ điển xuất hiện từ năm 45 trước Công Nguyên, nghĩa là nó đã có khoảng hơn 2000 tuổi. Một giáo sư của trường Hampden-Sydney College (bang Virginia - Mỹ) quan tâm tới một trong những từ la-tinh khó hiểu nhất, "consectetur", trích từ một đoạn của Lorem Ipsum, và đã nghiên cứu tất cả các ứng dụng của từ này trong văn học cổ điển, để từ đó tìm ra nguồn gốc không thể chối cãi của Lorem Ipsum.',

'<p><img alt="" src="https://th-thumbnailer.cdn-si-edu.com/vSnitgUqCQCRSx7mkHZtHZHry4U=/1072x720/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/04/8e/048ed839-a581-48af-a0ae-fac6fec00948/gettyimages-168346757_web.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>
<h1 style="text-align: center;">What is Lorem Ipsum?</h1>
<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishin</p>

<p>&nbsp;</p>

<p><img alt="" src="data:image/jpeg;base64,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" style="float:right; height:216px; margin-left:10px; margin-right:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing&nbsp;</p>
', 

'<table align="center" border="0" cellpadding="1" cellspacing="1" style="height:500px; width:900px">
	<tbody>
		<tr>
			<td>
			<p><iframe allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen="" frameborder="0" height="300" src="https://www.youtube.com/embed/fSoooaWkYAo" title="YouTube video player" width="485"></iframe></p>
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don&#39;t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn&#39;t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.The generated Lorem Ipsum is therefore always free from repetition,</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>
',
100, 
'Thang',
Null, 
'https://cdn.pixabay.com/photo/2020/01/26/20/14/computer-4795762_960_720.jpg',
2,
5)



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
('SU20SE02', 'KTK'),
('SU78SE14', 'NTH'),
('SU78SE14', 'KTK')

INSERT INTO Project_Supervisor(ProjectId, SupervisorID) 
VALUES ('SP20001', 'NTH'),
('SP20001', 'KTK')


-- tới đây rồi 
INSERT INTO TeamMember(StudentId, MemberName, MemberAvatar, Phone, BackupEmail,ProjectId, Account) 
VALUES
('SE111111', 'Rolls-Royce', 'https://vcdn-vnexpress.vnecdn.net/2020/09/02/2021-Rolls-ROyce-Ghost-3-3617-1599022529.jpg', 
'0123456789', 'rollsroyce@gmail.com', 'SU20SE02', 'rollsroyce@gmail.com'),
('SE222222', 'Land Rover', 'https://tuvanmuaxe.vn/upload/upload_img/images/bang-gia-xe-land-rover-2019-viet-nam-tuvanmuaxe-3.jpg',
'0123456789', 'LandRover@gmail.com', 'SU20SE02', 'landrover@gmail.com'), 
('SE333333', 'Audi', 'https://giaxeoto.vn/admin/upload/images/resize/640-Audi-R8-2021-co-gia-bao-nhieu.jpg',
'0123456789', 'audi@gmail.com', 'SU20SE02', 'audi@gmail.com'),

('SE151478', N'Trần Ngọc Thắng',
'https://scontent.fsgn5-13.fna.fbcdn.net/v/t1.6435-9/176403404_1653771231495813_8737993120589149592_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=6FCJKCzVKZoAX_vRdKd&_nc_ht=scontent.fsgn5-13.fna&oh=00_AT9qj5cO-nmI_7wTSDzbL6DghGx6zB8_SrnaVTmddxDCqA&oe=624A79FB', 
'0123456789', 'thang@fpt.edu.com', 'SU78SE14', 'thang@gmail.com'),

('SE121311', N'Nguyễn Đào Đức Quân',
'https://scontent.fsgn5-12.fna.fbcdn.net/v/t1.6435-9/135462321_2851510705085238_5255117934326662452_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=9WL2JA0e1xwAX8nWYFH&_nc_ht=scontent.fsgn5-12.fna&oh=00_AT8K_wL0IW1pijiWoNuPz8t9Bm7XSO1DXOABBs_TFXIyMQ&oe=62493F62', 
'0123456789', 'quan@gmail.edu.com', 'SU78SE14', 'quan@gmail.com'),

('SE811131', N'Nguyễn Lâm Thúy Phượng',
'https://scontent.fsgn5-8.fna.fbcdn.net/v/t1.6435-9/117801229_1236226840044384_5312147700656844303_n.jpg?_nc_cat=109&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=zNOOMXb-B4QAX-AUcGu&_nc_ht=scontent.fsgn5-8.fna&oh=00_AT_yrSl9IAmu7Hn5yEbIwyd8JIuuPowS_JgA8c5oO9AKRQ&oe=624BD9C2', 
'0123456789', 'phuong@gmail.edu.com', 'SU78SE14', 'phuong@gmail.com'),

('SE921131', N'Trần Thành Đạt',
'https://scontent.fsgn5-14.fna.fbcdn.net/v/t1.6435-9/103791127_1336281569909415_2852411701540184908_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=174925&_nc_ohc=YGjrghkcZfIAX_ylhUf&_nc_ht=scontent.fsgn5-14.fna&oh=00_AT9Tha7hTQlTFG2eE_GdWevFDRVTsEuh2c8T80adQBNjQw&oe=624A0CEB', 
'0123456789', 'dat@gmail.edu.com', 'SU78SE14', 'dat@gmail.com')

Insert into TeamMember([StudentId], [MemberName], [MemberAvatar], [Phone],[Account],[ProjectId])
values('SE121212',N'Con mèo','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtCLUHMov7xIQpDc7Wl8t2k34-AswYwSQeOQ&usqp=CAU', '0793808821', 'meowmeow@gmail.com', 'SU19SE05')

Insert into TeamMember([StudentId], [MemberName], [MemberAvatar], [Phone],[Account],[ProjectId])
values('SE131313',N'Eva','https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Eva_Simons_.jpg/1200px-Eva_Simons_.jpg', '0793808822', 'eva@gmail.com', 'SU19SE05')

INSERT INTO TeamMember(StudentId, MemberName, MemberAvatar, Phone, BackupEmail,ProjectId, Account) 
VALUES
('DE150243', N'Trần Thị Dịu Hương',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, NULL, 'huongttdde150243@fpt.edu.vn'),
('SE130137', N'Đặng Thanh Hải',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, NULL, 'haidtse130137@fpt.edu.vn'),
('SE130527', N'Nguyễn Công Khánh',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, NULL, 'khanhncse130527@fpt.edu.vn'),
('SE140042', N'Nguyễn Chí Cường',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, NULL, 'cuongncse140042@fpt.edu.vn'),
('SE140528', N'Phạm Ngọc Khải',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, NULL, 'khaipnse140528@fpt.edu.vn')

INSERT INTO TeamMember(StudentId, MemberName, MemberAvatar, Phone, BackupEmail,ProjectId, Account) 
VALUES
('SE123456', N'Lê Huỳnh Đức',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, 'SP20001', 'lehuynhduc@gmail.com'),

('SE130007', N'Võ Hữu Lộc',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, 'SP20001', 'vohuuloc@gmail.com'),

('SE789456', N'Nguyễn Doãn Quang',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, 'SP20001', 'nguyendoanquang@gmail.com'),

('SE456789', N'Đặng Trần Nam',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, 'SP20001', 'dangtrannam@gmail.com'),

('SE123457', N'Nguyễn Đức Huy',
'https://guantanamocity.org/wp-content/uploads/2020/12/huong-dan-tao-anh-dai-dien-hoat-hinh-tren-facebook-cach-tao-avatar-facebook-1.png', 
'0123456789', NULL, 'SP20001', 'nguyenduchuy@gmail.com')

INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2017/06/05/07/58/butterfly-2373175_960_720.png', 'SU20SE02')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2021/02/23/09/26/cat-6042858_960_720.jpg', 'SU20SE02')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2022/02/14/20/09/bird-7013754_960_720.jpg', 'SU20SE02')

INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://hcmuni.fpt.edu.vn/Data/Sites/1/media/2019-capstion-project/3.png', 'SP20001')

INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2019/07/14/16/27/pen-4337521_960_720.jpg', 'SU78SE14')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2020/11/13/08/37/pc-5737958_960_720.jpg', 'SU78SE14')
INSERT INTO ProjectImage(ImageUrl, ProjectId) 
VALUES('https://cdn.pixabay.com/photo/2017/10/01/00/52/architecture-2804083_960_720.jpg', 'SU78SE14')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Cách để trở thành một leader vui vẻ mà vẫn hiệu quả',
N'Đây là bài sharing của Rolls-Royce',
NULL, 
'SE121212', 
null,
2,
'SU19SE05')
INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Hãy luôn luôn tìm hiểu thứ mới. Với châm ngôn code không chạy hãy đốt nhang cầu ông bà.', N'Đây là bài sharing của Kiều Trọng Khánh', NULL, Null, 'KTK', 2, 'SU20SE02')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'How to be a fun and effective leader',
N'<p><img alt="" src="https://cdn.pixabay.com/photo/2016/08/07/21/58/lion-1577197_960_720.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>
<h1 style="text-align: center;">What is Lorem Ipsum?</h1>
<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishin</p>

<p>&nbsp;</p>

<p><img alt="" src="data:image/jpeg;base64,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" style="float:right; height:216px; margin-left:10px; margin-right:10px; width:400px" /></p>

<h1 style="text-align: center;">What is Lorem Ipsum?</h1>

<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.<strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing&nbsp;</p>
',
NULL, 
'SE121212', 
null,
2,
'SU19SE05')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Vượt qua những bất đồng quan điểm',
N'<p style="font-size: 120%; font-weight: 600; letter-spacing: 0.04em; margin-bottom:1em;">1. Create a safe environment for debate</p>
    <img style="float: left; padding: 0 1em;" src="https://cafefcdn.com/zoom/260_162/2017/2-1511677129891-0-3-300-585-crop-1511677490758-1511828254239.jpg" alt="">
    <p style="text-indent: 20px;">
        According to a report from Survey Monkey, 58% of women and 68% of men say they can voice their opinions without
        consequences. This means that there is much more that can be improved.
        There is, of course, the right time and place to raise a dissenting opinion. You should not tell a founder her
        idea is useless during an investor meeting.
        Instead, consider carefully where these discussions take place. For example, schedule serious talks, set time
        limits and talk topics to avoid conflict becoming unmanageable. Feedback should be positive and free of personal
        feelings and should not be offensive to others. Remember that people accept criticism and debate when it is
        fact-based and supported by evidence.
        Although you may think that a peaceful workplace is good, the opposite is true. As Harvard Business Review
        writer Liane Davey once said, “If you think it is right not to point out what is wrong in order to keep the peace
        in the company the right thing to do, you are lying to yourself.”
        “Teams need conflict to work effectively. Conflict allows employees to recognize and face difficult situations,
        to see problems from different perspectives, and to ensure that solutions are always optimal.” Conflict is not
        pleasant, but innovations and inventions often originate here.
    </p>
    <p style="font-size: 120%; font-weight: 600; letter-spacing: 0.04em; margin: 1em 0;">2. Ready to change your mind</p>
    <img style="width: 50%; float: right; padding: 0 1em;" src="http://static.ybox.vn/2018/1/16/27fcdefe-fa9c-11e7-8e93-56c566ee3692.jpg" alt="">
    <p style="text-indent: 20px;">
        Creating a positive debate environment only works if everyone is willing to change their mind.
        This is not easy. Therefore, Dr. Jim Stone has set out 5 steps for you to have an open debate with people.
        The first two steps are to acknowledge that we all have our own thoughts, but that we are all human with
        different lives. We see people with a different mindset as enemies, and we fall into hostility toward them.
        Instead, think about the things we have in common, instead of our differences. Step two is an enhancement of
        step one, and allows you to see the problem from someone else point of view.
        Next, make sure everyone feels safe arguing. If you want the other person to speak for themselves, you have to
        make them feel safe doing so. One of the best things you can do is say right from the start that it is okay to
        change your mind.
        The fourth step is to acknowledge the experience of the other person so that they feel that you understand them,
        that is, do not question the intentions of the other person when they express their thoughts. But there are also
        a few cases where this is necessary when you present your thoughts to refute what they say.
        The fifth and final step is to never forget the purpose of the conversation. If you feel you have to speak your
        mind, make sure people understand you are doing it for the good of the company.
    </p>
    <p style="font-size: 120%; font-weight: 600; letter-spacing: 0.04em; margin: 1em 0;">3. Be honest in your thoughts</p>
    <p style="text-indent: 20px;">
        As the head of the Startup, I may believe I am right – but unless I build consensus within the company, my
        beliefs are in vain.
        You must always seek the truth, no matter how your beliefs contradict that truth. In business, this means that
        your decisions must be based on facts, not on the person presenting that opinion.
        Great companies have a culture of innovation, fueled by collaboration and the ability to change. The best
        companies and employees have a curiosity to continually learn and grow – as well as a desire to find better and
        more productive ways to do things.
    </p>
',
Null, 
'SE811131', 
Null,
2,
'SP20001')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Đóng bỉm...',
N'<p><img alt="" src="https://uni.fpt.edu.vn/Data/Sites/1/News/6335/125891036_3636408889731179_7555913294350460710_o.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>
<p style="text-indent: 20px; line-height:2;">Bạch thầy, ai rồi cũng sẽ say mê một cái gì đó. Và khi say mê thì người ta sẽ quên cả giờ giấc, đất trời, ăn uống...
Con đã từng thấy thầy say mê như thế. Nhưng con lăn tăn là làm sao thầy có thể say mê code đến mức cả gần 5 tiếng đồng hồ mà không ngồi dậy nghỉ ngơi. Con còn nghe đồn thầy đã từng code trong thời gian dài hơn nữa kìa?"
"Ơ kìa, chính chú nói rằng đã say mê thứ này thì có thể quên thứ khác. 
Anh quên nghỉ ngơi thì cũng là hợp lẽ thôi mà."
"Ý con là thậm chí thầy không cần đi vệ sinh luôn á?"
"Ơ kìa, hồi bé mày đã ngủ ngon cả đêm mà vẫn không làm ướt nệm của má đấy thôi? 
Đóng bỉm viết code thì có gì sai?
Vì mục tiêu của mình là chiến không dừng lại để đến đích; thì khó khăn nào chả san bằng, hỉ..."
"Thầy làm con hoang mang quá, có thật vậy không thầy?"
"Ta gọi đó là bí thuật, con có thể thử mà, để đi đến tận cùng..."
* PS: 4h30p code SÁNG CHỦ NHẬT thông trưa không đi tè & ăn trưa đã lên sóng YouTube nha pà kon. 
Vừa live xong cách đây ít phút...
Yêu cả nhà 3000 ❤ </p>
<p style="width:100%; text-align:center; margin-top: 1em; font-size: 150%; font-weight: 600; letter-spacing: 0.08em;">HAPPY CODE - HAPPY MONEY - HAPPY LIFE</p>
</p>
',
NULL, 
null, 
'NTH',
2,
'SU78SE14')

INSERT INTO SharePost(Title, Details, Note, StudentId, SupervisorID, StateId, ProjectId)
VALUES(N'Đóng bỉm...',
N'<p><img alt="" src="https://uni.fpt.edu.vn/Data/Sites/1/News/6335/125891036_3636408889731179_7555913294350460710_o.jpg" style="float:left; height:269px; margin:10px; width:400px" /></p>
<p style="text-indent: 20px; line-height:2;">Bạch thầy, ai rồi cũng sẽ say mê một cái gì đó. Và khi say mê thì người ta sẽ quên cả giờ giấc, đất trời, ăn uống...
Con đã từng thấy thầy say mê như thế. Nhưng con lăn tăn là làm sao thầy có thể say mê code đến mức cả gần 5 tiếng đồng hồ mà không ngồi dậy nghỉ ngơi. Con còn nghe đồn thầy đã từng code trong thời gian dài hơn nữa kìa?"
"Ơ kìa, chính chú nói rằng đã say mê thứ này thì có thể quên thứ khác. 
Anh quên nghỉ ngơi thì cũng là hợp lẽ thôi mà."
"Ý con là thậm chí thầy không cần đi vệ sinh luôn á?"
"Ơ kìa, hồi bé mày đã ngủ ngon cả đêm mà vẫn không làm ướt nệm của má đấy thôi? 
Đóng bỉm viết code thì có gì sai?
Vì mục tiêu của mình là chiến không dừng lại để đến đích; thì khó khăn nào chả san bằng, hỉ..."
"Thầy làm con hoang mang quá, có thật vậy không thầy?"
"Ta gọi đó là bí thuật, con có thể thử mà, để đi đến tận cùng..."
* PS: 4h30p code SÁNG CHỦ NHẬT thông trưa không đi tè & ăn trưa đã lên sóng YouTube nha pà kon. 
Vừa live xong cách đây ít phút...
Yêu cả nhà 3000 ❤ </p>
<p style="width:100%; text-align:center; margin-top: 1em; font-size: 150%; font-weight: 600; letter-spacing: 0.08em;">HAPPY CODE - HAPPY MONEY - HAPPY LIFE</p>
</p>
',
NULL, 
null, 
'NTH',
2,
'SP20001')

INSERT INTO Favorite(Account , ProjectId) VALUES('audi@gmail.com', 'SU20SE02')

INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Đồ án rất hay, mình học hỏi rất được rất nhiều từ đồ án này', 'bebo@gmail.com', NULL, 'SU20SE02')

INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Đồ án rất hay, mình học hỏi rất được rất nhiều từ đồ án này', 'tienhltse151104@fpt.edu.vn', NULL, 'SP20001')
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Nhóm rất xuất sắc', 'nguyenlamthuyphuong25@gmail.com', NULL, 'SP20001')


INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Nhóm rất xuất sắc', 'bebo@gmail.com', NULL, 'SU20SE02')
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Đồ án rất hay, mình học hỏi rất được rất nhiều từ đồ án này', 'meowmeow@gmail.com', NULL, 'SU78SE14')
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Nhóm rất xuất sắc, đáng khen', 'vanTTN@gmail.com', NULL, 'SU78SE14')
INSERT INTO Comment(CommentContent, Account, PostId, ProjectId) 
VALUES (N'Tuyệt hảo', 'vanTTN@gmail.com', 2, null)
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Thật cảm Động', 'chipchip@gmail.com', 2, null)
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'gớt nước mắt mấy fen ơi', 'thang@gmail.com', 4, null)
INSERT INTO Comment(CommentContent, Account , PostId, ProjectId) 
VALUES (N'Tuyệt hảo quá', 'chipchip@gmail.com', 4, null)

INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE01', 'Timekeeping management by face recognition in LUG company', N'HỘI TRƯỜNG A', '12h30 - 15/12/2021', N'Có rất nhiều biến thể của Lorem Ipsum mà bạn có thể tìm thấy, nhưng đa số được biến đổi bằng cách thêm các yếu tố hài hước, các từ ngẫu nhiên có khi không có vẻ gì là có ý nghĩa.', 'https://cdn.pixabay.com/photo/2015/11/09/14/43/laptop-1035345_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE02', 'Smart city: Manage autonomous vehicle in resort', N'HỘI TRƯỜNG B', '16h00 - 16/12/2021', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://cdn.pixabay.com/photo/2018/04/08/05/35/cyborg-3300454_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE03', 'Smoking People Detection', N'HỘI TRƯỜNG B', '9h30 - 16/12/2021', N'Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'https://cdn.pixabay.com/photo/2018/04/13/20/31/city-3317493_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE04', 'Influencer Marketing Platform', N'HỘI TRƯỜNG A', '7h00 - 18/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE05', 'GSO inventory system', N'HỘI TRƯỜNG B', '7h00 - 19/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE06', 'A web-based classified ads app', N'HỘI TRƯỜNG A', '7h00 - 20/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE07', 'SMS notifications as a way to report crimes', N'HỘI TRƯỜNG B', '7h00 - 21/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE08', 'Online grading system', N'HỘI TRƯỜNG A', '7h00 - 22/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE09', 'Java tutorials for Android', N'HỘI TRƯỜNG A', '7h00 - 23/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE10', 'Artificial Intelligence and its effects on modern life', N'HỘI TRƯỜNG A', '7h00 - 24/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE11', 'The problems of virtual reality', N'HỘI TRƯỜNG B', '7h00 - 25/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE12', 'Educative mobile apps', N'HỘI TRƯỜNG A', '7h00 - 26/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE13', 'The internet and its future', N'HỘI TRƯỜNG A', '7h00 - 27/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE14', 'Decision support system for student information management', N'HỘI TRƯỜNG A', '7h00 - 28/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE15', 'The effect of intelligent cars on our transport system', N'HỘI TRƯỜNG A', '7h00 - 29/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE16', 'Learning apps for kids', N'HỘI TRƯỜNG B', '7h00 - 30/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE17', 'Intelligent homes', N'HỘI TRƯỜNG A', '9h00 - 30/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE18', 'The apps for booking airline tickets', N'HỘI TRƯỜNG A', '11h00 - 30/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE19', 'Discrete math gaming apps', N'HỘI TRƯỜNG B', '7h00 - 31/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')
INSERT INTO UpcomingProject([Id], ProjectName, [Location], [Date], [Description], [Image]) 
VALUES('SP23SE20', 'Mobile apps for financial forecasts', N'HỘI TRƯỜNG B', '9h00 - 31/12/2021', N'Chúng ta vẫn biết rằng, làm việc với một đoạn văn bản dễ đọc và rõ nghĩa dễ gây rối trí và cản trở việc tập trung vào yếu tố trình bày văn bản. ', 'https://cdn.pixabay.com/photo/2016/02/24/08/41/motherboard-1219352_960_720.jpg')


INSERT INTO Sensitive_word(banned_word)
VALUES
	('dume'), ('quan que'), ('duma')
--------------------------------------
Update Project 
Set IntroductionContent = N'A police robot told a woman to go away after she tried to report a violent brawl breaking out nearby – then trundled off while singing a song. Cogo Guebara rushed over to the motorized police officer and pushed its emergency alert button on seeing the brawl break out in Salt Lake Park, Los Angeles, last month. But instead of offering assistance, the egg-shaped robot, whose official name is HP RoboCop, barked at Guebara to ‘Step out of the way’. To add insult to injury, the high-tech device then rolled away while humming an ‘intergalactic tune’, pausing periodically to say ‘Please keep the park clean.’',
Details = N'<p><img alt="" src="https://metro.co.uk/wp-content/uploads/2019/10/comp-1570212652.png?quality=90&amp;strip=all&amp;zoom=1&amp;resize=644%2C362" style="float:right; height:337px; margin-left:10px; margin-right:10px; width:600px" /></p>

<p style="text-align: justify;"><big>&lsquo;It just kept ringing and ringing, and I kept pushing and pushing.&rsquo; The concerned bystander thought the five-foot tall robot might have needed to see her face before it began to work, so she crouched down in front of its camera.Department cops finally arrived 15 minutes later, after the row had ended. It left one woman with a bad head wound which saw her stretchered into an ambulance and taken to hospital for emergency treatment. Local Police Chief Cosme Lozano says the robots, which cost between $60,000 and $70,000 a year to lease, are still in a trial phase and that their alert buttons have not yet been activated. He said that law enforcement have not yet started advertising the robots crime-fighting activities. Any help requests are currently sent to a company called Knightscope, which creates and leases the robots. Lozano added that once the robot completes its trial, calls made using its alert button will be sent straight to dispatch. Other versions of the same model have previously hit the headlines after one fell into a fountain in Washington DC. And a third HP RoboCop struck a child while patrolling a mall in California&rsquo;s Silicon Valley.</big></p>
', 
Recap = N'<p><img alt="" src="https://pbs.twimg.com/media/D9ZVtE0UEAAee8i?format=jpg&amp;name=900x900" style="float:left; height:400px; margin-left:10px; margin-right:10px; width:300px" /></p>

<p style="text-align: justify;"><big>Knightscope&rsquo;s marketing materials and media&nbsp;<a href="https://www.houstonchronicle.com/business/technology/article/Security-robot-intrigues-River-Oaks-District-11946955.php">r</a>eporting&nbsp;suggest the technology can effectively recognize &ldquo;suspicious&rdquo; packages, vehicles, and people.&nbsp;</big></p>

<p style="text-align: justify;"><big>But when a robot is scanning a crowd for someone or something suspicious, what is it actually looking for? It&rsquo;s unclear what the company means. The decision to characterize certain actions and attributes as &ldquo;suspicious&rdquo; has to be made by someone. If robots are designed to think people wearing hoods are suspicious, they may target youth of color. If robots are programmed to zero in on people moving quickly, they may harass a jogger, or a pedestrian on a rainy day.</big></p>

<p style="text-align: justify;"><big>A robot&rsquo;s machine learning and so-called suspicious behavior detection will lead to racial profiling and other unfounded harrassement. This begs the question: Who gets reprimanded if a robot improperly harrasses an innocent person, or calls the police on them? Does the robot? The people who train or maintain the robot? When state violence is unleashed on a person because a robot falsely flagged them as suspicious, &ldquo;changing the programming&rdquo; of the robot and then sending it back onto the street will be little solace for a victim hoping that it won&rsquo;t happen again. And when programming errors cause harm, who will review changes to make sure they can address the real problem?&quot;</big></p>

<p style="text-align: justify;"><big>The next time you&rsquo;re at a protest and are relieved to see a robot rather than a baton-wielding officer, know that that robot may be using the IP address of your phone to identify your participation. This makes protesters vulnerable to reprisal from police and thus chills future exercise of constitutional rights.</big></p>

<p style="text-align: justify;"><big>&quot;When a device emitting a Wi-Fi signal passes within a nearly 500 foot radius of a robot,&rdquo; the company explains on its blog, &ldquo;actionable intelligence is captured from that device including information such as: where, when, distance between the robot and device, the duration the device was in the area and how many other times it was detected on site recently.&quot;</big></p>
', 
VideoUrl = 'https://www.youtube.com/embed/gIHXLVr44Nw'
Where ProjectId = 'SU19SE05'


INSERT INTO Project_Supervisor
Values('SU19SE05','NTH')
INSERT INTO Project_Supervisor
Values('SU19SE05','TTNV')
insert into ProjectImage([ImageUrl],[ProjectId])
Values('http://images6.fanpop.com/image/photos/37700000/Cyber-the-Police-Robot-robots-37755446-800-1032.jpg','SU19SE05')
insert into ProjectImage([ImageUrl],[ProjectId])
Values('https://www.inceptivemind.com/wp-content/uploads/2019/08/police-robots-traffic-china.jpg','SU19SE05')
insert into ProjectImage([ImageUrl],[ProjectId])
Values('https://www.eff.org/files/banner_library/knightscope_banner_0.jpg','SU19SE05')

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
/*
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

Select c.CommentDate
From Comment c 
Group by c.CommentDate

Select c.CommentContent, acc.Email, c.PostId, c.ProjectId
From Comment c inner join Account acc
on c.Account = acc.Email
Where c.CommentDate = '2022-03-09'


SELECT @CurrentDate As CurrentDateTime
From Comment c
Declare @CurrentDate DateTime = c.CommentDate


Select Convert(Date, @CurrentDate) As Aption1
*/
/*
Select sp.PostId, sp.Title, sp.CreateDate, sp.StudentId, sp.SupervisorID
From SharePost sp inner join States s
on sp.StateId = s.StateId
Where s.StateName = 'Approving'

select tm.StudentId, tm.MemberName, p.ProjectId, p.ProjectName, p.ProjectAva
From TeamMember tm inner join Project p
on tm.ProjectId = p.ProjectId
Where tm.MemberName LIKE N'%a%'

Select su.SupervisorID, su.SupervisorName, p.ProjectId, p.ProjectName, p.ProjectAva
from Supervisor su inner join Project_Supervisor ps
on su.SupervisorID = ps.SupervisorID
inner join Project p
on p.ProjectId = ps.ProjectId
Where su.SupervisorName LIKE N'%a%'

Select p.ProjectId, p.ProjectName, p.ProjectAva
From Project p
Where p.ProjectName LIKE N'%a%'
*/

Select p.ProjectId, p.ProjectName, p.ProjectAva
From TeamMember tm inner join Project p 
on tm.ProjectId = p.ProjectId
Where tm.Account = 'phuong@gmail.com'
