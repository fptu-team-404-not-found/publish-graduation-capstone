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
initLogin();
 
const input = document.getElementById('preview-supervisor-img-input');
const image = document.getElementById('preview-supervisor-img');

input.addEventListener('change', (e) => {
    if (e.target.files.length) {
        const src = URL.createObjectURL(e.target.files[0]);
        image.src = src;
    }
});

function getLinkImg() {
    if (event.which == 13 || event.keyCode == 13) {
        var getLinkImg = document.getElementById('preview-supervisor-img-link').value;
        document.getElementById('preview-supervisor-img').src = getLinkImg;
        return false;
    }
    return true;
};

function sendSupervisor() {
    var idInput = document.querySelector('#admin-main-supervisor-create-new-id').value;
    var nameInput = document.querySelector('#admin-main-supervisor-create-new-name').value;
    var emailInput = document.querySelector('#admin-main-supervisor-create-new-mail').value;
    var positionInput = document.querySelector('#admin-main-supervisor-create-new-position').value;
    var informationInput = document.querySelector('#admin-main-supervisor-create-new-information').value;
    var imageInput = document.querySelector('#preview-supervisor-img-link').value;

    object = {
        'supervisorId': idInput,
        'supervisorName': nameInput,
        'supervisorImage': imageInput,
        'information': informationInput,
        'postion': positionInput,
        'email': emailInput
    }

    const jsonData = JSON.stringify(object);

    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/saveSupervisor";
    xhr.open("POST", api);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log("nanana : " + jsonData);
    xhr.send(jsonData);
}

function sendSupervisorsList(jsonData) {
    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/saveSupervisorsList";
    xhr.open("POST", api);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log("nanana : " + jsonData);
    xhr.send(jsonData);
}