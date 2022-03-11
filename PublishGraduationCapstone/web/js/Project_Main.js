window.onload=function(){
    console.log("cc"+ document.cookie)
}

function showLogin() {
    var hidden = document.getElementById('home-login');
    if (hidden.style.display === 'none')
        hidden.style.display = 'block';
    else hidden.style.display = 'none';
}

function showSearch() {
    var hidden = document.getElementById('home-search-container');
    if (hidden.style.display === 'none')
        hidden.style.display = 'block';
    else hidden.style.display = 'none';
}


function showSearchPage() {
    var usernameInput = document.querySelector('#home-search-text').value;
    console.log('lala: ' + usernameInput);

    sessionStorage.setItem("keyword", usernameInput);

    location.replace("http://localhost:8084/PublishGraduationCapstone/search.html");
}


var projectId = sessionStorage.getItem('projectId');


function showProject() {
    var apiOrginal = "/PublishGraduationCapstone/ProjectDetailServlet?projectId=";
    var api = '';

    if (projectId != "") {
        api = apiOrginal + projectId;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            try {
                var jsonData = JSON.parse(res);
              } catch (e) {
                alert(e);
              }
            //show title
            document.getElementById("project-title").innerHTML = jsonData.projectName
            //show intro
            var video = 'https://www.youtube.com'
            if (jsonData.videoUrl != null)
                video = jsonData.videoUrl
            document.getElementById("intro-information").innerHTML =
                `
            <p id="intro-information-text">
                    <iframe id="intro-information-vid" src="${video}" 
                    title="YouTube video player" frameborder="0" 
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen></iframe>
                    ${jsonData.introductionContent}
            </p>
            `
            //Show member
            const memberList = document.querySelector('#project-team-container');
            let projectsMembers = new Array();
            jsonData.listMember.forEach(counter => {
                var projectMember =
                    `
                <div class="project-team-member">
                <div class="project-team-member-color-black">
                </div>
                <img class="project-team-member-img" src="${counter.memberAva}" />
                <p class="project-team-member-name">${counter.memberName}</p>
                <p class="project-team-line"></p>
                <span class="project-mail-icon"><i class="fa-solid fa-envelope"></i></span>
                <span class="project-mail-text">${counter.memberEmail}</span>
                <br />
                <span class="project-phone-icon"><i class="fa-solid fa-phone"></i></span>
                <span class="project-phone-text">${counter.memberPhone}</span>
                </div>
                `
                projectsMembers.push(projectMember);
            });
            memberList.innerHTML = projectsMembers.join('');

            //show supervisor
            const supervisorList = document.querySelector('#project-supervisors-container');
            let projectsSupervisorS = new Array();

            jsonData.listSupervisor.forEach(counter => {
                var supervisor =
                    `
                <div class="project-supervisor">
                <div class="project-supervisor-img-class"><img class="project-supervisor-img"
                        src="${counter.supervisorImage}" />
                </div>
                <div class="project-supervisor-text">
                    <p class="project-supervisor-text-name">${counter.supervisorName}</p>
                    <p class="project-supervisor-text-line"></p>
                    <p class="project-supervisor-text-subject">${counter.supervisorPosition}</p>
                    <p class="project-supervisor-text-quote">${counter.supervisorInformation}</p>
                </div>
                </div>
                `
                projectsSupervisorS.push(supervisor);
            });
            supervisorList.innerHTML = projectsSupervisorS.join('');

            //show project details
            document.getElementById("project-detail-text").innerHTML = jsonData.details;
            //show project recap
            document.getElementById("project-recap-text").innerHTML = jsonData.recap;
            //show project image
            const imgList = document.querySelector('#project-images-container');
            let projectsImages = new Array();

            jsonData.listUrlImage.forEach(counter => {
                var image =
                    `
                <div>
                <img id="project-images-img" src="${counter.imageUrl}">
                </div>
                `
                projectsImages.push(image);
            });
            imgList.innerHTML = projectsImages.join('');
        }
    };
}
showProject();

function showOtherproject() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            try {
                var jsonData = JSON.parse(res);
              } catch (e) {
                alert(e);
              }
            const listProject = document.querySelector('#project-sharing-other-project-container');
            let projects = new Array();
            var arrayLenght = jsonData.otherProject.length;
            if (arrayLenght > 3)
                arrayLenght = 3;
            else
                arrayLenght = jsonData.otherProject.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData.otherProject[i];
                var project =
                    `
                    <div class="project-sharing-other-img" onclick="projectRedirect(this)">
                    <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                        <div class="project-sharing-other-container">
                            <p class="project-sharing-other-img-text">${counter.projectName}</p>
                            <p class="project-sharing-other-img-line"></p>
                            <p class="project-sharing-other-img-content">${counter.introductionContent}</p>
                            <p class="project-sharing-other-img-more">More...</p>
                        </div>
                        <img class="project-sharing-other-img-container"
                            src="${counter.projectAva}">
                            <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                    </a>
                    </div>
                    `
                projects.push(project);
            };
            listProject.innerHTML = projects.join('');


        }
    }
}
showOtherproject();

function showComment() {
    var xhttp = new XMLHttpRequest();
    var apiShowcoment = "/PublishGraduationCapstone/api/project/showCommentsOfProject?projectId=";
    var apiComment = apiShowcoment + projectId;
    xhttp.open("GET", apiComment);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            try {
                var jsonData = JSON.parse(res);
              } catch (e) {
                alert(e);
              }
            const listProject = document.querySelector('#project-commented-container-all');

            let projects = new Array();
            for (var i = jsonData.commentsOfProject.length - 1; i >= 0; i--) {
                var counter = jsonData.commentsOfProject[i];
                var project = `
                    <div id="project-commented-container">
                        <span id="project-commented-img"><img id="project-commented-img-1" src="${counter.userAva}"
                                alt="ava img"></span>
                        <span id="project-commented-text">${counter.userName}</span>
                        <span id="project-commented-line">-</span>
                        <span id="project-commented-date">${counter.commentDate}</span>
                        <p id="project-commented-leave-text">${counter.commentContent}</p>
                    </div>
                    `
                projects.push(project);
            }
            listProject.innerHTML = projects.join('');
        }
    };
}
showComment();

function showSharePostList() {
    var xhttp = new XMLHttpRequest();
    var apishowSharePost = "/PublishGraduationCapstone/api/share/showSharePostList?projectId=";
    var apiSharePost = apishowSharePost + projectId;

    xhttp.open("GET", apiSharePost);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            try {
                var jsonData = JSON.parse(res);
              } catch (e) {
                alert(e);
              }

            const listProject = document.querySelector('#project-sharing-container-all');

            let projects = new Array();
            var arrayLenght = jsonData.showSharePostList.length;
            if (arrayLenght > 3)
                arrayLenght = 3;
            else
                arrayLenght = jsonData.showSharePostList.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData.showSharePostList[i];
                var project =
                    `
                    <div class="project-sharing-experience-container" onclick="sharePostRedirect(this)">
                    <a href="http://localhost:8084/PublishGraduationCapstone/Sharing_Main.html" style="text-decoration: none">
                        <div id="project-sharing-experience-img-container">
                        <img id="project-sharing-experience-img" src="${counter.Avatar}" alt="">
                        </div>
                        <div id="project-sharing-experience-text-container">
                        <p>${counter.title}</p>
                        <p class="project-sharing-experience-text-viewmore">View more...</p>
                        </div>
                        <p class="share-id" style="display: none">${counter.postId}</p>
                    </a>
                    </div>
                    
                `
                projects.push(project);
            };
            listProject.innerHTML = projects.join('');
        }
    };
}
showSharePostList();

//redirect to project 
function projectRedirect(div) {
    var projectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(projectId);
    sessionStorage.setItem("projectId", projectId);
}

//show search page by enter
var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

//redirect to share post
function sharePostRedirect(div) {
    var sharePostId = div.querySelector('.share-id').innerText;
    console.log(sharePostId);
    sessionStorage.setItem("sharePostId", sharePostId);

}

// function writeComment(){
//     var xhttp = new XMLHttpRequest();
//     var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
//     xhttp.open("GET", api);
//     xhttp.send();
//     xhttp.onreadystatechange = function () {
//         if (this.readyState === 4 && this.status === 200) {
//             var res = this.responseText;
//             try {
//                 var jsonData = JSON.parse(res);
//               } catch (e) {
//                 alert(e);
//               }
         


//         }
//     }
// }

