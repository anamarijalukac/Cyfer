import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../Cards.css';
import CardItem from '../CardItem';
import { useHistory } from "react-router-dom";


function Dogs(props) {

    const [dogs, setDogs] = React.useState([]);
    const [chooseDogs, setChooseDogs] = React.useState(false);
    let history = useHistory()
    let isLoggedInUser = localStorage.getItem("loggedInUser") !== null;
    const [needyDogs, setNeedyDogs] = React.useState([]);


    React.useEffect(() => {
        fetch('/dog')
            .then(data => data.json())
            .then(dogs => {
                setDogs(dogs)
            })
    }, []);

    React.useEffect(() => {
        fetch('/dog/statistics')
            .then(data => data.json())
            .then(data => {
                setNeedyDogs(data);
                console.log(needyDogs);
            })
    }, [needyDogs.length])

    const needyDogCards = needyDogs.map(dog =>
        <div>
            <CardItem
                key={dog.dogId}
                src={dog.image}
                text={dog.description}
                label={dog.name}
                path={'/dog/' + dog.dogId}
            />
        </div>
    )

    const dogCards = dogs.map(dog =>
        <div>
            <CardItem
                key={dog.dogId}
                src={dog.image}
                text={dog.description}
                label={dog.name}
                path={'/dog/' + dog.dogId}
            />
            {chooseDogs &&
            <div className='cards__item__text' style={{textAlign: 'center', height: '50px', margin: '15px'}}>
                <span>
                    <span>Odaberi psa za šetnju   </span>
                <input type='checkbox' name='walk' id={dog.dogId}></input>
                </span>
            </div>
            }
        </div>
    )


    function multiDogs() {
        setChooseDogs(true)
    }

    function vrati() {
        setChooseDogs(false)
    }

    function rezerviraj() {
        let dogsToWalk = dogs.filter(dog => document.getElementById(dog.dogId).checked).map(dog=> 'dog='+dog.dogId).join("&")
        history.push('/multipleDogReservation/'+dogsToWalk)

    }


    return (
        <div className='cards'>
            <h1 className='udruge'>Odaberi psa za šetnju:</h1>
            {!chooseDogs && isLoggedInUser &&
            <div className='button-container'>
                <button className='multiplebtn' onClick={multiDogs}>Odaberi više pasa za grupnu šetnju</button>
            </div>
            }
            {chooseDogs &&
            <div className='button-container'>
                <button className='multiplebtn' style={{marginRight:'20px'}} onClick={rezerviraj}>Rezerviraj</button>
                <button className='multiplebtn' style={{marginLeft:'20px'}} onClick={vrati}>Odustani</button>
            </div>
            }

            <h2 className='udruge'>Psi kojima je najpotrebnija šetnja: </h2>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <div className='grid-container'>
                        {needyDogCards}
                    </div>
                </div>
            </div>

            <h2 className='udruge'>Svi psi: </h2>
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
