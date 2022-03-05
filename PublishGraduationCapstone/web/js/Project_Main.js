
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
var apiOrginal = "/PublishGraduationCapstone/api/project/showProjectDetails?projectId=";
var api = '';

if (projectId != "") {
    api = apiOrginal + projectId;
}

function showProjectTitle() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            document.getElementById("project-title").innerHTML = jsonData.projectName
            console.log("title ne: " + jsonData.projectName);

        }
    };
}
showProjectTitle();

function showProjectIntro() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);
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

        }
    };
}
showProjectIntro();

function showProjectMember() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            const memberList = document.querySelector('#project-team-container');
            let projects = new Array();


            jsonData.listMember.forEach(counter => {
                var project =
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
                projects.push(project);
            });
            memberList.innerHTML = projects.join('');
        }
    };
}
showProjectMember();

function showProjectSupervisor() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            const supervisorList = document.querySelector('#project-supervisors-container');
            let projects = new Array();


            jsonData.listSupervisor.forEach(counter => {
                var project =
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
                projects.push(project);
            });
            supervisorList.innerHTML = projects.join('');
        }
    };
}
showProjectSupervisor();

function showProjectDetails() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            document.getElementById("project-detail-text").innerHTML = jsonData.projectDetails;


        }
    };
}
showProjectDetails();

function showProjectRecap() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            document.getElementById("project-recap-text").innerHTML = jsonData.projectRecap;

        }
    };
}
showProjectRecap();

function showProjectImage() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            const imgList = document.querySelector('#project-images-container');
            let projects = new Array();


            jsonData.listUrlImage.forEach(counter => {
                var project =
                    `
                <div>
                <img id="project-images-img" src="${counter.imageUrl}">
                </div>
                `
                projects.push(project);
            });
            imgList.innerHTML = projects.join('');
        }
    };
}
showProjectImage();

function showOtherproject() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
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
                        <div class="project-sharing-other-container">
                            <p class="project-sharing-other-img-text">${counter.projectName}</p>
                            <p class="project-sharing-other-img-line"></p>
                            <p class="project-sharing-other-img-content">${counter.introductionContent}</p>
                            <p class="project-sharing-other-img-more">More...</p>
                        </div>
                        <img class="project-sharing-other-img-container"
                            src="${counter.projectAva}">
                            <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                    </div>
                `
                projects.push(project);
            };
            listProject.innerHTML = projects.join('');


        }
    }
}
showOtherproject();

function projectRedirect(div){
    var projectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(projectId);
    sessionStorage.setItem("projectId", projectId);
    
    location.replace("http://localhost:8084/PublishGraduationCapstone/Project_Main.html");
  }

  var input = document.getElementById("home-search-text");
  input.addEventListener("keydown", function(e) {
      if (e.keyCode === 13) {
          showSearchPage();
      }
    });

    function showComment(){
        var xhttp = new XMLHttpRequest();
        var apiShowcoment = "/PublishGraduationCapstone/api/project/showCommentsOfProject?projectId=SU20SE02";
       
        xhttp.open("GET", apiShowcoment);
        xhttp.send();
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                var res = this.responseText;
                var jsonData = JSON.parse(res);
                const listProject = document.querySelector('#project-commented-container-all');
    
                let projects = new Array();
                for (var i = jsonData.commentsOfProject.length-1; i >= 0 ; i--) {
                    console.log("mang dai: " + i)
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
