
 function showInfo() {
    var hidden = document.getElementById('user-main-small-control');
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

function login() {
    if (localStorage.getItem("email") == null || localStorage.getItem("email") == '') {
        var xhttp = new XMLHttpRequest();
        var api = "/PublishGraduationCapstone/api/login/getLoginAccountInfo?accessToken=";

        var cookies = document.cookie;
        console.log("cookies ne:" + cookies);
        var ccs = cookies.slice(6, cookies.length).trim();
        console.log("cookies ne 2:" + ccs);
        var url = api + ccs;
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
            }
        };
    }

}
login();