import React from 'react';
import '../App.css';
import { Button } from './Button';
import './HeroSection.css';
import { Link } from 'react-router-dom';


function HeroSection() {
    const loggedIn = localStorage.getItem("loggedIn") === true
  return (
    <div className='hero-container'>
        {<h1>ŠETANJE PASA</h1>}
        {loggedIn === true && <p>Što čekaš? Prijavi se!</p>}
        {loggedIn === true &&
        <div className='hero-btns'>
            <Link to='/log-in' className='btn-mobile'>
                <Button
                    className='btns'
                    buttonStyle='btn--outline'
                    buttonSize='btn--large'
                >
                    Prijava
                </Button>
            </Link>
            <Link to='/sign-up' className='btn-mobile'>
                <Button
                    className='btns'
                    buttonStyle='btn--primary'
                    buttonSize='btn--large'
                >
                    Registracija
                </Button>
            </Link>
        </div>
        }
    </div>
  );
}

export default HeroSection;
