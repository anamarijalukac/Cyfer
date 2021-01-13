import React from 'react';
import '../../components/pages/profile.css';
import {useHistory} from 'react-router-dom';


function EditProfile(props) {

    let history = useHistory();

    var input = ""
    var data;
    var lokacija
    let isShelter = localStorage.getItem("loggedInShelter") === "true"
    //shelter
    if (isShelter) {
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

        var body
        if (isShelter)
            body = {
                'name': document.getElementById("name").value,
                'username': document.getElementById("username").value,
                'image': document.getElementById("image").value,
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


        console.log(body)
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
            body: JSON.stringify(body)
        };

        let path = '/api/'+(isShelter?'shelter':'walker')+'/update/'+props.id
        debugger

        fetch(path, options)
            .then(response => {
                if (response.ok) {
                    alert("Promjene uspješno pohranjene.")
                    localStorage.setItem("password", body.password)
                    //history.push('/profile');
                    return response.json()
                }
                else{
                    history.push('/profile');
                }
            })
            .then(data => {
                debugger
                if (isShelter) {
                    localStorage.setItem("udruga", JSON.stringify(data))
                    localStorage.setItem("lokacija", JSON.stringify(data.location))
                    debugger
                    props.onLoginShelter()
                } else {
                    data.username = body.username;
                    localStorage.setItem("korisnik", JSON.stringify(data))
                    props.onLoginUser()
                }
                history.push('/profile');
            })
            .catch(error => {
                console.log(error)
                alert("Greška pri pohranjivanju promjena.")
            });
    }


        //korisnik
        if (!isShelter)
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
                        <input type="password" name="password" id="password" className="form-control"
                               required="required"
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


                    <p className="fontstyle">URL slike:</p>
                    <input type="text" name="image" id="image" className="form-control"
                           defaultValue={data.image}/>


                    <p className="fontstyle">Lozinka:</p>
                    <input type="password" name="password" id="password" className="form-control" required="required"
                           defaultValue={localStorage.getItem("password")}/>


                    <p className="fontstyle">Adresa:</p>
                    <input type="text" name="address" id="address" className="form-control" required="required"
                           defaultValue={data.address}/>

                    <p className="fontstyle">Grad:</p>
                    <input type="text" name="city" id="city" className="form-control" required="required"
                           defaultValue={data.city}/>




                    <button className="loginbtn fontstyle" onClick={onClick}>
                        Pohrani promjene
                    </button>
                </div>
            </div>
        );
    }


    export default EditProfile;