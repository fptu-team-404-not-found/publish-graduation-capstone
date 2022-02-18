//window.onload = 
function showAllProduct() {
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
                <p class="other-project-img-text">${counter.projectName}</p> </span>`

                projects.push(project);
            });
            listProject.innerHTML = projects.join('');
        }
    };
}
showAllProduct();

function renderProject() {

}
