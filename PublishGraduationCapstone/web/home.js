function showOtherProject() {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/project/showOtherProjects";
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {


            var res = this.responseText;

            var jsonData = JSON.parse(res);

            const listProject = document.querySelector('#other-project-img');
            
           let projects = new Array();
          
            jsonData.otherProject.forEach(counter => {
                var project =`
                <span class="other-img">
                <img class="other-project-img" src="${counter.projectAva}" > 
                <p class="other-project-img-text">${counter.projectName}</p> </span>
                `

                projects.push(project);
            });
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
                var project =`
                <div class="hightlight-img">
                <div class="hightlight-img-container">
                    <p class="hightlight-img-text">${counter.projectName}</p>
                    <p class="hightlight-img-line"></p>
                    <p class="hightlight-img-team">${counter.teamName}</p>
                    <p class="hightlight-img-content">${counter.introductionContent}</p>
                    <p class="hightlight-img-more">More...</p>
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
showHightLight() ;

