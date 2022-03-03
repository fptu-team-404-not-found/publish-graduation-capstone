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

            var jsonData = JSON.parse(res);

            const listProject = document.querySelector('#other-project-img');
            
           let projects = new Array();
          
            jsonData.otherProject.forEach(counter => {
                var project =`
                <span class="other-img">
                <img class="other-project-img" src="${counter.projectAva}" > 
                <p class="other-project-img-text">${counter.projectName}</p> 
                <p class="upcoming-img-id" style="display: none">${counter.projectId}</p>
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