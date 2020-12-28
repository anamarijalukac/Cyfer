import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import {Link, useHistory} from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  let history = useHistory();
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

  var isShelter, isWalker;
  if(localStorage.getItem("udruga") !== null && localStorage.getItem("udruga") !== undefined) {
    isShelter = true;
  }
  if(localStorage.getItem("korisnik") !== null && localStorage.getItem("korisnik") !== undefined) {
    isWalker = true;
  }

  console.log("shelter " + isShelter)
  console.log("walker " + isWalker)


  const login =  <Link to='/log-in' className='btn-mobile'>
    <Button className='btns' buttonStyle='btn--outline' buttonSize='btn--small'>
      Prijava </Button></Link>


  const registrate = <Link to='/sign-up' className='btn-mobile'>
      <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--small'
      >
        Registracija
      </Button>
    </Link>

  var shelterId = ""
  if(isShelter)
    shelterId = localStorage.getItem("udruga").shelterId

    const listaPasaUdruge = <li className='nav-item'>
      <Link
          to={'/shelter/'+ shelterId + '/dogs'}
          className='nav-links'
          onClick={closeMobileMenu}
      >
        Lista naših pasa
      </Link>
    </li>

    const listaPasa = <li className='nav-item'>
    <Link
        to='/dogs'
        className='nav-links'
        onClick={closeMobileMenu}
    >
      Lista pasa
    </Link>
  </li>

  const listaUdruga = <li className='nav-item'>
    <Link
        to='/shelters'
        className='nav-links'
        onClick={closeMobileMenu}
    >
      Lista udruga
    </Link>
  </li>

  const rangListaŠetača =  <li className='nav-item'>
    <Link
        to='/RangList'
        className='nav-links'
        onClick={closeMobileMenu}
    >
      Rang lista šetača
    </Link>
  </li>

  const odjava = <Button
      className='btns'
      buttonStyle='btn--outline'
      buttonSize='btn--small'
      onClick={logout}
  >
    Odjava
  </Button>

  function logout(){
    localStorage.clear();
    history.push('/');
  }

  const profile = <li className='nav-item'>
    <Link
        to='/Profile'
        className='nav-links'
        onClick={closeMobileMenu}
    >
      Profil
    </Link>
  </li>









  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            ŠAPA
            <i className='fas fa-paw' />
          </Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            {listaPasa}
            {isShelter && listaPasaUdruge}
            {listaUdruga}
            {rangListaŠetača}
            {localStorage.getItem("loggedIn") && profile}






          </ul>

          <div className='navbar-buttons'>
            {!localStorage.getItem("loggedIn") && login }
            {!localStorage.getItem("loggedIn") && registrate }
            {localStorage.getItem("loggedIn") && odjava }
        </div>
        </div>
      </nav>
    </>
  );
}

export default Navbar;

