# PUBLISH GRADUATION CAPSTONE WEBAPP

:wave: Welcome to our Software Development Project (SWP391) :wave:

## Table of Contents
- [Description](#description)
- [Preview Screenshot](#preview-screenshot)
- [Technology](#technology)
- [Functional requirements](#functional-requirements)
- [Useful Resources](#useful-resources)
- [Contributors](#contributors)
- [References](#references)
- [License & Copyright](#license--copyright)

## Description
- This is a website to publish the graduation projects of final year students
- This web works well on desktop, not for mobile
- This project started from 05-01-2022 to 06-04-2022

## Preview Screenshot

<div align="center">
  <img src="./imgs/home-1.png" alt="Home 1" width="45%"></img> &nbsp;&nbsp; <img src="./imgs/home-2.png" alt="Home 2" width="45%"></img>
  <img src="./imgs/sharing.png" alt="Sharing" width="45%"></img> &nbsp;&nbsp; <img src="./imgs/project-details.png" alt="Project Details" width="45%"></img>
  <img src="./imgs/search.png" alt="Search Page" width="45%"></img> &nbsp;&nbsp; <img src="./imgs/admin-account-list.png" alt="Admin Account List" width="45%"></img>
  <img src="./imgs/admin-post-list.png" alt="Admin Post List" width="45%"></img> &nbsp;&nbsp; <img src="./imgs/admin-supervisor-list.png" alt="Admin Supervisor List" width="45%"></img> 
  <img src="./imgs/admin-upcoming.png" alt="Admin Upcoming List" width="45%"></img> &nbsp;&nbsp; <img src="./imgs/admin-word-list.png" alt="Admin Word List" width="45%"></img>
</div>
  
## Technology
**1. Frontend**
  - HTML, CSS, JavaScript
  - XMLHttpRequest

**2. Backend**
  - Java Language

**3. Database**
  - Microsoft SQL Server - a relational model database server produced by Microsoft
  - Full-Text Search - SQL Server

**4. Other Technologies**
- RESTful API
- Oauth2
- MVC2 Model

**5. Tool**
  - Netbeans 8.2
  - Java JDK 8
  - Apache Tomcat 8.5.29
  - Figma
  - Postman
  - StarUML
  - Visual Studio Code
  - Microsoft SQL Server Management Studio 18

## Functional requirements
**1. Guest:**
- [x] Login by Gmail
- [x] View projects
- [x] Search projects by project's name, supervisor's name, team member's name
- [x] Bookmark favorite project
- [x] Comment in project

**2. Contributor (Team member, suppervior, company)**
- [x] Basic functions of normal users
- [x] Share related to the project
- [x] Post the sharing of the project

**3. Editor (Academic staff)**
- [x] Post basic information of the project such as name, project defense date, member,...

**4. Admin:**
- [x] Approve post from the editor
- [x] Approve shared post the contributor
- [x] Filter comments
- [x] Managing supervisors
- [x] Account Management

## Useful Resources

#| #| Name | Description
-| -| ---- | -----------
1| -| [Main Project Folder](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/tree/main/PublishGraduationCapstone) | Source code
2| -| [Database Folder](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/tree/main/database) | -
-| 2.1| [Database Script](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/blob/main/database/ScriptDatabase.sql) | SQL Scipt
-| 2.2| [Database StarUML Model File](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/blob/main/database/SWP391.mdj) | -
-| 2.3| [Database ERD Diagram](https://raw.githubusercontent.com/fptu-team-404-not-found/publish-graduation-capstone/main/imgs/database-ERD.png) | - 
-| 2.4| [Database Diagram](https://raw.githubusercontent.com/fptu-team-404-not-found/publish-graduation-capstone/main/imgs/database-diagram.png) | -
3| -| [UI Design](https://www.figma.com/file/8bXKMQcuvUHcne1PlG5mlE/Project-Siu-%C4%90%E1%BB%89n?node-id=151%3A368) | UI design on Figma
4| -| [Library Folder](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/tree/main/PublishGraduationCapstone/lib) | Useful libraries
5| -| [Presentation Slide](https://www.canva.com/design/DAE8klTREvM/i1Ct1HMotGMVPdgMNADOmg/view) | Presentation slide for defense day

## Contributors
**1. Mentors:**
- Lecturer - Main Mentor: [Nguyen The Hoang - giáo-làng](https://github.com/doit-now)
- Adviser: Lecturer Kieu Trong Khanh

**2. Members:**
- [Tran Ngoc Thang](https://github.com/thangtn2101) - SE151478 - **Leader | Database Designer | Front-end Developer**
- [Nguyen Dao Duc Quan](https://github.com/dq-qiji) - SE151008 - **UI Designer | Front-end Developer**
- [Nguyen Lam Thuy Phuong](https://github.com/nguyenlamthuyphuong25) - 	**SE150999 - UI Designer | Front-end Developer**
- [Huynh Le Thuy Tien](https://github.com/tienhuynh-tn) - SE151104 - **Back-end Developer | Database Designer**
- [Tran Thanh Dat](https://github.com/DatTranLK) - SE151444 - **Back-end Developer**

## References
- [OpenID Connect](https://developers.google.com/identity/protocols/oauth2/openid-connect)
- [Hướng dẫn thực hiện chức năng login vào ứng dụng bằng tài khoản facebook - Phạm Huy Hoàng](http://www.kieutrongkhanh.net/2016/08/huong-dan-thuc-hien-chuc-nang-login-vao.html)
- [XMLHttpRequest](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest)
- [Giới thiệu về RESTful Web Services – công nghệ tạo web services đơn giản so với các dạng web services trước kia](http://www.kieutrongkhanh.net/2016/08/gioi-thieu-ve-restful-web-services-cong.html)
- [Java + JSP: Login Google - Youtube](https://youtu.be/bCkGaym6SSQ)
- [Login with Google using Java - Tutorial](http://blog.sodhanalibrary.com/2014/11/login-with-google-with-java-tutorial.html#.Yy6SJ3bP23B)
- [Integrate login with google in Websites with JAVA](https://chillyfacts.com/integrate-login-with-google-in-websites-with-java/)

## License & Copyright
&copy; 2022 fptu-team-404-not-found Licensed under the [GPL-3.0 LICENSE](https://github.com/fptu-team-404-not-found/publish-graduation-capstone/blob/main/LICENSE).
