function showLogin() {
    /*Alo alo alo alo Theng*/
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

function showOtherProject() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {


            var res = this.responseText;

            var jsonData = JSON.parse(res);
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
            <span class="other-img">
            <img class="other-project-img" src="${counter.projectAva}" > 
            <p class="other-project-img-text">${counter.projectName}</p> 
            <div class="hidden">${counter.projectId}</div>
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
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {


            var res = this.responseText;

            var jsonData = JSON.parse(res);

            const listProject = document.querySelector('#hightlight-project-img');

            let projects = new Array();

            jsonData.getHighlightProjects.forEach(counter => {
                var project = `
                <div class="hightlight-img">
                <div class="hightlight-img-container">
                    <p class="hightlight-img-text">${counter.projectName}</p>
                    <p class="hightlight-img-line"></p>
                    <p class="hightlight-img-team">${counter.teamName}</p>
                    <p class="hightlight-img-content">${counter.introductionContent}</p>
                    <p class="hightlight-img-more">More...</p>
                    <div class="hidden">${counter.projectId}</div>
                </div>
                <img class="hightlight-project-img-container" src="${counter.projectAva}">
                </div>
                `

                projects.push(project);
            });
            listProject.innerHTML = projects.join('');
        }
    };
}
showHightLight();

var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function(e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

function showUpcoming(callback) {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/getUpcomingProjects";
    var jsonData = "";
    let comingProjects = new Array();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            jsonData = JSON.parse(res);
            console.log("upcoming nè: ")
            console.log(jsonData);
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

console.log(showUpcoming(function(jsonData) {
    console.log("Show ne :" + jsonData);
    comingProjects = jsonData;
}));

function hoverUpcoming() {

}

function showThisUpcoming(div) {
    var thisProjectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(thisProjectId);

    var i = 0;
    while (thisProjectId != comingProjects[i].projectId) {
        i++;
    }
    console.log(i);

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