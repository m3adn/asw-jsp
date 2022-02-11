/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$.ajax({
    url: 'http://localhost:8080/ASW/Servlet/Account',
    type: 'GET',
    success: function (result) {
        console.log("Account Info: " + JSON.stringify(result));
        $('#Username').html(result.Username);
        $('#Email').html(result.Email);
        $('#PhoneNumber').html(result.PhoneNumber);
        $('#Balance').html(result.Balance + " $");
    },
    error: function () {
        console.log("Account Info: Failed");
    }
});