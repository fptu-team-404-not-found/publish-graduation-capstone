CKEDITOR.replace('editor', {
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
    xhttp.open("POST", api);
    xhttp.setRequestHeader("Content-Type", "application/json")
    var title = document.getElementById("user-main-sharing-edit-post-title").value;
    var details = CKEDITOR.instances.editor.getData();
    console.log("details: " + details)
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
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            var res = this.responseText;
            console.log("rs: " + res);
            try {
                if(1 == 1)
                {
                    alert(res);
                }
            } catch (e) {
                alert(e);
            }
        };
    };


}