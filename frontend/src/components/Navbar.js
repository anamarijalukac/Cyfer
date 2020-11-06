import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            ŠAPA
            <i class='fas fa-paw' />
          </Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Početna
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/RangList'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Rang lista
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/RegUdr'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Registriraj udrugu
              </Link>
            </li>
          </ul>
         <div class='navbar-buttons'>
          <Link to='/log-in' className='btn-mobile'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--small'
        >
          Log in
        </Button>
        </Link>

        <Link to='/sign-up' className='btn-mobile'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--small'
        >
          Sign up
        </Button>
        </Link>
        </div>
        </div>
      </nav>
    </>
  );
}

export default Navbar;