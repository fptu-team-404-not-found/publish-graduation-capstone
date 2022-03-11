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