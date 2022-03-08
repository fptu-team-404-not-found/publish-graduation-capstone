console.log('Team 404');

function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
    var profile = googleUser.getBasicProfile();
//    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
//    console.log('Full Name: ' + profile.getName());
//    console.log('Given Name: ' + profile.getGivenName());
//    console.log('Family Name: ' + profile.getFamilyName());
//    console.log("Image URL: " + profile.getImageUrl());
//    console.log("Email: " + profile.getEmail());


    // The ID token you need to pass to your backend:
    var access_token = googleUser.getAuthResponse().access_token;

    var url = '/PublishGraduationCapstone/LoginServlet?token=';
    url = url + access_token;

    var xhttp = new XMLHttpRequest();

    xhttp.open("GET", url);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //using data here

            var res = this.responseText;
            var jsonData = JSON.parse(res);

            jsonData.information.forEach(counter => {
                localStorage.setItem("email", counter.email);
                localStorage.setItem("name", counter.name);
                localStorage.setItem("picture", counter.picture);
                localStorage.setItem("roleId", counter.roleId);
                localStorage.setItem("token", counter.token);
            });
            if(this.responseText !== null){
                location.replace('Search-BE.html');
            }
        }
    };
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        alert("You have been signed out successfully");
        localStorage.clear();
        $(".g-signin2").css("display", "block");
        $(".data").css("display", "none");
    });
}