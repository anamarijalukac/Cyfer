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
import EditProfile from "./components/pages/EditProfile"
import EditDog from "./components/pages/EditDog";
import Calendar from "./components/pages/Calendar";
import {Link, useHistory} from 'react-router-dom';

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(localStorage.getItem("loggedIn") === null ? false : localStorage.getItem("loggedIn"));
  const [isLoggedInUser, setIsLoggedInUser] = React.useState(localStorage.getItem("loggedInUser") === null ? false : localStorage.getItem("loggedInUser"));
  const [isLoggedInShelter, setIsLoggedInShelter] = React.useState(localStorage.getItem("loggedInShelter") === null ? false : localStorage.getItem("loggedInShelter"));
  const [id, setId] = React.useState("");
  const [params, setParams] = React.useState("");
  const[walker, setWalker] = React.useState();
  const[shelter, setShelter] = React.useState();

  let history = useHistory();
  debugger

  function onLoginUser(){
    setIsLoggedIn(true);
    setIsLoggedInUser(true)
    localStorage.setItem("loggedIn", true);
    localStorage.setItem("loggedInUser", true);
    setId(JSON.parse(localStorage.getItem("korisnik")).id)
    setWalker(JSON.parse(localStorage.getItem("korisnik")))
  }
  function onLoginShelter(){
    setIsLoggedIn(true);
    setIsLoggedInShelter(true)
    localStorage.setItem("loggedIn", true);
    localStorage.setItem("loggedInShelter", true);
    setId(JSON.parse(localStorage.getItem("udruga")).shelterId)
    setShelter(JSON.parse(localStorage.getItem("udruga")))
    debugger
  }

  function onLogout(){
    localStorage.clear();
    setIsLoggedIn(false);
    setIsLoggedInUser(false);
    setIsLoggedInShelter(false);
    setShelter(null)
    setWalker(null)
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
        <Navbar isLoggedIn={isLoggedIn} onLogout={onLogout} isLoggedInUser = {isLoggedInUser} isLoggedInShelter={isLoggedInShelter} id={id}/>
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/RangList' component={RangList} />
          <RegUdr path='/RegUdr' component={RegUdr} onLogin={onLoginShelter} />
          <LogInUdr path='/LogInUdr' component={LogInUdr} onLogin={onLoginShelter}/>
          <Route path='/info' component={info} />
          <Dogs path='/dogs' component={Dogs} history={history} dog={"dog"} setParams={setParams}/>
          <Route path='/shelters' component={Shelters} />
          <SignUp path='/sign-up' component={SignUp} onLogin={onLoginUser} />
          <LogIn path='/log-in' component={LogIn} onLogin={onLoginUser} />
          <Route path='/dog/edit/:dogId' component={(routerProps) => <EditDog dogId={routerProps.match.params.dogId}/>}/>
          <Route Dog path='/dog/:dogId' component={(routerProps) => <Dog dogId={routerProps.match.params.dogId}/>}/>
          <Route path='/DogReservation' component={DogReservation} params={{params}}/>
          <Route path='/multipleDogReservation/:dogs' component={(routerProps) => <DogReservation dogs={routerProps.match.params.dogs}/>}/>
          <Route path='/shelter/:shelterId/dogs' component={(routerProps) => <ShelterDogs shelterId={routerProps.match.params.shelterId}/>}/>
          <Profile path='/profile' component={Profile} onLogout={onLogout} shelter={shelter} walker={walker} />
          <Route path='/shelter/info/:shelterId' component={(routerProps) => <ShelterProfile shelterId={routerProps.match.params.shelterId}/>}/>
          <Route EditProfile path='/walker/update/:walkerId' component={(routerProps) => <EditProfile id={routerProps.match.params.walkerId} onLoginUser={onLoginUser}/>}/>
          <Route EditProfile path='/shelter/update/:shelterId' component={(routerProps) => <EditProfile id={routerProps.match.params.shelterId} onLoginShelter={onLoginShelter}/>}/>
          <Route path='/walker/:walkerId/calendar' component={(routerProps) => <Calendar id={routerProps.match.params.walkerId}/>}/>
        </Switch>
      </Router>
    </>
  );
}

export default App;
