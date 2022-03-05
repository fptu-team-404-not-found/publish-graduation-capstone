
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

var projectId = sessionStorage.getItem('projectId');
    var apiOrginal = "/PublishGraduationCapstone/api/project/showProjectDetails?projectId=";
    var api = '';

    if (projectId != "") {
        api = apiOrginal + projectId;
    }

function showProjectTitle(){
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            document.getElementById("project-title").innerHTML = jsonData.projectName
            console.log("title ne: "+ jsonData.projectName);
              
        }
    };
}
showProjectTitle();

function showProjectIntro(){
   var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;

            var jsonData = JSON.parse(res);

            document.getElementById("intro-information").innerHTML = jsonData.projectIntro;
            
        }
    };
}
showProjectIntro();

function showProjectMember(){
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

function showProjectSupervisor(){
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

function showProjectDetails(){
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

function showProjectRecap(){
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

function showProjectImage(){
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