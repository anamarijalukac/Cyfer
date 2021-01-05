import React from 'react';
import '../../components/pages/profile.css';
import {useHistory} from 'react-router-dom';


function Profile(props){

    let history = useHistory();
    var inputDelete;
    var inputUpdate;
    var data;
    var lokacija

    if(localStorage.getItem("korisnik") === null) {
        data = JSON.parse(localStorage.getItem("udruga"));
        lokacija = JSON.parse(localStorage.getItem("lokacija"));
        inputDelete = 'shelter/delete/' + data.shelterId
        inputUpdate = 'shelter/update/' + data.shelterId
    }
    else {
        data = JSON.parse(localStorage.getItem("korisnik"));
        inputDelete = 'walker/delete/' + data.walkerId
        inputUpdate = 'walker/update/' + data.walkerId
    }


    function onClickDelete(){

        var auth = 'Basic ' + new Buffer(data.username + ':' + localStorage.getItem("password")).toString('base64');

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
        };
        fetch(inputDelete, options)
            .then(response =>{
                if(response.ok){
                    localStorage.clear();
                    props.onLogout();
                    history.push('/');
                    console.log("Success");
                }
            })
            .catch(error => console.log(error));
    }


    function onClickUpdate() {
        history.push(inputUpdate)
    }

    if(localStorage.getItem("korisnik") === null){
        //udruga
        return (
            <div className="profile">
                <h1>Profil udruge: {data.username}</h1>

                <div className="container">
                    <p className="fontstyle">Korisničko ime udruge: {data.username} </p>

                    <p className="fontstyle">Ime: {data.name} </p>
                    <p className="fontstyle">OIB: {data.oib} </p>
                    <p className="fontstyle">Adresa: {lokacija.address} </p>
                    <p className="fontstyle">Grad: {lokacija.city} </p>


                    <button className="loginbtn fontstyle" onClick={onClickDelete}>
                        Izbriši profil
                    </button>
                    <button className="loginbtn fontstyle" onClick={onClickUpdate}>
                        Uredi profil
                    </button>

                </div>
            </div>
        )
    }

    //korisnik
    return (
        <div className="profile">
            <h1>Profil korisnika: {data.username}</h1>

            <div className="container">
                <p className="fontstyle">Korisničko ime: {data.username} </p>

                <p className="fontstyle">Ime: {data.firstName} </p>
                <p className="fontstyle">Prezime: {data.lastName} </p>
                <p className="fontstyle">E-mail: {data.email} </p>


                <button className="loginbtn fontstyle" onClick={onClickDelete}>
                    Izbriši profil
                </button>
                <button className="loginbtn fontstyle" onClick={onClickUpdate}>
                    Uredi profil
                </button>
            </div>
        </div>
    );
}


export default Profile;