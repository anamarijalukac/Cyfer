import React from "react";
import {useHistory} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Dog.css'

function Dog(props) {

    let history = useHistory();

    const [dog, setDog] = React.useState("");
    const [shelter, setShelter] = React.useState("");
    const [location, setLocation] = React.useState("");


    React.useEffect(() => {
        fetch('/dog/id/'+props.dogId)
            .then(data => data.json())
            .then(dog => {
                setDog(dog)
                setShelter(dog.shelter)
                setLocation(dog.shelter.location)
            })
    }, []);




    function rezervacija(){

        if(localStorage.getItem("loggedIn") === null){
            history.push('/log-in');
            alert("Morate biti ulogirani za rezervaciju šetnje");
        }
        else if(localStorage.getItem("loggedInUser") === null){
            alert("Morate biti ulogirani kao korisnik za rezervaciju šetnje");
            history.push('/');
        }
        else {
            history.push({
                pathname: "/DogReservation",
                state: {
                    dogId: dog.dogId,
                    shelterId: shelter.shelterId
                }
            });
        }
    }


    let isLoggedIn = true;

    if(localStorage.getItem("loggedInUser") === null){
        isLoggedIn = false;
    }


    let button =
        <button class='btndog' type="submit" onClick={rezervacija}>
            Rezerviraj šetnju!
        </button>



    console.log(dog)
    console.log(shelter)
    console.log(location)


    return (
        <div class="container bootstrap snippets bootdey">
        <div class="panel-body inf-content"  style = {{backgroundColor: 'white', margin: '30px'}}>
        <h2 class="text-dark" style = {{textAlign: 'center'}}>Informacije</h2>
            <div class="row">
                <div class="col-md-4">
                    <img  src ={dog.image} alt="" style={{width:'600 px' }}title="" class="img-circle img-thumbnail isTooltip"></img> 
                </div>
                <div class="col-md-6">
                    <div class="table-responsive">
                    <table class="table table-user-information">
                        <tbody>
                            <tr>    
                                <td class ="text-secondary">
                                        Ime                                                
                                </td>
                                <td >
                                {dog.name}     
                                </td>
                            </tr>
                            <tr>        
                                <td class ="text-secondary">
                                    Opis
                                </td>
                                <td>
                                {dog.description}     
                                </td>
                            </tr>
        
                            <tr>        
                                <td class ="text-secondary">
                                        Grad
                                </td>
                                <td class>
                                    {location.city} 
                                </td>
                            </tr>
        
        
                            <tr>        
                            <td class ="text-secondary">
                                        Udruga                                                
                                </td>
                                <td>
                                {shelter.name}
                                </td>
                            </tr>
                            <tr>        
                            <td class ="text-secondary">
                                        Mogućnost grupnih šetnji                                                
                                </td>
                                <td class>
                                {dog.typeOfWalk === "I" ? "DA" : "NE"} 
                                </td>
                            </tr>
                            <tr>        
                            <td class ="text-secondary">
                                        Opis                                                
                                </td>
                                <td classs>
                                    {dog.description}
                                </td>
                            </tr>
                            {isLoggedIn && button}
                                  
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
        </div>    
    );
}

export default Dog;
