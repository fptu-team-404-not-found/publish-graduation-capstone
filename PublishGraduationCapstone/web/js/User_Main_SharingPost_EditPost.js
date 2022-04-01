CKEDITOR.replace('user-main-post-sharing-edit-post-editor', {
    extraPlugins: 'justify',
});
CKEDITOR.on('instanceLoaded', function (e) {
    e.editor.resize(1300, 720)
});

var usermail = localStorage.getItem("email");
var memberId = null;
var supervisorId = null;

function getId(){
    
}

var projectIdUser = sessionStorage.getItem('projectIdUser');

function createSharePost(){
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/share/addSharePost";
    xhttp.setRequestHeader("Content-Type", "application/json")
    xhttp.open("POST", api);
    var title = document.getElementById("user-main-sharing-edit-post-title").value;
    var details = document.getElementById("user-main-post-sharing-edit-post-editor").value;
    
    var object = 
    {"title": title,
    "details": details ,
    "note":null,
    "student":{
        "memberId":"SE111111"
    },
    "supervisor":{
        "supervisorId":null
    },"project":{
        "projectId": projectIdUser
    }
    }

    jsonData = JSON.stringify(object);

    xhttp.send(jsonData);


}