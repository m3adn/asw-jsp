/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$("#Logout").click(function () {
    $.ajax({
        url: 'http://localhost:8080/ASW/Servlet/Logout',
        type: 'PUT',
        success: function () {
            $(window).attr('location', 'http://localhost:8080/ASW/');
        },
        error: function () {
            $(window).attr('location', 'http://localhost:8080/ASW/');
        }
    });
});

$("#DeleteAccount").click(function () {
    $.ajax({
        url: 'http://localhost:8080/ASW/Servlet/Account',
        type: 'DELETE',
        success: function () {
            $(window).attr('location', 'http://localhost:8080/ASW/');
        },
        error: function () {
            $(window).attr('location', 'http://localhost:8080/ASW/');
        }
    });
});

$.ajax({
    url: 'http://localhost:8080/ASW/Servlet/Account',
    type: 'GET',
    success: function (result) {
        console.log("Account Info: " + JSON.stringify(result));
        $('#Welcome').html("Welcome " + result.Username + "!!");
        $('#Balance').html("Balance: " + result.Balance + " $");
    },
    error: function () {
        console.log("Account Info: Failed");
        $("#Welcome").html("Failed!!");
    }
});

$.ajax({
    url: 'http://localhost:8080/ASW/Servlet/Coins/Buy',
    type: 'GET',
    success: function (result) {
        console.log("Coins to Buy: " + JSON.stringify(result));
    },
    error: function () {
        console.log("Coins to Buy: Failed");
    }
});

$.ajax({
    url: 'http://localhost:8080/ASW/Servlet/Portfolio',
    type: 'GET',
    success: function (result) {
        console.log("Portfolio: " + JSON.stringify(result));
    },
    error: function () {
        console.log("Portfolio: Failed");
    }
});