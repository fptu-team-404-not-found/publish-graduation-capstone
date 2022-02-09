console.log('Team 404');

function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
    var profile = googleUser.getBasicProfile();
    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
    console.log('Full Name: ' + profile.getName());
    console.log('Given Name: ' + profile.getGivenName());
    console.log('Family Name: ' + profile.getFamilyName());
    console.log("Image URL: " + profile.getImageUrl());
    console.log("Email: " + profile.getEmail());

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
            console.log('data: ' + this.responseText);
        }
    };
}
