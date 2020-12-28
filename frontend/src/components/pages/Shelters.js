import React from 'react';
import '../Cards.css';
import CardItem from '../CardItem';


function Shelters() {

    const [shelters, setShelters] = React.useState([]);

    React.useEffect(() => {
        fetch('/shelter')
            .then(data => data.json())
            .then(shelters => {
                setShelters(shelters)
                console.log(shelters)
            })
    }, []);

    //console.log(dogs)
    const shelterCards =  shelters.map(shelter =>
        <CardItem
            key={shelter.shelterId}
            src={"https://upload.wikimedia.org/wikipedia/commons/f/fe/American_Eskimo_Dog_1.jpg"}
            text={shelter.location.city}
            label={shelter.name}
            path={'/shelter/'+shelter.shelterId}
        />
    )



    return (
        <div className='cards'>
            <h1 className='udruge'>Odaberi udrugu:</h1>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <div className='grid-container'>
                        {shelterCards}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Shelters;
