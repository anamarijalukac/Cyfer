import React from "react";
import CardItem from "../CardItem";
import {useHistory} from 'react-router-dom';

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

        history.push({
            pathname: "/DogReservation",
            state: {
                dogId : dog.dogId,
                shelterId : shelter.shelterId
            }
        });
    }




    console.log(dog)
    console.log(shelter)
    console.log(location)


    return (
        <div className='cards'>
            <h1 className='udruge'>Ovo je {dog.name}</h1>
            <div className='cards__container'>

                    <img src={dog.image} alt="nema"/>
                    <div>Opis: {dog.description}</div>
                    <div>Grad: {location.city}</div>
                    <div>Udruga: {shelter.name}</div>
                    <div>Mogućnost grupnih šetnji: {dog.typeOfWalk === "I" ? "DA" : "NE"}</div>
                    <div>Opis: {dog.description}</div>
                <button onClick={rezervacija}>
                    Rezerviraj Šetnju!
                </button>

            </div>
        </div>
    );
}

export default Dog;
