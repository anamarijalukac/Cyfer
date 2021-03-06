import React from "react";
import {useHistory} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Dog.css'

function EditDog(props) {

    let history = useHistory();

    const [dog, setDog] = React.useState("");
    const [shelter, setShelter] = React.useState("");
    const [location, setLocation] = React.useState("");
    const [individual, setIndividual] = React.useState("");


    React.useEffect(() => {
        fetch('/dog/id/' + props.dogId)
            .then(data => data.json())
            .then(dog => {
                setDog(dog)
                setShelter(dog.shelter)
                setLocation(dog.shelter.location)
                setIndividual(dog.typeOfWalk)
            })
    }, []);


    let isLoggedInUser = true;
    if (localStorage.getItem("loggedInUser") === null) {
        isLoggedInUser = false;
    }
    let isLoggedInShelter = true;
    if (localStorage.getItem("loggedInShelter") === null) {
        isLoggedInShelter = false;
    }


    function pohraniPromjene() {

        var auth = 'Basic ' + new Buffer(shelter.username + ':' + localStorage.getItem("password")).toString('base64');


        let body = {
            'name': document.getElementById("name").value,
            'description': document.getElementById("description").value,
            'typeOfWalk': individual,
            'image': document.getElementById("image").value
        }

        debugger


        console.log(body)
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
            body: JSON.stringify(body)
        };

        fetch('/shelter/' + shelter.shelterId + '/dogs/' + props.dogId + '/update', options)
            .then(response => {
                if (response.ok) {
                    alert("Promjene uspješno pohranjene.")
                    return response.json()
                }
            })
            .then(data => {
                debugger
                history.push('/dog/'+props.dogId)
                console.log('/dog/'+props.dogId)
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
            <div className="panel-body inf-content" style={{backgroundColor: 'white', margin: '30px'}}>
                <h2 className="text-dark" style={{textAlign: 'center'}}>Informacije</h2>
                <div className="row">
                    <div className="col-md-4">
                        <img src={dog.image} alt="" style={{width: '600 px'}} title=""
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
                                               required="required"
                                               defaultValue={dog.name}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-secondary">
                                        Opis
                                    </td>
                                    <td>
                                        <input type="text" name="description" id="description" className="form-control"
                                               required="required"
                                               defaultValue={dog.description}/>
                                    </td>
                                </tr>

                                <tr>
                                    <td className="text-secondary">
                                        URL slike
                                    </td>
                                    <td>
                                        <input type="text" name="image" id="image" className="form-control"
                                               defaultValue={dog.image}/>
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

export default EditDog;
