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
    sessionStorage.setItem("keyword", usernameInput);
    location.replace("http://localhost:8084/PublishGraduationCapstone/search.html");
}

var sharePostId = sessionStorage.getItem('sharePostId');

//show content of a share post 
function showSharePostContent() {
    var apiOrginal = "/PublishGraduationCapstone/api/share/sharePostDetail?postId=";
    var api = '';
    if (sharePostId != "") {
    api = apiOrginal + sharePostId;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            //show share post title 
            document.getElementById("sharing-main-title").innerHTML = jsonData.sharePostDetail.title
            //show share post details
            document.getElementById("sharing-main-content").innerHTML = jsonData.sharePostDetail.details
            //show share post date
            document.getElementById("sharing-main-date").innerHTML = jsonData.sharePostDetail.createDate
            //show author name
            document.getElementById("sharing-main-poster").innerHTML = jsonData.sharePostDetail.AuthorName
            //show belong project 
            document.getElementById("sharing-main-right-container").innerHTML =
            `
            <div class="project-sharing-belongs-container" onclick="projectRedirect(this)">
            <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                <p class="project-sharing-belongs-img-text">${jsonData.sharePostDetail.project.ProjectName}</p>
                <p class="project-sharing-belongs-img-line"></p>
                <p class="project-sharing-belongs-img-content">${jsonData.sharePostDetail.project.ProjectIntroduction}</p>
                <p class="project-sharing-belongs-img-more">More...</p>
                <p class="upcoming-img-id" style="display: none">${jsonData.sharePostDetail.project.ProjectId}</p>
            </a>
            </div>
            <img class="project-sharing-belongs-img-container"
                src="${jsonData.sharePostDetail.project.ProjectAva}">
            `
            document.getElementById("sharing-main-right-topic").innerHTML = jsonData.sharePostDetail.project.ProjectName
            var projectId = jsonData.sharePostDetail.project.ProjectId
            showSharePostList(projectId);
        }
    };
}
showSharePostContent() ;

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
                    <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                        <div class="project-sharing-other-container">
                            <p class="project-sharing-other-img-text">${counter.projectName}</p>
                            <p class="project-sharing-other-img-line"></p>
                            <p class="project-sharing-other-img-content">${counter.introductionContent}</p>
                            <p class="project-sharing-other-img-more">More...</p>
                        </div>
                        <img class="project-sharing-other-img-container"
                            src="${counter.projectAva}">
                            <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
                    </a>
                    </div>
                `
                projects.push(project);
            };
            listProject.innerHTML = projects.join('');
        }
    }
}
showOtherproject();

function showSharePostList(projectId){
    var xhttp = new XMLHttpRequest();
    var apishowSharePost = "/PublishGraduationCapstone/api/share/showSharePostList?projectId=";
    var apiSharePost = apishowSharePost+projectId;
    console.log("project ID: "+projectId)
    xhttp.open("GET", apiSharePost);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);

            const listProject = document.querySelector('#project-sharing-container-all');

            let projects = new Array();
            var arrayLenght = jsonData.showSharePostList.length;
            if (arrayLenght > 3)
                arrayLenght = 3;
            else
                arrayLenght = jsonData.showSharePostList.length;

            for (var i = 0; i < arrayLenght; i++) {
                var counter = jsonData.showSharePostList[i];
                if(counter.postId != sharePostId){
                    var project =
                    `
                    <div class="project-sharing-experience-container" onclick="sharePostRedirect(this)">
                    <a href="http://localhost:8084/PublishGraduationCapstone/Sharing_Main.html"style="text-decoration: none">
                    <div id="project-sharing-experience-img-container">
                        <img id="project-sharing-experience-img" src="${counter.Avatar}" alt="">
                    </div>
                    <div id="project-sharing-experience-text-container">
                        <p>${counter.title}</p>
                        <p class="project-sharing-experience-text-viewmore">View more...</p>
                    </div>
                    <p class="share-id" style="display: none">${counter.postId}</p>
                    </a>
                    </div>
                `
                projects.push(project);
                }
            };
            listProject.innerHTML = projects.join('');
        }
    };
}

//redirect to project 
function projectRedirect(div) {
    var projectId = div.querySelector('.upcoming-img-id').innerText;
    console.log(projectId);
    sessionStorage.setItem("projectId", projectId);
}

//show search page by enter
var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        showSearchPage();
    }
});

//redirect to share post
function sharePostRedirect(div) {
    var sharePostId = div.querySelector('.share-id').innerText;
    console.log(sharePostId);
    sessionStorage.setItem("sharePostId", sharePostId);
}

//show commnet of share post 
function showComment() {
    var xhttp = new XMLHttpRequest();
    var apiShowcoment = "/PublishGraduationCapstone/api/share/showCommentsOfShare?shareId=";
    var apiComment = apiShowcoment + sharePostId;
    xhttp.open("GET", apiComment);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            const listProject = document.querySelector('#project-commented-container-all');

            let projects = new Array();
            for (var i = jsonData.commentsOfShare.length - 1; i >= 0; i--) {
                var counter = jsonData.commentsOfShare[i];
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
