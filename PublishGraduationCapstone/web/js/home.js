function showLogin() {
    /*MainMainMain*/
    var hidden = document.getElementById('login-box');
    if (hidden.style.display === 'none')
        hidden.style.display = 'block';
    else
        hidden.style.display = 'none';
}

function showSearch() {
    var hidden = document.getElementById('home-search-container');
    if (hidden.style.display === 'none')
        hidden.style.display = 'block';
    else
        hidden.style.display = 'none';
}

function showSearchPage() {
    var usernameInput = document.querySelector('#home-search-text').value;

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
            }
            ;
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
    sessionStorage.setItem("projectId", projectId);
}


function showUpcoming() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/getUpcomingProjects";
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
            const listUpcoming = document.querySelector('#right-upcoming-project-all');
            let comingProjects = new Array();

            var arrayLenght = jsonData.length;
            if (arrayLenght > 5)
                arrayLenght = 5;
            else
                arrayLenght = jsonData.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData[i];

                var project = `
                <div class="right-upcoming-project">
                <p class="right-upcoming-project-date">${counter.projectDate}</p>
                <span class="right-upcoming-project-place-icon"><i class="fa-solid fa-location-dot"></i></span>
                <span class="right-upcoming-project-place">${counter.projectLocation}</span>
                <p class="right-upcoming-project-topic">${counter.projectName}</p>
                </div>
                `

                comingProjects.push(project);
            };
            listUpcoming.innerHTML = comingProjects.join('');
        }
    };
}
showUpcoming();


function startup() {
    console.log("email: " + localStorage.getItem("email"))
    if (localStorage.getItem("email") != null) {
        if (localStorage.getItem("roleId") == 3) {
            location.replace('http://localhost:8084/PublishGraduationCapstone/Staff_Approved.html')
        }
        if (localStorage.getItem("roleId") == 4) {
            location.replace('http://localhost:8084/PublishGraduationCapstone/Admin_Main.html')
        }
        if (localStorage.getItem("roleId") == 1 || localStorage.getItem("roleId") == 2) {
            initLogin();
        }
    } else {
        var match = document.cookie.match(new RegExp('(^| )' + "token" + '=([^;]+)'));
        if (match) {
            console.log("match: " + match[2]);
            var xhttp = new XMLHttpRequest();
            var api = "/PublishGraduationCapstone/api/login/getLoginAccountInfo?accessToken=";
            var url = api + match[2];
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
                };
            }
        } 
    }
}
startup();

function initLogin() {
    var username = localStorage.getItem("name")
    var picture = localStorage.getItem("picture")

    document.getElementById("icon-show-login").innerHTML =
            `
        <img src="${picture}" alt=""> 
        `
    document.getElementById("login-box").innerHTML =
        `
        <div id="user-main-small-control">
        <a href="User_Profile.html" style="text-decoration: none;  color: #fff;">
        <p id="user-main-small-name">${username}</p>
        </a>
        <a href="User_Main.html" style="text-decoration: none;  color: #fff;">
        <p id="user-main-small-bookmark"><i class="fa-solid fa-bookmark user-main-small-bookmark-icon"></i>Bookmark</p>
        </a>
        <a href="/PublishGraduationCapstone/LogoutProcess" style="text-decoration: none;  color: #fff;" onclick="logout()">
        <p id="user-main-small-logout"><i class="fa-solid fa-right-from-bracket"></i>Log Out</p>
        </a>
        </div>
        `
}

function logout() {
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
