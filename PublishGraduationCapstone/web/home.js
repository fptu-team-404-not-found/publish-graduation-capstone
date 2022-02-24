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

function showOtherProject() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {


            var res = this.responseText;

            var jsonData = JSON.parse(res);
            console.log(jsonData);
            const listProject = document.querySelector('#other-project-img');
            let projects = new Array();
            var arrayLenght = jsonData.otherProject.length;
            if(arrayLenght>18)
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
    xhttp.onreadystatechange = function () {
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

function showUpcoming(){

}

function hoverUpcoming(){

}

