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

function viewPendingAccounts(clicked) {
    var account_type = clicked;
    let url = "http://localhost:9000/employee/work/" + account_type;
    
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            if(res1.length==0){
                alert("No Work Here!")
            }
            else{
            let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>" +
                "<tr><th></th>" +
                "<th></th>" +
                "<th>Account Type</th>" +
                "<th>Account Number</th>" +
                "<th>Customer ID</th>" +
                "<th>Balance</th></tr></thead>";

            res1.forEach(element => {
                data = data + "<tr><td>" + "<button class=" + "'btn btn-outline-light bg-dark'" + "type=" + "'submit'" + " onclick=" + "approveAccount(" + element.acc_num + "," + account_type + ")" + ">Approve</button>" + "</td>"
                data = data + "<td>" + "<button class=" + "'btn btn-outline-light bg-dark'" + "type=" + "'submit'" + " onclick=" + "denyAccount(" + element.acc_num + "," + account_type + ")" + ">Deny</button>" + "</td>"
                data = data + "<td>" + account_type + "</td>"
                data = data + "<td>" + element.acc_num + "</td>"
                data = data + "<td>" + element.customer_id + "</td>"
                data = data + "<td>" + element.balance + "</td></tr>"

            });
            data = data + "</table>"

            document.getElementById("results").innerHTML = data;
        }
        });

}

function approveAccount(acc_num, acc_type) {
    let url = "http://localhost:9000/employee/approve/"+email+"/" + acc_type.value + "/" + acc_num;
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            console.log(res1)
            if (!res1) {
                alert("Operation Failed: " + res1.message)
                return false;
            }
            else {

                viewPendingAccounts(acc_type.value);
                return true;
            }
        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) {
                alert("No Conncection")
                return false;
            }


        });
}

function denyAccount(acc_num, acc_type) {
    let url = "http://localhost:9000/employee/deny/"+email+"/" + acc_type.value + "/" + acc_num;


    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            if (!res1) {
                alert("Operation Failed: " + res1.message)
                return false;
            }
            else {

                viewPendingAccounts(acc_type.value);
                return true;
            }
        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) {
                alert("No Conncection")
                return false;
            }
        });
}
