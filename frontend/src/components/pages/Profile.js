import React from 'react';
import '../../components/pages/profile.css';
import { useHistory, useState } from 'react-router-dom';
import DemoApp from '../../components/pages/Calendar.js';

function Profile(props) {


    let history = useHistory();
    var inputDelete;
    var inputUpdate;
    var data;
    var lokacija;
    let inputStatistics;
    let isShelter = localStorage.getItem("loggedInShelter") === "true"
    debugger



    if (isShelter) {
        data = JSON.parse(localStorage.getItem("udruga"))
        lokacija = JSON.parse(localStorage.getItem("lokacija"));
        inputDelete = 'shelter/delete/' + data.shelterId
        inputUpdate = 'shelter/update/' + data.shelterId
    }
    else {
        data = JSON.parse(localStorage.getItem("korisnik"))
        inputDelete = 'walker/delete/' + data.walkerId
        inputUpdate = 'walker/update/' + data.walkerId
        inputStatistics = '/walker/' + data.walkerId + '/stats/';
    }


    function onClickDelete() {

        // eslint-disable-next-line no-restricted-globals
        if (!confirm("Jeste li sigurni da želite obrisati profil?"))
            return

        var auth = 'Basic ' + new Buffer(data.username + ':' + localStorage.getItem("password")).toString('base64');

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
        };
        fetch(inputDelete, options)
            .then(response => {
                if (response.ok) {
                    debugger
                    history.push('/');
                    props.onLogout();
                }
            })
            .catch(error => {
                console.log(error)
                alert("Neuspješno brisanje profila.")
            });
    }

    function onClickCalendar() {
        history.push('/walker/'+data.walkerId+'/calendar')
    }


    function onClickUpdate() {
        history.push(inputUpdate)
    }



    let auth = 'Basic ' + new Buffer(data.username + ':' + localStorage.getItem("password")).toString('base64');

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
    };

    const [walksDuration, setWalksDuration] = React.useState(0);

    const [numberOfDogsWalked, setNumberOfDogsWalked] = React.useState(0);

    const [numberOfWalks, setNumberOfWalks] = React.useState(0);

    if (!isShelter) {

        fetch(inputStatistics + '1', options)
            .then(data => {
                return data.json();
            })
            .then(data => {
                setWalksDuration(data);
            })
            .catch(error => console.log(error))

        fetch(inputStatistics + '3', options)
            .then(data => {
                return data.json();
            })
            .then(data => {
                setNumberOfDogsWalked(data);
            })
            .catch(error => console.log(error))

        fetch(inputStatistics + '2', options)
            .then(data => {
                return data.json();
            })
            .then(data => {
                setNumberOfWalks(data);
            })
            .catch(error => console.log(error))

    }


    if (isShelter) {
        //udruga
        return (
            <div className="profile">

                <div className="container">
                    <div>
                        <img className="image1" src={data.image !== "" ? data.image : "https://pngimg.com/uploads/dog/dog_PNG50375.png"} />
                    </div>
                    <h1>Profil udruge: {data.username}</h1>
                    <p className="fontstyle">Korisničko ime udruge: {data.username} </p>

                    <p className="fontstyle">Ime: {data.name} </p>
                    <p className="fontstyle">OIB: {data.oib} </p>
                    <p className="fontstyle">Adresa: {data.address} </p>
                    <p className="fontstyle">Grad: {data.city} </p>


                    <button className="loginbtn fontstyle" onClick={onClickUpdate}>
                        Uredi profil
                    </button>
                    <button className="loginbtn fontstyle" onClick={onClickDelete}>
                        Izbriši profil
                    </button>


                </div>
            </div>
        )
    }

    //korisnik
    return (
        <div>
            <div className="profile">
                <h1>Profil korisnika: {data.username}</h1>

                <div className="container">
                    <p className="fontstyle">Korisničko ime: {data.username} </p>

                    <p className="fontstyle">Ime: {data.firstName} </p>
                    <p className="fontstyle">Prezime: {data.lastName} </p>
                    <p className="fontstyle">E-mail: {data.email} </p>
                    <p className="fontstyle">Ukupna duljina šetnji: {walksDuration}</p>
                    <p className="fontstyle">Broj pasa koje sam šetao: {numberOfDogsWalked}</p>
                    <p className="fontstyle">Ukupan broj šetnji: {numberOfWalks}</p>


                    <button className="loginbtn fontstyle" onClick={onClickCalendar}>
                        Pregledaj kalendar šetnji
                    </button>
                    <button className="loginbtn fontstyle" onClick={onClickUpdate}>
                        Uredi profil
                    </button>
                    <button className="loginbtn fontstyle" onClick={onClickDelete}>
                        Izbriši profil
                </button>

                </div>
            </div>
        </div>
    );
}


export default Profile;