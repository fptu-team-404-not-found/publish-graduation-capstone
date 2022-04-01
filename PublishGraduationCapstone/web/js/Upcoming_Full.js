function login() {
    if (localStorage.getItem("email") != null && localStorage.getItem("email") != '') {
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

function showUpcomingTable(){
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
            const listUpcoming = document.querySelector('#table-body');
            let comingProjects = new Array();

            for (var i = 0; i < jsonData.length; i++) {
                var counter = jsonData[i];

                var project = `
                <tr class="table-header">
                <td>${counter.projectDate}</td>
                <td>${counter.projectLocation}</td>
                <td>${counter.projectName}</td>
                </tr>
                `

                comingProjects.push(project);
            };
            listUpcoming.innerHTML = comingProjects.join('');
        }
    };
}
showUpcomingTable();
