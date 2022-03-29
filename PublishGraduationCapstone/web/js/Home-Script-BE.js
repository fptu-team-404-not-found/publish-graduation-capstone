function writeComment() {
    document.forms['commentForm']['email'].value = "bebo@gmail.com"; //local storage email
    document.forms['commentForm']['projectId'].value = "SU20SE02"; //var projectId = sessionStorage.getItem('projectId');

    var txtCommentBox = document.getElementById("project-comment-text").value;
    console.log('commentContent: ' + txtCommentBox);

    // document.getElementById('commentForm').submit();
    /*
    var userMail = localStorage.getItem("email"); 
    if(userMail == null){
        alert("Please login before comment!")
    }
    */
    var form = document.getElementById("commentForm");
    var formData = new FormData(form);
    var api = "/PublishGraduationCapstone/api/project/commentOnProject";
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", api);
//    xhttp.setRequestHeader("Content-Type", "multipart/form-data");
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            console.log("rs: " + res);
            try {
                
            } catch (e) {
                alert(e);
            }
        };
    }
};