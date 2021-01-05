import React from 'react';
import '../../components/pages/profile.css';
import {useHistory} from 'react-router-dom';


function EditProfile(props) {

    let history = useHistory();

    var input = ""
    var data;
    var lokacija
    //shelter
    if (localStorage.getItem("korisnik") === null) {
        data = JSON.parse(localStorage.getItem("udruga"));
        lokacija = JSON.parse(localStorage.getItem("lokacija"));
        input = 'shelter/update/' + JSON.parse(localStorage.getItem("udruga")).shelterId
    }
    //user
    else {
        data = JSON.parse(localStorage.getItem("korisnik"));
        input = 'walker/update/' + JSON.parse(localStorage.getItem("korisnik")).walkerId
    }


    function onClick() {

        var auth = 'Basic ' + new Buffer(data.username + ':' + localStorage.getItem("password")).toString('base64');

        console.log(input);
        console.log(data.username)
        console.log(localStorage.getItem("password"))

        var body
        if (localStorage.getItem("korisnik") === null)
            body = {
                'name': document.getElementById("name").value,
                'username': document.getElementById("username").value,
                'city': document.getElementById("city").value,
                'address': document.getElementById("address").value,
                'password': document.getElementById("password").value
            }
        else
            body = {
                'firstName': document.getElementById("firstName").value,
                'lastName': document.getElementById("lastName").value,
                'username': document.getElementById("username").value,
                'email': document.getElementById("email").value,
                'password': document.getElementById("password").value
            }


        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
            body: body
        };

        fetch(input, options)
            .then(response => {
                if (response.ok) {
                    localStorage.clear();
                    props.onLogout();
                    history.push('/');
                    console.log("Success");
                }
            })
            .catch(error => console.log(error));
    }


    //korisnik
    if ((localStorage.getItem("udruga") === null))
        return (
            <div className="profile">
                <h1>Profil korisnika: {data.username}</h1>


                <div className="container">

                    <p className="fontstyle">Ime:</p>
                    <input type="text" name="firstName" id="firstName" className="form-control" required="required"
                           defaultValue={data.firstName}/>

                    <p className="fontstyle">Prezime:</p>
                    <input type="text" name="lastName" id="lastName" className="form-control" required="required"
                           defaultValue={data.lastName}/>

                    <p className="fontstyle">Email adresa:</p>
                    <input type="text" name="email" id="email" className="form-control" required="required"
                           defaultValue={data.email}/>

                    <p className="fontstyle">Korisničko ime:</p>
                    <input type="text" name="username" id="username" className="form-control" required="required"
                           defaultValue={data.username}/>


                    <p className="fontstyle">Lozinka:</p>
                    <input type="password" name="password" id="password" className="form-control" required="required"
                           defaultValue={localStorage.getItem("password")}/>


                    <button className="loginbtn fontstyle" onClick={onClick}>
                        Pohrani promjene
                    </button>
                </div>
            </div>
        );

    //shelter
    return (
        <div className="profile">
            <h1>Profil udruge: {data.username}</h1>


            <div className="container">

                <p className="fontstyle">Ime:</p>
                <input type="text" name="name" id="name" className="form-control" required="required"
                       defaultValue={data.name}/>


                <p className="fontstyle">Korisničko ime:</p>
                <input type="text" name="username" id="username" className="form-control" required="required"
                       defaultValue={data.username}/>


                <p className="fontstyle">Lozinka:</p>
                <input type="password" name="password" id="password" className="form-control" required="required"
                       defaultValue={localStorage.getItem("password")}/>


                <p className="fontstyle">Adresa:</p>
                <input type="text" name="address" id="address" className="form-control" required="required"
                       defaultValue={lokacija.address}/>

                <p className="fontstyle">Grad:</p>
                <input type="text" name="city" id="city" className="form-control" required="required"
                       defaultValue={lokacija.city}/>


                <button className="loginbtn fontstyle" onClick={onClick}>
                    Pohrani promjene
                </button>
            </div>
        </div>
    );
}


export default EditProfile;