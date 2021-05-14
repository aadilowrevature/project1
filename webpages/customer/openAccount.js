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
}

function back() {
    window.location.href = "dashboard/dashboard.html";
}

function validator() {
    var acctype = document.getElementById("acc-type").value;
    var balance = document.getElementById("bal").value;

    let url = "http://localhost:9000/customer/new-account/" + email + "/" + acctype + "/" + balance;
    console.log(url);
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            if (res1.length == 0) {
                alert("Account Creation Failed!")
                return false;
            }
            else {
                
                alert("Successful Account Creation")
                //window.location.href = "dashboard/dashboard.html";
                return true;
            }
        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) {
                alert("No Conncection")
                return false;
            }
        });

}

