import React from "react";
import {useHistory} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Dog.css'

function AddDog(props) {

    let history = useHistory();

    const [dog, setDog] = React.useState("");
    const [shelter, setShelter] = React.useState("");
    const [location, setLocation] = React.useState("");
    const [individual, setIndividual] = React.useState("");
    let savedShelter = JSON.parse(localStorage.getItem("udruga"))


    React.useEffect(() => {

        var auth = 'Basic ' + new Buffer(savedShelter.username + ':' + localStorage.getItem("password")).toString('base64');
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
        };
        fetch('/shelter/' + props.shelterId, options)
            .then(data => data.json())
            .then(shelter => {
                setShelter(shelter)
                setLocation(shelter.location)
                setIndividual("I")
            })
    }, []);


    let isLoggedInUser = localStorage.getItem("loggedInUser") === "true";
    let isLoggedInShelter = localStorage.getItem("loggedInShelter") === "true";



    function pohraniPromjene() {

        var auth = 'Basic ' + new Buffer(shelter.username + ':' + localStorage.getItem("password")).toString('base64');


        let body = {
            'name': document.getElementById("name").value,
            'description': document.getElementById("description").value,
            'typeOfWalk': individual,
            'image': document.getElementById("image").value
        }

        debugger
        if(body.name === "" || body.description === "") {
            alert("Ime i opis psa ne smiju biti prazni.")
            return
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

        fetch('/shelter/' + shelter.shelterId + '/dog/add', options)
            .then(response => {
                if (response.ok) {
                    alert("Novi pas uspješno dodan.")
                    return response.json()
                }
            })
            .then(data => {
                debugger
                history.push('/dog/'+data.dogId)
                console.log('/dog/'+data.dogId)
            })
            .catch(error => {
                console.log(error)
                alert("Greška pri pohranjivanju promjena.")
            });

    }

    let pohrani =
        <button className='btndog' type="submit" onClick={pohraniPromjene}>
            Pohrani promjene
        </button>


    return (
        <div className="container bootstrap snippets bootdey">
            <div className="panel-body inf-content" style={{backgroundColor: 'white', margin: '30px', padding:'40px'}}>
                <h2 className="text-dark" style={{textAlign: 'center'}}>Informacije</h2>
                <div className="row">
                    <div className="col-md-4">
                        <img src={"https://cdn4.vectorstock.com/i/thumb-large/08/08/adult-coloring-bookpage-a-cute-dog-image-vector-23000808.jpg"} alt="" style={{width: '700 px', marginTop:'70px'}} title=""
                             className="img-circle img-thumbnail isTooltip"></img>
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
                                        <input type="text" name="name" id="name" className="form-control"
                                               required="required"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-secondary">
                                        Opis
                                    </td>
                                    <td>
                                        <input type="text" name="description" id="description" className="form-control"
                                               required="required"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td className="text-secondary">
                                        URL slike
                                    </td>
                                    <td>
                                        <input type="text" name="image" id="image" className="form-control"/>
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
                                    <td>
                                        <input type="radio" id="grupne" name="walk" value="G"
                                               checked={individual === "I" ? "" : "checked"}
                                               onChange={e => setIndividual(e.target.value)}/>
                                        <label htmlFor="grupne">DA</label>
                                        <input type="radio" id="individual" name="walk" value="I"
                                               checked={individual === "I" ? "checked" : ""}
                                               onChange={e => setIndividual(e.target.value)}/>
                                        <label htmlFor="individual">NE</label>
                                    </td>
                                </tr>
                                <tr>

                                </tr>
                                {pohrani}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddDog;
