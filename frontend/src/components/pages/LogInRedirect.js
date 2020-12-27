import React from 'react';
import '../../components/pages/profile.css';
import {useHistory} from 'react-router-dom';


function LogInRedirect(props){

    let history = useHistory();



    function onClick(){
        localStorage.removeItem("user");
        props.onLogout();
        history.push('/');
    }

    if(localStorage.getItem("korisnik") === null){

        let shelterData = JSON.parse(localStorage.getItem("udruga"));
        
        return (
            <div class="profile">
            <h1>Profil udruge: {shelterData.username}</h1>
            

            <div className="container">
                <p className="fontstyle">Korisničko ime udruge: {shelterData.username} </p> 

                <p className="fontstyle">Ime: {shelterData.name} </p>
                <p className="fontstyle">OIB: {shelterData.oib} </p>


                <button className="loginbtn fontstyle" onClick={onClick}>
                    Izbriši profil
                </button>

            </div>
      </div>
        )
    }

    let data = JSON.parse(localStorage.getItem("korisnik"));


    return (
        <div class="profile">
            <h1>Profil korisnika: {data.username}</h1>
            

            <div className="container">
                <p className="fontstyle">Korisničko ime: {data.username} </p> 

                <p className="fontstyle">Ime: {data.firstName} </p>
                <p className="fontstyle">Prezime: {data.lastName} </p>
                <p className="fontstyle">E-mail: {data.email} </p>


                <button className="loginbtn fontstyle" onClick={onClick}>
                    Izbriši profil
                </button>

            </div>
      </div>
    );
}


export default LogInRedirect; 