function sendUserData(jsonData) {
    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/saveAccountList";
    xhr.open("POST", api, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(jsonData);
}