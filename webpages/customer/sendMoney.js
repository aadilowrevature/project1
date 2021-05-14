
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
           "<tr><th></th>"+
           "<th>Account Type</th>"+
           "<th>Account Number</th>"+
           "<th>Account Balance</th>"+
           "<th>Account Status</th></tr></thead>";
           res1.forEach(element => {
               if(element.account_status=="Pending"){

               }
               else{
               data = data + "<tr><td>" + "<button class="+"'btn btn-outline-light bg-dark'" + "type="+"'submit'"+" onclick="+"transaction("+element.acc_num+","+parseFloat(element.balance).toFixed(2) +","+acctype+")"+ ">Send Money</button>" + "</td>"
               data = data + "<td>" + acctype + "</td>"
               data = data + "<td>" + element.acc_num + "</td>"
               data = data + "<td>" + parseFloat(element.balance).toFixed(2) + "</td>"
               data = data + "<td>" + element.account_status + "</td></tr>"
               }
           });
           data = data + "</table>"
       
           document.getElementById("results").innerHTML = data; 
       });
}

function transaction(acc_num,balance,acctype){
    var val= document.getElementById("bal").value;
    var amount=parseFloat(val).toFixed(2);
    target_acc_num=document.getElementById("acc_num_destination").value;
    target_acc_type=document.getElementById("acc-type-destination").value;

    if(!amount || amount<=0){
        alert("Please Enter a valid Amount!")
    } 
 
    if(balance<amount){
        alert("Insufficient Balance")
    }
    else{   
    let url = "http://localhost:9000/send-money/"+acctype.value+"/" +acc_num +"/"+balance + "/" +amount +"/" +target_acc_type +"/"+ target_acc_num;
    
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
    
            if (res1.length == 0 || res1.message) {
                alert("Transaction Failed: "+res1.message)
                return false;
            }
            else {
                
                getAccounts(acctype.value);
                return true;
            }
        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) {
                alert("No Conncection")
                return false;
            }
        });
     }
 }