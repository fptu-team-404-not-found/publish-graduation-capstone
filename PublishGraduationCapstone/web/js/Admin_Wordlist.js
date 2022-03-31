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
/*-------------------------------------------------------------*/
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];
function openInfor() {
    modal.style.display = "block";
    span.style.display = "block";
}
function exitInfo() {
    span.style.display = "none";
    modal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
/*-------------------------------------------------------------*/
function showComment(){
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/countCommentInDate";
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

            const listComment = document.querySelector('#admin-main-wordlist_right_infor_content_list_container');

            let comments = new Array();

            jsonData.countCommentInDate.forEach(counter => {
                var comment = `
                <div class="admin-main-wordlist_right_infor_content_list" onclick="showCommentByDate(this)">
                <p class="admin-main-wordlist_right_infor_content_list_date">${counter.commentDate}</p>
                <p class="admin-main-wordlist_right_infor_content_list_comment" ><i class="fa-solid fa-comment-dots"
                        id="admin-main-wordlist_right_infor_content_list_comment"></i>${counter.total}
                    Comments
                </p>
                </div>
                `
                comments.push(comment);
            });
            listComment.innerHTML = comments.join('');
        }
    };
}
showComment();

function showCommentByDate(p){
    var xhttp = new XMLHttpRequest();
    var api = "/PublishGraduationCapstone/api/admin/showCommentByDateWithParameter?commentDate=";
    var day = p.querySelector('.admin-main-wordlist_right_infor_content_list_date').innerText;
    console.log(day + "this is the day")
    var apiUrl = api + day;
    xhttp.open("GET", apiUrl);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var res = this.responseText;
            try {
                var jsonData = JSON.parse(res);
            } catch (e) {
                alert(e);
            }
            document.querySelector('#admin-main-wordlist_right_infor_content_list_container_modal').innerHTML = " ";
            const CommentModal = document.querySelector('#admin-main-wordlist_right_infor_content_list_container_modal');
            
            let comments = new Array();

            jsonData.showCommentByDateWithParameter.forEach(counter => {
                var comment = `
                <div class="admin-main-wordlist_right_infor_content_list_modal">
                <div class="upcoming-img-id" style="display: none">${counter.Id}</div>
                <p class="admin-main-wordlist_right_infor_content_list_post_modal">${counter.projectName}</p>
                <p class="admin-main-wordlist_right_infor_content_list_comment_modal" ><i
                        class="fa-solid fa-comment-dots"></i>${counter.commentContent}
                </p>
                <p class="admin-main-wordlist_right_infor_content_list_mail_modal">${counter.email}</p>
                </div>
                `
                comments.push(comment);
            });
            CommentModal.innerHTML = comments.join('');
        }
    };
    openInfor();
}
