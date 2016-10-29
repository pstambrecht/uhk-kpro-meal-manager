$(document).ready(function() {
   refreshUsers();
});


function refreshUsers() {
    $.ajax({
        url: "localhost:8080/api/users"
    }).then(function(data) {
    	for (var item in data) {
    	       $('.greeting-id').append(item.id);
    	       $('.greeting-content').append(item.name);
    	}
    });
}