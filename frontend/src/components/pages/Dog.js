import React from "react";
import {useHistory, Link} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Dog.css'

function Dog(props) {

    let history = useHistory();

    const [dog, setDog] = React.useState("");
    const [shelter, setShelter] = React.useState("");
    const [location, setLocation] = React.useState("");


    React.useEffect(() => {
        fetch('/api/dog/id/' + props.dogId)
            .then(data => data.json())
            .then(dog => {
                setDog(dog)
                setShelter(dog.shelter)
                setLocation(dog.shelter.location)
            })
    }, []);


    let isLoggedInUser = localStorage.getItem("loggedInUser") === "true";
    let isLoggedInShelter = localStorage.getItem("loggedInShelter") === "true"
    let isLoggedIn = localStorage.getItem("loggedIn") === "true"

    var udruga
    if (localStorage.getItem("loggedInShelter") === "true")
        udruga = JSON.parse(localStorage.getItem("udruga"))


    function rezervacija() {

        if (localStorage.getItem("loggedIn") === null) {
            history.push('/log-in');
            alert("Morate biti ulogirani za rezervaciju šetnje");
        } else if (localStorage.getItem("loggedInUser") === null) {
            alert("Morate biti ulogirani kao korisnik za rezervaciju šetnje");
            history.push('/');
        } else {
            history.push({
                pathname: "/DogReservation",
                state: {
                    dogId: dog.dogId,
                    shelterId: shelter.shelterId
                }
            });
        }
    }


    function brisanje() {
        // eslint-disable-next-line no-restricted-globals
        if (!confirm("Jeste li sigurni da želite obrisati profil psa?"))
            return

        var auth = 'Basic ' + new Buffer(udruga.username + ':' + localStorage.getItem("password")).toString('base64');

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
        };
        fetch('/api/shelter/' + udruga.shelterId + '/' + dog.dogId + '/delete', options)
            .then(response => {
                if (response.ok) {
                    history.push('/shelter/' + udruga.shelterId + '/dogs')
                }
            })
            .catch(error => {
                console.log(error)
                alert("Neuspješno brisanje profila psa.")
            });
    }


    let rezerviraj =
        <button className='btndog btndogdouble' type="submit" onClick={rezervacija}>
            Rezerviraj šetnju!
        </button>

    let uredi =
        <Link to={'/dog/edit/' + props.dogId}>
            <button className='btndog' type="submit">
                Uredi podatke
            </button>
        </Link>

    let obrisi =
        <button className='btndog' type="submit" onClick={brisanje}>
            Obriši profil psa
        </button>



    return (
        <div className="container bootstrap snippets bootdey">
            <div className="panel-body inf-content" style={{backgroundColor: 'white', margin: '30px', padding:'30px'}}>
                <h2 className="text-dark" style={{textAlign: 'center'}}>Informacije</h2>
                <div className="row">
                    <div className="col-md-4">
                        <img src={dog.image} alt="" style={{width: '600 px'}} title=""
                             className="img-circle img-thumbnail isTooltip"/>
                    </div>
                    <div className="col-md-6">
                        <div className="table-responsive">
                            <table className="table table-user-information">
                                <tbody>
                                <tr>
                                    <td className="text-secondary">
                                        Ime
                                    </td>
                                    <td>
                                        {dog.name}
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-secondary">
                                        Opis
                                    </td>
                                    <td>
                                        {dog.description}
                                    </td>
                                </tr>

                                <tr>
                                    <td className="text-secondary">
                                        Grad
                                    </td>
                                    <td>
                                        {location.city}
                                    </td>
                                </tr>


                                <tr>
                                    <td className="text-secondary">
                                        Udruga
                                    </td>
                                    <td>
                                        {shelter.name}
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-secondary">
                                        Mogućnost grupnih šetnji
                                    </td>
                                    <td className="text-secondary">
                                        {dog.typeOfWalk === "I" ? "NE" : "DA"}
                                    </td>
                                </tr>
                                <tr>
                                    {isLoggedInUser &&
                                    <td>
                                        {rezerviraj}
                                    </td>}
                                    {isLoggedInShelter && shelter.shelterId === udruga.shelterId &&
                                    <td>
                                        {uredi}
                                    </td>}
                                    {isLoggedInShelter && shelter.shelterId === udruga.shelterId &&
                                    <td>
                                        {obrisi}
                                    </td>
                                    }
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div>
                    {!isLoggedIn &&
                    <h5 className="text-dark" style={{textAlign: 'center', marginTop: '30px'}}>Morate biti prijavljeni da biste rezervirali
                        šetnju.</h5>}
                </div>
            </div>
        </div>
    );
}

export default Dog;
