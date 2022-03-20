
function ShowLogout() {
    var logOut = document.getElementById('user-main-small-control');
    if (logOut.style.display == 'none') {
        logOut.style.display = 'block';
    } else
        logOut.style.display = 'none';
}

function logout() {
    localStorage.clear();

}
function login() {
    if (localStorage.getItem("email") == null || localStorage.getItem("email") == '') {
        var match = document.cookie.match(new RegExp('(^| )' + "token" + '=([^;]+)'));
        if (match) {
            console.log("match: " + match[2]);
            var xhttp = new XMLHttpRequest();
            var api = "/PublishGraduationCapstone/api/login/getLoginAccountInfo?accessToken=";
            var url = api + match[2];
            xhttp.open("GET", url);
            xhttp.send();

            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    //using data here
                    var res = this.responseText;
                    try {
                        var jsonData = JSON.parse(res);
                    } catch (e) {
                        alert(e);
                    }
                    localStorage.setItem("email", jsonData.email);
                    localStorage.setItem("name", jsonData.name);
                    localStorage.setItem("picture", jsonData.picture);
                    localStorage.setItem("roleId", jsonData.roleId);
                    initLogin()
                };
            }
        }
    } else {
        initLogin();
    }
}
login();


function initLogin() {
    var username = localStorage.getItem("name")
    var picture = localStorage.getItem("picture")

    document.getElementById("admin-main-nav-login-img-container").innerHTML =
        `
        <img id="admin-main-nav-login-img" src="${picture}" alt="">
        `
    document.getElementById("admin-main-nav-login").innerHTML = username
    document.getElementById("user-main-small-name").innerHTML = username
}

