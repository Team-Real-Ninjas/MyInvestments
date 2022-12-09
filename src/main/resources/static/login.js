// Import the functions you need from the SDKs you need

import { initializeApp } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-app.js";
import { getAuth, signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/9.4.1/firebase-auth.js";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries
// Your web app's Firebase configuration
$(document).ready(function() {

    //TODO: Place your Firebase Config variable here


    // Web app's Firebase configuration
    const firebaseConfig = {
        apiKey: "AIzaSyCMgW7ePWNAd9jJXnXgAZFsYb7xpk52phw",
        authDomain: "mysmartinvestment.firebaseapp.com",
        projectId: "mysmartinvestment",
        storageBucket: "mysmartinvestment.appspot.com",
        messagingSenderId: "341448302091",
        appId: "1:341448302091:web:157e53ebea90507b9e310e"
    };

    // Initialize Firebase
    const app = initializeApp(firebaseConfig);

    const auth = getAuth();
    $("#login").click((e) => {
        e.preventDefault();
        $("#responseBanner").html("").addClass("visually-hidden");

        signInWithEmailAndPassword(auth, $("#emailAddress").val(), $("#password").val())
            .then(async (cred)=>{
                let user = cred.user;
                let res = await user.getIdTokenResult(false);
                let token = res.token;
                localStorage.setItem("idToken", token);
                let headers = {"Authorization": "Bearer " + token}

                //If the login works it will the take these actions
                $.ajax({
                    headers: headers,
                    url:"http://localhost:8080/session",
                    method: "GET",
                    context: document.body
                }).done(()=>{
                    location.replace("http://localhost:8080/Home");//Home Page
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