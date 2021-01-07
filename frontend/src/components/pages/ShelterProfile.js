import React from 'react';
import '../../components/pages/profile.css';
import {useHistory} from 'react-router-dom';


function ShelterProfile(props) {

    const [shelter, setShelter] = React.useState([]);
    const [location, setLocation] = React.useState("");

    let history = useHistory();

    function onClick() {
        history.push('/shelter/'+props.shelterId+'/dogs');
    }


    React.useEffect(() => {
        fetch('/shelter/info/' + props.shelterId)
            .then(data => data.json())
            .then(shelter => {
                setShelter(shelter)
                setLocation(shelter.location)
                console.log(shelter)
            })
    }, []);



    return (
        <div class="profile">
            <h1>Profil udruge: {shelter.name}</h1>


            <div className="container">
                <div>
                    <img src={shelter.image !== "" ? shelter.image : "https://pngimg.com/uploads/dog/dog_PNG50375.png"} alt="" style={{width: '400 px'}} title=""
                         className="img-circle img-thumbnail isTooltip"/>
                </div>
                <p className="fontstyle">Lokacija udruge: {shelter.address + ", " + shelter.city} </p>
                <p className="fontstyle">OIB: {shelter.oib} </p>


                <button className="loginbtn fontstyle" onClick={onClick}>
                    Lista pasa udruge
                </button>

            </div>
        </div>
    )
}


export default ShelterProfile;