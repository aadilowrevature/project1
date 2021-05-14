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
function validator(){}

function search(){
    id=document.getElementById("cid").value;
    acctype=document.getElementById("acc-type").value;

    let url = "http://localhost:9000/employee/search/" + id + "/" + acctype;
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            console.log(res1)
            if (res1.length == 0 || res1.message) {
                alert(res1.message)
                window.location.href = "viewAccounts.html";
                return false;
            }
            else {
            let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>"+
            "<tr><th>id</th>"+
            "<th>Account Type</th>"+
            "<th>Account Number</th>"+
            "<th>Account Balance</th>"+
            "<th>Account Status</th></tr></thead>";
            res1.forEach(element => {
                data = data + "<td>" + id + "</td>"
                data = data + "<td>" + acctype + "</td>"
                data = data + "<td>" + element.acc_num + "</td>"
                data = data + "<td>" + parseFloat(element.balance).toFixed(2) + "</td>"
                data = data + "<td>" + element.account_status + "</td></tr>"
                
            });
            data = data + "</table>"
        
            document.getElementById("results").innerHTML = data; 
        }
        });

}

