import React from 'react';
import '../../components/pages/profile.css';


function LogInRedirect(){

    let data = JSON.parse(localStorage.getItem("user"));

    console.log(data);

    let beep = "korisnik";

    return (
        <div class="profile">
            <h1>Profil korisnika: {data.username}</h1>
            

            <div className="container">
                <p className="fontstyle">Korisničko ime: {data.username} </p> 

                <p className="fontstyle">Ime: {data.firstName} </p>
                <p className="fontstyle">Prezime: {data.lastName} </p>
                <p className="fontstyle">E-mail: {data.email} </p>

            </div>
      </div>
    );
}


export default LogInRedirect; 