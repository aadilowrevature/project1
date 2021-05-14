
email = sessionStorage.getItem("storageEmail");
pwd = sessionStorage.getItem("storagePwd");

if(!email||!pwd){
    alert("Please Login")
    window.location.href ="../../home.html";
}
function logout(){
    sessionStorage.removeItem("storageEmail");
    sessionStorage.removeItem("storagePwd");
    
    email=null;
    pwd=null;
    window.location.href ="../../home.html";
}

function openAccount(){
    window.location.href ="../openAccount.html";
}

function withdraw(){
    window.location.href ="../withdraw.html";
}

function deposit(){
    window.location.href ="../deposit.html";
}

function sendMoney(){
    window.location.href ="../sendMoney.html";
}

function viewAccounts(){
    window.location.href ="../viewAccounts.html";
}
function viewTransactions(){
    window.location.href ="../viewTransactions.html";
}
