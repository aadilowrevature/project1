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
function validator() { }

function viewAll(filter, filter_spec) {

    let url = "http://localhost:9000/employee/transactions/" + filter + "/" + filter_spec;
    fetch(url)
        .then(res => res.json())
        .then(res1 => {
            console.log(res1)
            if (res1.length == 0) {
                alert(res1.message)
                window.location.href = "viewTransactions.html";
                return false;
            }
            else {
                if (filter == 6) {
                    str = "<th>Name</th>";
                } else str = "";
                let data = "<table class='table table-bordered table-dark table-hover'><thead class='thead-dark'>" +
                    "<tr><th>Customer ID</th>" +
                    str +
                    "<th>Account Number</th>" +
                    "<th>Account Type</th>" +
                    "<th>Transaction Type</th>" +
                    "<th>Amount</th>" +
                    "<th>Transaction Number</th>" +
                    "<th>Timestamp</th></tr></thead>";
                res1.forEach(element => {
                    if (!element.account_type) { }
                    else {
                        data = data + "<td>" + element.customer_id + "</td>"
                        if (filter == 6) {
                            data = data + "<td>" + element.name + "</td>"
                        }
                        data = data + "<td>" + element.acc_num + "</td>"
                        data = data + "<td>" + element.account_type + "</td>"
                        data = data + "<td>" + element.transaction_type + "</td>"
                        data = data + "<td>" + parseFloat(element.amount).toFixed(2) + "</td>"
                        data = data + "<td>" + element.transaction_num + "</td>"

                        data = data + "<td>" + element.timestamp + "</td></tr>"

                    }
                });

                data = data + "</table>"

                document.getElementById("results").innerHTML = data;
            }

        });
}

function byDate(filter) {
    filter_spec = document.getElementById("filter-spec2").value;
    regex = /([0-9]{4}-[0-9]{2}-[0-9]{2})/;

    a = filter_spec.length
    b = regex.test(filter_spec);

    if (a > 10 || !b) {
        alert("Incorrect Format: Date Must be in YYYY-MM-DD")
        window.location.href = "viewTransactions.html";
    }
    else {

        viewAll(filter, filter_spec);

    }

}

function byType(filter) {

    var filter_spec = document.getElementById("filter-spec3").value;
    console.log(filter_spec)

    viewAll(filter, filter_spec);



}

function byCustomerID(filter) {
    filter_spec = document.getElementById("filter-spec4").value;
    regex = /([0-9]{0,9})/;

    a = filter_spec.length
    b = regex.test(filter_spec);

    if (a > 9 || !b) {
        alert("Incorrect Format")
        window.location.href = "viewTransactions.html";
    }
    else {

        viewAll(filter, filter_spec);

    }

}

function byAccountNumber(filter) {

    filter_spec = document.getElementById("filter-spec5").value;
    regex = /([0-9]{0,9})/;

    a = filter_spec.length
    b = regex.test(filter_spec);

    if (a > 9 || !b) {
        alert("Incorrect Format")
        window.location.href = "viewTransactions.html";
    }
    else {

        viewAll(filter, filter_spec);

    }

}

function byName(filter) {
    filter_spec = document.getElementById("filter-spec6").value;

    a = filter_spec.length

    if (a > 20) {
        alert("Incorrect Format: Name Too Long! Must be Less than 20 Characters ")
        window.location.href = "viewTransactions.html";
    }
    else {


        viewAll(filter, filter_spec);

    }

}