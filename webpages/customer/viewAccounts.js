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
}function back() {
    window.location.href = "dashboard/dashboard.html";
}

function validator(){


}

function getAccounts(clicked_id){
    acctype = document.getElementById(clicked_id).value;

   
   let url = "http://localhost:9000/accounts/"+acctype+"/"+email;
   fetch(url)
       .then(res => res.json())
       .then(res1 => {
           let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>"+
           "<tr><th>Account Type</th>"+
           "<th>Account Number</th>"+
           "<th>Account Balance</th>"+
           "<th>Account Status</th></tr></thead>";
           res1.forEach(element => {
               
               data = data + "<tr><td>" + acctype + "</td>"
               data = data + "<td>" + element.acc_num + "</td>"
               data = data + "<td>" + parseFloat(element.balance).toFixed(2) + "</td>"
               data = data + "<td>" + element.account_status + "</td></tr>"
               
           });
           data = data + "</table>"
       
           document.getElementById("results").innerHTML = data; 
       });
}