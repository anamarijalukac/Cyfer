import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Link, useHistory } from 'react-router-dom';
import './Navbar.css';

function Navbar(props) {

  let history = useHistory();

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
    history.push('/');

  }


  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

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
                Rang lista šetaća
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/Profile'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Profil
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
