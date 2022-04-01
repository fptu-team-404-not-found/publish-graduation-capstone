function ShowLogout() {
    var logOut = document.getElementById('user-main-small-control');
    if (logOut.style.display == 'none') {
        logOut.style.display = 'block';
    } else
        logOut.style.display = 'none';
}

function logout() {
    localStorage.clear();

}

function initLogin() {
    var username = localStorage.getItem("name")
    var picture = localStorage.getItem("picture")

    document.getElementById("admin-main-nav-login-img-container").innerHTML =
        `
        <img id="admin-main-nav-login-img" src="${picture}" alt="">
        `
    document.getElementById("admin-main-nav-login").innerHTML = username
    document.getElementById("user-main-small-name").innerHTML = username
}
initLogin();

var userData = new Array;

function takeUserData(callback) {
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/showSupervisorList";
    xhttp.open("GET", api);
    xhttp.send();
    var table = new Array;
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            var jsonData = JSON.parse(res);
            for (var i = 0; i < jsonData.showSupervisorList.length; i++) {
                var obj = {
                'supervisorId': jsonData.showSupervisorList[i].supervisorId,
                'supervisorName': jsonData.showSupervisorList[i].supervisorName,
                'status': jsonData.showSupervisorList[i].status};
                table.push(obj);
            };
            console.log("Table : ");
            console.log(table);
			if (callback) callback(table);
        }
    };
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}
    
takeUserData(function(table) {
    for (var i = 0; i < table.length; i++) {
        var obj = {
            'supervisorId': table[i].supervisorId,
            'supervisorName': table[i].supervisorName,
            'status': table[i].status};
        userData.push(obj);
    };

    var state = { 
        'querySet': userData,
        
        'page': 1,
        'rows': 5,
        'window': 5,
    }
    
    var searchInput = document.querySelector('#admin-main-account-list-search');
    console.log('search input: ' + searchInput);
    searchInput.onchange = function (e) {
      searchInputText = e.target.value;
      if (searchInputText.isEmpty) {
        $('#table-body').empty()
        state = { 
            'querySet': userData,
            
            'page': 1,
            'rows': 5,
            'window': 5,
        }
        buildTable()
      }
      else {
            $('#table-body').empty()
            var subTable = new Array;
            for (var i = 0; i < userData.length; i++) {
                if (userData[i].supervisorName.indexOf(searchInputText) !== -1) {
                    var obj2 = {
                        'supervisorId': userData[i].supervisorId,
                        'supervisorName': userData[i].supervisorName,
                        'status': userData[i].status
                    };
                    subTable.push(obj2);
                }
            }
        
            state = { 
                'querySet': subTable,
                
                'page': 1,
                'rows': 5,
                'window': 5,
            }
            buildTable()
        }

    };
    buildTable()

    function pagination(querySet, page, rows) {
    
        var trimStart = (page - 1) * rows
        var trimEnd = trimStart + rows
    
        var trimmedData = querySet.slice(trimStart, trimEnd)
        
        var pages = Math.round(querySet.length / rows);
        if (pages < querySet.length / rows)
            pages++;
        return {
            'querySet': trimmedData,
            'pages': pages,
        }
    }
    
    function pageButtons(pages) {
        var wrapper = document.getElementById('pagination-wrapper')
    
        wrapper.innerHTML = ``
    
        var maxLeft = (state.page - Math.floor(state.window / 2))
        var maxRight = (state.page + Math.floor(state.window / 2))
    
        if (maxLeft < 1) {
            maxLeft = 1
            maxRight = state.window
        }
    
        if (maxRight > pages) {
            maxLeft = pages - (state.window - 1)
            
            if (maxLeft < 1){
                maxLeft = 1
            }
            maxRight = pages
        }
    
        for (var page = maxLeft; page <= maxRight; page++) {
            wrapper.innerHTML += `<button value=${page} class="page btn btn-sm btn-info">${page}</button>`
        }
    
        if (state.page != 1) {
            wrapper.innerHTML = `<button value=${1} class="page btn btn-sm btn-info">&#171; First</button>` + wrapper.innerHTML
        }
    
        if (state.page != pages) {
            wrapper.innerHTML += `<button value=${pages} class="page btn btn-sm btn-info">Last &#187;</button>`
        }
    
        $('.page').on('click', function() {
            $('#table-body').empty()
    
            state.page = Number($(this).val())
    
            buildTable()
        })
    }
    
    
    function buildTable() {
        takeUserData();
        var table = $('#table-body')
        var data = pagination(state.querySet, state.page, state.rows)
        var myList = data.querySet
    
        for (var i = 1 in myList) {
            //Keep in mind we are using "Template Litterals to create rows"
            var row = `<tr class="admin-main-account-list-infors">
                      <td class="admin-main-account-list-table-id">${myList[i].supervisorId}</td>
                      <td class="admin-main-account-list-table-account">${myList[i].supervisorName}</td>
                      <td class="admin-main-account-list-table-user">${myList[i].status}</td>
                        <tr>
                      `
            table.append(row)
        }
    
        pageButtons(data.pages)
    }
});

