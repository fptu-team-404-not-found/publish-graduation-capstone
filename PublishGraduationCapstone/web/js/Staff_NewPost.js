function getOptionValue(sel) {
    console.log('value: ' + sel.options[sel.selectedIndex].value);
}

function changeMemberOptionValue(sel) {
    var studentId = sel.options[sel.selectedIndex].value;
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/staff/loadTeamMemberInfo?studentId=";
    var apiSend = api + studentId;
    xhttp.open("GET", apiSend);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            var tr = sel.closest('tr');
            tr.cells[1].innerText = jsonData.name;
            tr.cells[2].innerText = jsonData.mail;
            tr.cells[3].innerText = jsonData.phone;
        }
    };
}

var supervisorArray = new Array;
function takeSupervisorData(callback) {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/staff/loadSupervisor";
    xhttp.open("GET", api);
    xhttp.send();
    var arraySupervisorData = new Array;
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            for (var i = 0; i < jsonData.length; i++) {
                var obj = {
                    'supervisorId': jsonData[i].supervisorId,
                    'supervisorName': jsonData[i].supervisorName
                };
                arraySupervisorData.push(obj);
            };
            if (callback) callback(arraySupervisorData);
        }
    };
}

var selectItem = document.querySelectorAll('.staff-edit-post')

takeSupervisorData(function (arraySupervisorData) {
    for (let j = 0; j < selectItem.length; j++) {
        supervisorArray = new Array;
        for (var k = 0; k < arraySupervisorData.length; k++) {
            var obj = {
                'supervisorId': arraySupervisorData[k].supervisorId,
                'supervisorName': arraySupervisorData[k].supervisorName
            };
            supervisorArray.push(obj);
        };
    
        for (var i = 0; i < supervisorArray.length; i++) {
            var newOption = document.createElement('option');
            var optionText = document.createTextNode(supervisorArray[i].supervisorName);
            newOption.appendChild(optionText);
            newOption.setAttribute('value', supervisorArray[i].supervisorName);
            selectItem[j].appendChild(newOption);
        }
    }
})

//-------------------------------
var studentIdArray = new Array;
function takeStudentIdData(callback) {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/staff/loadStudentId";
    xhttp.open("GET", api);
    xhttp.send();
    var arrayData = new Array;
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            for (var i = 0; i < jsonData.length; i++) {
                var obj = {
                    'studentId': jsonData[i].studentId
                };
                arrayData.push(obj);
            };
            if (callback) callback(arrayData);
        }
    };
}

var selectItems = document.querySelectorAll('.staff-edit-post-student')

takeStudentIdData(function (arrayData) {
    for (let j = 0; j < selectItems.length; j++) {
        studentIdArray = new Array;
        for (var k = 0; k < arrayData.length; k++) {
            var obj = {
                'studentId': arrayData[k].studentId
            };
            studentIdArray.push(obj);
        };

        for (var i = 0; i < studentIdArray.length; i++) {
            var newOption = document.createElement('option');
            var optionText = document.createTextNode(studentIdArray[i].studentId);
            newOption.appendChild(optionText);
            newOption.setAttribute('value', studentIdArray[i].studentId);
            selectItems[j].appendChild(newOption);
        }
    }

})


function sendProject() {
    var object = {
        'projectId': document.querySelector('#staff-edit-post-id').value,
        'projectName': document.querySelector('#staff-edit-post-title').value,
        'projectAva': document.querySelector('#staff-edit-post-ava').value,
        'videoUrl': document.querySelector('#staff-edit-post-intro-video').value,
        'introductionContent': document.querySelector('#staff-edit-post-intro-word').value,
        'details': "",
        'recap': "",
        'authorName': "",
        'semester': {
            'semesterName': document.querySelector('#staff-edit-post').value
        },
        'listMembers':
        [
            {'memberId': document.querySelector('#id1').value},
            {'memberId': document.querySelector('#id2').value},
            {'memberId': document.querySelector('#id3').value},
            {'memberId': document.querySelector('#id4').value},
            {'memberId': document.querySelector('#id5').value}
        ],
        'listImages':
        [
            {'imageUrl': document.querySelector('#staff-edit-post-intro-img-link-1').value},
            {'imageUrl': document.querySelector('#staff-edit-post-intro-img-link-2').value},
            {'imageUrl': document.querySelector('#staff-edit-post-intro-img-link-3').value},
        ]
    }

    var xhr = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/staff/insertProject";
    xhr.open("POST", api);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log("nanana : " + JSON.stringify(object));
    xhr.send(JSON.stringify(object));
}