function sendUserData(jsonData) {
    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/changeAccountList";
    xhr.open("POST", api);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log("nanana : " + jsonData);
    xhr.send(jsonData);
}