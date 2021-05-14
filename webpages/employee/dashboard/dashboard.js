
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

function approveAccounts(){
    window.location.href ="../approveAccounts.html";
}

function viewAccounts(){
    window.location.href ="../viewAccounts.html";
}
function viewTransactions(){
    window.location.href ="../viewTransactions.html";
}

function viewLogs(){
    window.location.href ="../viewLogs.html";
}
