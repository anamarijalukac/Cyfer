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

  let item = localStorage.getItem("loggedIn");
  console.log("item is " + item);

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
              <Link
                to='/RangList'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Rang lista
              </Link>
            </li>

            {/*}
            <li className='nav-item'>
              <Link
                to='/log-in'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Prijava
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/sign-up'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Registriracija
              </Link>
            </li>
            <li className='nav-item'>
              <Link to='/LogInRedirect' className='nav-links' onClick={closeMobileMenu}>
                test
              </Link>
            </li>
  */}
          </ul>
  
        <div class='navbar-buttons'>
         <Link to='/sign-up' className='btn-mobile'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--small'
        >
          Registracija
        </Button>
        </Link>

        <Link to='/log-in' className='btn-mobile'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--small'
        >
          Prijava 
        </Button>

  </Link>
        </div>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
