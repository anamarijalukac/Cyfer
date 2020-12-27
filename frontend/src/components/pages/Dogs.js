import React from 'react';
import './Cards.css';
import CardItem from '../CardItem';


function Cards() {

    const options = {
        method: 'GET',
        headers: {
            'Content-Type' : 'application/json'
        }
    };


    fetch('/dogs', options)
        .then(response => {
            if(response.ok){

            }
            else{

            }
        }).catch(error => console.log(error));

    return (
        <div className='cards'>
            <h1 className='udruge'>Udruge uključene u projekt:</h1>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 1'
                            label='pas 1'
                            path='/info'
                        />
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 2'
                            label='pas 2'
                            path='/info'
                        />
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 3'
                            label='pas 3'
                            path='/info'
                        />
                    </ul>
                    <ul className='cards__items'>
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 4'
                            label='pas 4'
                            path='/info'
                        />
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 5'
                            label='pas 5'
                            path='/info'
                        />
                        <CardItem
                            src='images/pes.jpg'
                            text='Udruga 6'
                            label='pas 6'
                            path='/info'
                        />
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Cards;
