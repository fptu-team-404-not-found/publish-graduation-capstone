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
            <p id="user-main-small-name">${username}</p>
            <p id="user-main-small-bookmark"><i class="fa-solid fa-bookmark user-main-small-bookmark-icon"></i>Bookmark</p>
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

var usernameInput = document.querySelector('#home-search-text').value;
console.log('lala: ' + usernameInput);

function showSearchPage() {
    usernameInput = document.querySelector('#home-search-text').value;
    console.log('lala: ' + usernameInput);
    sessionStorage.setItem("keyword", usernameInput);

    location.replace("http://localhost:8084/PublishGraduationCapstone/search.html");
}

function showAllProduct() {
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

            jsonData.otherProject.forEach(counter => {
                var project = `
                <span class="other-img" onclick=" projectRedirect(this)">
                <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                <img class="other-project-img" src="${counter.projectAva}" > 
                <p class="other-project-img-text">${counter.projectName}</p> 
                <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                </a>
                </span>
                `

                projects.push(project);
            });
            listProject.innerHTML = projects.join('');
        }
    };
}
showAllProduct();

var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

function projectRedirect(div) {
    var projectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(projectId);
    sessionStorage.setItem("projectId", projectId);
}