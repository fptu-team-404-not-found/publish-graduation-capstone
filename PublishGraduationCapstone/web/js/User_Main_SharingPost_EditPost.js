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
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/user/checkId?email=" + usermail;
    xhttp.open("GET", api);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            try {
                var jsonData = JSON.parse(res);
            } catch (e) {
                alert(e);
            }
            if(jsonData.checkId[0].supervisorId)
            supervisorId = jsonData.checkId[0].supervisorId
            else
            supervisorId = null;
            if(jsonData.checkId[0].memberId)
            memberId = jsonData.checkId[0].memberId
            else
            memberId = null
        }
    };
    
}
getId()

var projectIdUser = sessionStorage.getItem('projectIdUser');

function createSharePost(){
    console.log("supervisorId: "+ supervisorId);
    console.log("memberId: "+ memberId);
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
        "memberId":memberId
    },
    "supervisor":{
        "supervisorId": supervisorId
    },"project":{
        "projectId": projectIdUser
    }
    }

    jsonData = JSON.stringify(object);

    xhttp.send(jsonData);
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            try {
               alert("Success")
               location.reload();
            } catch (e) {
                alert(e);
            }
        };
    };


}