// Import the functions you need from the SDKs you need

import { initializeApp } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-app.js";
import { getAuth, signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-auth.js";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
// Your web app's Firebase configuration
$(document).ready(function() {

    //TODO: Place your Firebase Config variable here

    function firebaseConfig() {

    }

    // Initialize Firebase
    const app = initializeApp(firebaseConfig);

    const auth = getAuth();
    $("#login").click((e) => {
        e.preventDefault();
        $("#responseBanner").html("").addClass("visually-hidden");

        signInWithEmailAndPassword(auth, $("#email").val(), $("#password").val())
            .then(async (cred)=>{
                let user = cred.user;
                let res = await user.getIdTokenResult(false);
                let token = res.token;
                localStorage.setItem("idToken", token);
                let headers = {"Authorization": "Bearer " + token}

                $.ajax({
                    headers: headers,
                    url:"http://localhost:8080/session",
                    method: "GET",
                    context: document.body
                }).done(()=>{
                    location.replace("http://localhost:8080/Home");
                })
            })
            .catch(function (error) {
                // Handle Errors here.
                let errorCode = error.code;
                let errorMessage = error.message;
                $("#responseBanner").html(errorMessage).removeClass("visually-hidden");
                console.log(error);
            });
    })


});