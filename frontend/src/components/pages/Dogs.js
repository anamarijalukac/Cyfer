import React from 'react';
import '../Cards.css';
import CardItem from '../CardItem';


function Dogs() {

    const [dogs, setDogs] = React.useState([]);

    React.useEffect(() => {
        fetch('/dog')
            .then(data => data.json())
            .then(dogs => {
                setDogs(dogs)
                console.log(dogs)
            })
    }, []);

    //console.log(dogs)
    const dogCards =  dogs.map(dog =>
        <CardItem
            key={dog.dogId}
            src={dog.image}
            text={dog.description}
            label={dog.name}
            path={'/dog/'+dog.dogId}
        />
    )



    return (
        <div className='cards'>
            <h1 className='udruge'>Odaberi psa za šetnju:</h1>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <div className='grid-container'>
                        {dogCards}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dogs;
