let formEdit = document.forms["editModal"];
editUser();

async function editModalData(id) {
    const modal = new bootstrap.Modal(document.querySelector('#modalEdit'));
    modal.show();
    let user = await getUser(id);
    formEdit.id.value = user.id;
    formEdit.firstName.value = user.firstName;
    formEdit.lastName.value = user.lastName;
    formEdit.age.value = user.age;
    formEdit.username.value = user.username;
    formEdit.password.value = user.password;
    formEdit.roles.value = user.roles[0].id;
}

async function getUser(id) {
    let url = "http://localhost:8080/api/admin/getUser/" + id;
    let response = await fetch(url);
    return await response.json();
}

function editUser() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let editRoles = [];
        for (let i = 0; i < 2; i++) {
            if (formEdit.roles.options[i].selected) editRoles.push({
                id: formEdit.roles.options[i].value,
                name: "ROLE_" + formEdit.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/api/admin/updateUser/" + formEdit.id.value, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                firstName: formEdit.firstName.value,
                lastName: formEdit.lastName.value,
                age: formEdit.age.value,
                username: formEdit.username.value,
                password: formEdit.password.value,
                roles: editRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            allUsers();
        });
    });
}