//show a confirmation and redirect to the delete profile script
function deleteConfirm() {
    return confirm('You sure you want to delete this record from database?');
}

function setAssignActive(obj) {
    console.log(obj.options[obj.selectedIndex].value);
    document.getElementById("assign").disabled = obj.options[obj.selectedIndex].value == 0;
}