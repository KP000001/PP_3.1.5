let formNew = document.forms["formNewUser"];
addUser();

function addUser() {
    formNew.addEventListener("submit", ev => {
        ev.preventDefault();
        let newRoles = [];
        for (let i = 0; i < 2; i++) {
            if (formNew.roles.options[i].selected) newRoles.push({
                id: formNew.roles.options[i].value,
                name: "ROLE_" + formNew.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/api/admin/saveUser", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: null,
                firstName: formNew.firstName.value,
                lastName: formNew.lastName.value,
                age: formNew.age.value,
                username: formNew.username.value,
                password: formNew.password.value,
                roles: newRoles
            })
        }).then(() => {
            formNew.reset();
            allUsers();
            $('#tableTab').click();
        });
    });
}