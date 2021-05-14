email = sessionStorage.getItem("storageEmail");
pwd = sessionStorage.getItem("storagePwd");

if (!email || !pwd) {
    alert("Please Login")
    window.location.href = "../home.html";
}
function logout() {
    sessionStorage.removeItem("storageEmail");
    sessionStorage.removeItem("storagePwd");

    email = null;
    pwd = null;
    window.location.href = "../home.html";
} function back() {
    window.location.href = "dashboard/dashboard.html";
}

function viewLogs(){
    let url = "http://localhost:9000/employee/worklogs";
    
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            if(res1.length==0){
                alert("No Worklogs")
            }
            else{
            let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>" +
                "<tr><th>Employee Email</th>" +
                "<th>Employee Name</th>" +
                "<th>Employee ID</th>" +
                "<th>Account Number</th>" +
                "<th>Account Type</th>" +
                "<th>Account Status</th></tr></thead>";

            res1.forEach(element => {
                data = data + "<tr><td>" + element.email + "</td>"
                data = data + "<td>" + element.name + "</td>"
                data = data + "<td>" + element.employee_id + "</td>"
                data = data + "<td>" + element.acc_num + "</td>"
                data = data + "<td>" + element.account_type + "</td>"
                data = data + "<td>" + element.status + "</td></tr>"

            });
            data = data + "</table>"

            document.getElementById("results").innerHTML = data;
        }
        });


}