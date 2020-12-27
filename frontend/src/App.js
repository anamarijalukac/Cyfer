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
import LogInRedirect from './components/pages/LogInRedirect';
import LoggedInNavbar from './components/LoggedInNavbar';
import Dogs from './components/pages/Dogs';
import Dog from './components/pages/Dog';

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(localStorage.getItem("loggedIn") === null ? false : localStorage.getItem("loggedIn"));

  function onLogin(){
    setIsLoggedIn(true);
    localStorage.setItem("loggedIn", true);
  }

  function onLogout(){
    localStorage.clear();
    setIsLoggedIn(false);
  }

  console.log("Logged in is: " + isLoggedIn);
  if(isLoggedIn){
    return(
      <Router>
        <LoggedInNavbar onLogout={onLogout} />
        <Switch>
          <Route path='/' exact component={Home} />
          <LogInRedirect path='/LogInRedirect' component={LogInRedirect} onLogout={onLogout} />
        </Switch>
      </Router>
    )
  }

  return (
    <>
      <Router>
        <Navbar/>
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/RangList' component={RangList} />
          <RegUdr path='/RegUdr' component={RegUdr} onLogin={onLogin} />
          <LogInUdr path='/LogInUdr' component={LogInUdr} onLogin={onLogin}/>
          <Route path='/info' component={info} />
          <Route path='/dogs' component={Dogs} />
          {/*<Route path='/sign-up' component={SignUp} />*/}
          {/*<Route path='/log-in' component={LogIn} onLogin={onLogin}/>*/}
          <SignUp path='/sign-up' component={SignUp} onLogin={onLogin} />
          <LogIn path='/log-in' component={LogIn} onLogin={onLogin} />

          <Route path='/logInRedirect' component={LogInRedirect} />
          <Route path='/dog/:dogId' component={(routerProps) => <Dog dogId={routerProps.match.params.dogId}/>}/>

        </Switch>
      </Router>
    </>
  );
}

export default App;
