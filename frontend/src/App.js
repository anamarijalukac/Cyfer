import React from 'react';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import info from './components/pages/info';
import RangList from './components/pages/RangList';
import RegUdr from './components/pages/RegUdr';
import SignUp from './components/pages/SignUp';
import LogIn from './components/pages/LogIn';
import LogInUdr from './components/pages/LogInUdr';
import Dogs from './components/pages/Dogs';
import Dog from './components/pages/Dog';
import Profile from "./components/pages/Profile";
import Shelters from './components/pages/Shelters';
import ShelterDogs from './components/pages/ShelterDogs';
import ShelterProfile from "./components/pages/ShelterProfile";
import DogReservation from "./components/pages/DogReservation";

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(localStorage.getItem("loggedIn") === null ? false : localStorage.getItem("loggedIn"));
  const [isLoggedInUser, setIsLoggedInUser] = React.useState(localStorage.getItem("loggedInUser") === null ? false : localStorage.getItem("loggedInUser"));
  const [isLoggedInShelter, setIsLoggedInShelter] = React.useState(localStorage.getItem("loggedInShelter") === null ? false : localStorage.getItem("loggedInShelter"));

  function onLoginUser(){
    setIsLoggedIn(true);
    setIsLoggedInUser(true)
    localStorage.setItem("loggedIn", true);
    localStorage.setItem("loggedInUser", true);
  }
  function onLoginShelter(){
    setIsLoggedIn(true);
    setIsLoggedInShelter(true)
    localStorage.setItem("loggedIn", true);
    localStorage.setItem("loggedInShelter", true);
  }

  function onLogout(){
    localStorage.clear();
    setIsLoggedIn(false);
    setIsLoggedInUser(false);
    setIsLoggedInShelter(false);
  }


  /*
  if(isLoggedIn){
    return(
      <Router>
        <LoggedInNavbar onLogout={onLogout} />
        <Switch>
          <Route path='/' exact component={Home} />
          <Profile path='/Profile' component={Profile} onLogout={onLogout} />
        </Switch>
      </Router>
    )
  }
  */

  return (
    <>
      <Router>
        <Navbar isLoggedIn={isLoggedIn} onLogout={onLogout} isLoggedInUser = {isLoggedInUser} isLoggedInShelter={isLoggedInShelter}/>
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/RangList' component={RangList} />
          <RegUdr path='/RegUdr' component={RegUdr} onLogin={onLoginShelter} />
          <LogInUdr path='/LogInUdr' component={LogInUdr} onLogin={onLoginShelter}/>
          <Route path='/info' component={info} />
          <Route path='/dogs' component={Dogs} />
          <Route path='/shelters' component={Shelters} />
          {/*<Route path='/sign-up' component={SignUp} />*/}
          {/*<Route path='/log-in' component={LogIn} onLogin={onLogin}/>*/}
          <SignUp path='/sign-up' component={SignUp} onLogin={onLoginUser} />
          <LogIn path='/log-in' component={LogIn} onLogin={onLoginUser} />
          <Route path='/dog/:dogId' component={(routerProps) => <Dog dogId={routerProps.match.params.dogId}/>}/>
          <Route path='/DogReservation' component={DogReservation} />
          <Route path='/shelter/:shelterId/dogs' component={(routerProps) => <ShelterDogs shelterId={routerProps.match.params.shelterId}/>}/>
          <Route path='/Profile' component={Profile}/>
          <Route path='/shelter/info/:shelterId' component={(routerProps) => <ShelterProfile shelterId={routerProps.match.params.shelterId}/>}/>
        </Switch>
      </Router>
    </>
  );
}

export default App;
