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

var usernameInput = document.querySelector('input[name="home-search-text"]');
console.log('Lay1: ' + usernameInput);

var firstkeyword = sessionStorage.getItem('keyword');
console.log('lala: ' + firstkeyword);
var usernameInputText = '';
var apiOrginal = "/PublishGraduationCapstone/api/project/searchProject?keyword=";
var api = '';
usernameInput.onchange = function (e) {
  usernameInputText = e.target.value;
  console.log('lele: ' + usernameInputText);
  api = apiOrginal + usernameInputText;
  return api;
};

function init() {
  if (firstkeyword != "") {
    api = apiOrginal + firstkeyword;
    sessionStorage.setItem("keyword", "");
    showSearchPage();
  }
}


function showSearchPage() {
  var xhttp = new XMLHttpRequest();
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
      const listProject = document.querySelector('#search-result-projects');
      const resultCount = document.querySelector('#search-result-countresult');
      var count = jsonData.searchProject.length;
      let projects = new Array();

      resultCount.innerHTML = `<span>${count} Results</span>`;
      if (count == 0) {
        resultCount.innerHTML = `<span>${count} Results</span>`;
      }
      if (count == 1) {
        resultCount.innerHTML = `<span>${count} Result</span>`;
      }

      jsonData.searchProject.forEach(counter => {
        var project =
          `
                <span id="search-result-project" class="${counter.semester}" onclick="projectRedirect(this)">
                <a href="http://localhost:8084/PublishGraduationCapstone/Project_Main.html" style="text-decoration: none">
                <img class="search-result-project-image" src="${counter.projectAva}">
                <p class="search-result-project-title">${counter.projectName}</p>
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
init();

function change() {
  var SemesterCB = document.querySelectorAll(".models input[type='checkbox']");
  var filters = {
    Semester: getClassOfCheckedCheckboxes(SemesterCB),
  };
  
  filterResults(filters);
}

function getClassOfCheckedCheckboxes(checkboxes) {
  var classes = [];

  if (checkboxes && checkboxes.length > 0) {
    for (var i = 0; i < checkboxes.length; i++) {
      var cb = checkboxes[i];

      if (cb.checked) {
        classes.push(cb.getAttribute("rel"));
      }
    }
  }
  console.log("classes:" + classes);
  return classes;
}

function filterResults(filters) {
  var rElems = document.querySelectorAll("#search-result-projects span");
  var hiddenElems = [];
  console.log("ele search: " + rElems);

  if (!rElems || rElems.length <= 0) {
    return;
  }

  for (var i = 0; i < rElems.length; i++) {
    var el = rElems[i];


    if (filters.Semester.length > 0) {
      var isHidden = true;

      for (var j = 0; j < filters.Semester.length; j++) {
        var filter = filters.Semester[j];
        console.log("el: " + el);
        console.log("filter: " + filter);
        if (el.classList.contains(filter)) {
          isHidden = false;
          break;
        }
      }
      if (isHidden) {
        hiddenElems.push(el);
      }
    }

  }
  var count = rElems.length - hiddenElems.length
  if(count == 1){
    document.getElementById("search-result-countresult").innerHTML = `<span>${count} Result</span>`;
  }else{
    document.getElementById("search-result-countresult").innerHTML = `<span>${count} Results</span>`;
  }

  for (var i = 0; i < rElems.length; i++) {
    rElems[i].style.display = "inline-block";
  }

  if (hiddenElems.length <= 0) {
    return;
  }

  for (var i = 0; i < hiddenElems.length; i++) {
    hiddenElems[i].style.display = "none";
  }

}

var input = document.getElementById("home-search-text");
input.addEventListener("keydown", function (e) {
  if (e.keyCode === 13) {
    showSearchPage();
  }
});

function projectRedirect(div) {
  var projectId = div.querySelector('.upcoming-img-id').innerText;
  sessionStorage.setItem("projectId", projectId);
}

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