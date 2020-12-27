import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar(props) {
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

  function logout(){
    props.onLogout();
  }


  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

  let item = localStorage.getItem("loggedIn");
  console.log("item is " + item);

    return(
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
            </ul>
            <div className='navbar-buttons'>
                <Button
                  className='btns'
                  buttonStyle='btn--outline'
                  buttonSize='btn--small'
                  onClick={logout}
                >
                Odjava
                </Button>
            </div>
        </div>
      </nav>
    );
}

export default Navbar;
