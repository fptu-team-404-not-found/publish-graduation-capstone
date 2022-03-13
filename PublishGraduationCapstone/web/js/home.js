function showLogin() {
    /*MainMainMain*/
    var hidden = document.getElementById('login-box');
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

function showOtherProject() {
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

            console.log(jsonData);
            const listProject = document.querySelector('#other-project-img');
            let projects = new Array();
            var arrayLenght = jsonData.otherProject.length;
            if (arrayLenght > 18)
                arrayLenght = 18;
            else
                arrayLenght = jsonData.otherProject.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData.otherProject[i];

                var project =
                    `
            <span class="other-img" onclick=" projectRedirect(this)">
            <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
            <img class="other-project-img" src="${counter.projectAva}" > 
            <p class="other-project-img-text">${counter.projectName}</p> 
            <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
            </a>
            </span>
            `
                projects.push(project);
            };
            listProject.innerHTML = projects.join('');


        }
    };
}
showOtherProject();

function showHightLight() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/getHighlightProjects";
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

            const listProject = document.querySelector('#hightlight-project-img');

            let projects = new Array();

            jsonData.getHighlightProjects.forEach(counter => {
                var project = `
                <div class="hightlight-img" onclick="projectRedirect(this)">
                <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                <div class="hightlight-img-container">
                    <p class="hightlight-img-text">${counter.projectName}</p>
                    <p class="hightlight-img-line"></p>
                    <p class="hightlight-img-content">${counter.introductionContent}</p>
                    <p class="hightlight-img-more">More...</p>
                    <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                </div>
                <img class="hightlight-project-img-container" src="${counter.projectAva}">
                </a>
                </div>

                `

                projects.push(project);
            });
            listProject.innerHTML = projects.join('');
        }
    };
}
showHightLight();

//search by enter
var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

//redirect to project detail
function projectRedirect(div) {
    var projectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(projectId);
    sessionStorage.setItem("projectId", projectId);
}


function showUpcoming(callback) {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/getUpcomingProjects";
    var jsonData = "";
    let comingProjects = new Array();
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

            const listComingProject = document.querySelector('#upcomming-project-img');
            var arrayLenght = jsonData.getUpcomingProjects.length;
            if (arrayLenght > 8)
                arrayLenght = 8;
            else
                arrayLenght = jsonData.getUpcomingProjects.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData.getUpcomingProjects[i];

                var project =
                    `
                        <div class="upcoming-img" onclick="showThisUpcoming(this)">
                            <div class="upcoming-img-container">
                                <p class="upcoming-text-img">${counter.projectName}</p>
                                <p class="upcoming-img-line"></p>
                                <p class="upcoming-img-team">TEAM NAME ???</p>
                                <p class="upcoming-img-content">${counter.projectDescription}</p>
                                <p class="upcoming-img-more">More...</p>
                                <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                            </div>
                            <img class="hightlight-project-img-container" src="${counter.projectImage}">
                        </div>    
                    `
                comingProjects.push(project);
            };
            listComingProject.innerHTML = comingProjects.join('');

            const firstComingProject = document.querySelector('#upcoming-content-big');
            var bigProject = `
            <p id="upcoming-comingUp">Coming Project Defense Session</p>
            <p id="upcoming-line"></p>
            <p id="upcoming-day">${jsonData.getUpcomingProjects[0].projectDate}</p>
            <p id="upcoming-place">${jsonData.getUpcomingProjects[0].projectLocation}</p>
            <p id="upcoming-name">${jsonData.getUpcomingProjects[0].projectName}</p>
            <p id="upcoming-intro">${jsonData.getUpcomingProjects[0].projectDescription}</p>
            `
            firstComingProject.innerHTML = bigProject;

            const firstComingImage = document.querySelector('#upcoming-img-big');
            var bigImage = `
                <img class="upcoming-img-big" src="${jsonData.getUpcomingProjects[0].projectImage}">
            `
            firstComingImage.innerHTML = bigImage;

            if (callback) callback(jsonData.getUpcomingProjects);
        }
    };
}

var comingProjects;

console.log(showUpcoming(function (jsonData) {
    console.log("Show ne :" + jsonData);
    comingProjects = jsonData;
}));

function showThisUpcoming(div) {
    var thisProjectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(thisProjectId);

    var i = 0;
    while (thisProjectId != comingProjects[i].projectId) {
        i++;
    }

    thisProjectDate = comingProjects[i].projectDate;
    thisProjectLocation = comingProjects[i].projectLocation;
    thisProjectName = comingProjects[i].projectName;
    thisProjectDescription = comingProjects[i].projectDescription;
    thisProjectImage = comingProjects[i].projectImage;

    const thisComingProject = document.querySelector('#upcoming-content-big');
    var bigProject = `
    <p id="upcoming-comingUp">Coming Project Defense Session</p>
    <p id="upcoming-line"></p>
    <p id="upcoming-day">${thisProjectDate}</p>
    <p id="upcoming-place">${thisProjectLocation}</p>
    <p id="upcoming-name">${thisProjectName}</p>
    <p id="upcoming-intro">${thisProjectDescription}</p>
    `
    thisComingProject.innerHTML = bigProject;

    const thisComingImage = document.querySelector('#upcoming-img-big');
    var bigImage = `
        <img class="upcoming-img-big" src="${thisProjectImage}">
    `
    thisComingImage.innerHTML = bigImage;
}


function startup() {
    if (localStorage.getItem("email") != null && localStorage.getItem("email") != '') {
        if (localStorage.getItem("roleId") == 3) {
            location.replace('http://localhost:8084/PublishGraduationCapstone/Staff_EditPost.html')
        }
        if (localStorage.getItem("roleId") == 4) {
            location.replace('http://localhost:8084/PublishGraduationCapstone/Admin_Main.html')
        }
        if (localStorage.getItem("roleId") == 1 || localStorage.getItem("roleId") == 2){
            initLogin();
        }
    }
    else{
        var cookies = document.cookie;
        if (cookies != '') {
            var xhttp = new XMLHttpRequest();
            var api = "/PublishGraduationCapstone/api/login/getLoginAccountInfo?accessToken=";
            console.log("cookies ne 1:" + cookies);
            var ccs = cookies.slice(6, cookies.length).trim();
            console.log("cookies ne 2:" + ccs);
            var url = api + ccs;
            xhttp.open("GET", url);
            xhttp.send();
    
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    //using data here
                    var res = this.responseText;
                    try {
                        var jsonData = JSON.parse(res);
                    } catch (e) {
                        alert(e);
                    }
                    localStorage.setItem("email", jsonData.email);
                    localStorage.setItem("name", jsonData.name);
                    localStorage.setItem("picture", jsonData.picture);
                    localStorage.setItem("roleId", jsonData.roleId);
                    initLogin()
                }
            };
     }
    }
}
     
startup();

function initLogin(){
    var username = localStorage.getItem("name")
    var picture = localStorage.getItem("picture")

        document.getElementById("icon-show-login").innerHTML = 
        `
        <img src="${picture}" alt=""> 
        `
        document.getElementById("login-box").innerHTML = 
        `
        <div id="user-main-small-control">
        <p id="user-main-small-name">${username}</p>
        <p id="user-main-small-bookmark"><i class="fa-solid fa-bookmark user-main-small-bookmark-icon"></i>Bookmark</p>
        <a href="/PublishGraduationCapstone/LogoutProcess" style="text-decoration: none;  color: #fff;" onclick="logout()">
        <p id="user-main-small-logout"><i class="fa-solid fa-right-from-bracket"></i>Log Out</p>
        </a>
        </div>
        `
}

function logout(){
    localStorage.clear();
    document.getElementById("icon-show-login").innerHTML = 
    `
    <i class="fa-solid fa-user"></i>
    `
    document.getElementById("login-box").innerHTML = 
    `
    <div id="home-login">
    <a
        style="text-decoration: none; color: #fff;" 
        href="https://accounts.google.com/o/oauth2/auth?scope=openid%20email%20profile&redirect_uri=http://localhost:8084/PublishGraduationCapstone/LoginProcess&response_type=code&client_id=905648126821-fs0vnje6r097kc3u2nar0d2p3rnrlh4l.apps.googleusercontent.com&approval_prompt=force&access_type=offline">
        <div name="button" type="button">Login</div>
    </a>
    </div>

    `
}
