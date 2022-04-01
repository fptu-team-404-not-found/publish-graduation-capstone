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

function sendUserData(jsonData) {
    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/changeAccountList";
    xhr.open("POST", api);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log("nanana : " + jsonData);
    xhr.send(jsonData);
}