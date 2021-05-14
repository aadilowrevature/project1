function back() {
    window.location.href = "../../home.html";
}

function validator() {
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("pwd").value;


    const regex = /[^ ]{8,16}/;

    if (pwd.length > 0 && !(pwd.match(regex))) {
        alert("Password must be 8-16 characters");
        return false;
    }

    let url = "http://localhost:9000/employee/login/" + email + "/" + pwd;

    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            
            if (res1.length == 0) {
                alert("Incorrect Email or Password")
                
                
                return false;
            }
            else {
                sessionStorage.setItem("storageEmail",email);
                sessionStorage.setItem("storagePwd",pwd);
                window.location.href ="../dashboard/dashboard.html";
                return true;
            }

        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) { alert("No Conncection") 
            return false;
        }
        });

    return false;
}