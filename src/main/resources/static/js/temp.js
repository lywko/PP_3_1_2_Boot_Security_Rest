fetch("http://localhost:8080/api/users").then(
    res => {
        res.json().then(
            data => {
                console.log(data);
                if (data.length > 0) {
                    var temp = "";
                    var rolesList = "";

                    data.forEach((u) => {
                        temp += "<tr>";
                        temp += "<td>" + u.id + "</td>";
                        temp += "<td>" + u.name + "</td>";
                        temp += "<td>" + u.lastName + "</td>";
                        temp += "<td>" + u.email + "</td>";
                        var rolesList = "";
                        u.roles.forEach((r) => {
                                rolesList += r.name.slice(5) + " ";
                            }
                        )
                        temp += "<td>" + rolesList + "</td></tr>";

                    })

                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    }
)