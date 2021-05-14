function back() {
    window.location.href = "../../home.html";
}

function validator(){
    var firstname = document.getElementById("first-name").value;
    var lastname = document.getElementById("last-name").value;
    var name= firstname+" "+lastname;
    var email = document.getElementById("email").value;
    var password=document.getElementById("pwd").value;
    

    
    let url = "http://localhost:9000/register/employee/"+ name +"/"+ email +"/"+password ;
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            console.log(res1)
            if (res1.length == 0  || res1.message) {
                alert("Account Creation Failed!: " +res1.message)
                return false;
            }
            else {
                
                alert("Successful Account Creation: Please Login")
                window.location.href = "../login/login.html";
                return true;
            }
        }).catch(function (response) { //runs for any error but for now should only launch if no connection
            if (!response.ok) {
                alert("No Conncection")
                return false;
            }
        });
}