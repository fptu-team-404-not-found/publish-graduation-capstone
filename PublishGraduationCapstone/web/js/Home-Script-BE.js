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
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            console.log("rs: " + res);
            try {
                //Nếu có response trả về => cmt trúng vào từ bị ban => hiện thông báo hay gì đó
                //Nếu reponse là chuỗi rỗng => load lại trang cho nó hiện cmt mới nhất
            } catch (e) {
                alert(e);
            }
        };
    };
};