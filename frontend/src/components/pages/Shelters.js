import React from 'react';
import '../Cards.css';
import CardItem from '../CardItem';


function Shelters() {

    const [shelters, setShelters] = React.useState([]);
    const [images, setImages] = React.useState([]);


    React.useEffect(() => {
            fetch('/api/shelter')
                .then(data => data.json())
                .then(shelters => {
                        console.log(shelters)
                        setShelters(shelters)
                    }
                )
    }, [])

    /*
    React.useEffect(() => {
        fetch('/dog')
            .then(data => data.json()).then(dogs => {
            fetch('/shelter')
                .then(data => data.json())
                .then(shelters => {
                    debugger
                        shelters = shelters.map(s => {
                            s.image = dogs.filter(d => d.shelter.shelterId === s.shelterId)[0].image
                            return s
                        })
                    console.log(shelters)
                    setShelters(shelters)
                    }
                )
        })
    }, [])

     */


    const shelterCards = shelters.map(shelter => {
            console.log(shelter)


            return <CardItem
                key={shelter.shelterId}
                src={shelter.image !== "" ? shelter.image : "https://pngimg.com/uploads/dog/dog_PNG50375.png"}
                text={shelter.city}
                label={shelter.name}
                path={'/shelter/info/' + shelter.shelterId}
            />
        }
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
