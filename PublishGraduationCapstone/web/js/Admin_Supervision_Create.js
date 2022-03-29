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
/*-------------------------------------------------------------------*/
 
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

function addSupervision() {
    var name = document.querySelector('#admin-main-supervisor-create-new-name').value;
    console.log(name);
}