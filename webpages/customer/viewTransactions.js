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


function viewTransactions(){
    let url = "http://localhost:9000/transactions/"+email;
   fetch(url)
       .then(res => res.json())
       .then(res1 => {
           let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>"+
           "<tr><th>Account Type</th>"+
           "<th>Account Number</th>"+
           "<th>Customer ID</th>"+
           "<th>Timestamp</th>"+
           "<th>Transaction Number</th>"+
           "<th>Transaction Type</th>"+
           "<th>Amount</th></tr></thead>";

           res1.forEach(element => {
               
               data = data + "<tr><td>" + element.account_type + "</td>"
               data = data + "<td>" + element.acc_num + "</td>"
               data = data + "<td>" + element.customer_id + "</td>"
               data = data + "<td>" + element.timestamp+ "</td>"
               data = data + "<td>" + element.transaction_num + "</td>"
               data = data + "<td>" + element.transaction_type + "</td>"
               data = data + "<td>" + parseFloat(element.amount).toFixed(2) + "</td></tr>"
               
               
           });
           data = data + "</table>"
       
           document.getElementById("results").innerHTML = data; 
       });
}