function login() {
    if (localStorage.getItem("email") != null && localStorage.getItem("email") != '') {
        var username = localStorage.getItem("name")
        var picture = localStorage.getItem("picture")
        var role = localStorage.getItem("roleId")
        if(role != 2){
            document.getElementsByClassName("user-main-sharing-box")[0].style.display = "none"
        }
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
            document.getElementById("user-main-detail-image-container").innerHTML =
            `
            <img id="user-main-detail-image" src="${picture}" alt="">
            `
            document.getElementById("user-main-detail-name-container").innerHTML = username
        
    }
}
login();

function showLogin() {
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
    sessionStorage.setItem("keyword", usernameInput);
    location.replace("http://localhost:8084/PublishGraduationCapstone/search.html");
}

//show search page by enter
var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

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

function showSharing (){
    var mail =  "hoangNT@gmail.com"//localStorage.getItem("email");
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/user/showSharingPost?email="+mail;
    console.log("API: " + api)

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
            const listProject = document.querySelector('#user-main-sharing-havepost-container-box');

            let projects = new Array();
                jsonData.showSharingPost.forEach(counter => {
                    var project = `
                    <a href="User_Main_SharingPost_Have_Share.html" style="text-decoration: none">
                    <div id="user-main-sharing-havepost-container" onclick="projectRedirectUser(this)">
                    <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                    <div class="user-main-sharing-havepost-left-img">
                        <img id="user-main-sharing-havepost-img" src="${counter.projectAva}" alt="">
                    </div>
                    <div class="user-main-sharing-havepost-right">
                        <h3 class="user-main-sharing-havepost-title">${counter.projectName}</h3>
                    </div>
                    </div>
                    </a>
                    `
                    projects.push(project);
                });
                listProject.innerHTML = projects.join('');
            
        }
    };
}
showSharing ();
function projectRedirectUser(p) {
    var projectIdUser = p.querySelector('.upcoming-img-id').innerText;
    sessionStorage.setItem("projectIdUser", projectIdUser);
    console.log("projectIdUser ne: " + projectIdUser)
}

//chư có css, chứ có đổi mail thầy hoàng 