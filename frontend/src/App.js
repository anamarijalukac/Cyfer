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

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(localStorage.getItem("loggedIn"));

  function onLogin(){
    setIsLoggedIn(true);
    localStorage.setItem("loggedIn", true);
    console.log("Settao sam log in");
    console.log(isLoggedIn);
  }

  function onLogout(){
    localStorage.setItem("loggedIn", false);
    setIsLoggedIn(false);
    console.log("Settao sam log out");
    console.log(isLoggedIn);
  }

  if(isLoggedIn){
    return(
      <Router>
        <LoggedInNavbar onLogout={onLogout} />
        <Switch>
          <Route path='/' exact component={Home} />
        </Switch>
      </Router>
    )
  }

  return (
    <>
      <Router>
        <Navbar onLogout={onLogout}/>
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/RangList' component={RangList} />
          <Route path='/RegUdr' component={RegUdr} />
          <Route path='/LogInUdr' component={LogInUdr} />
          <Route path='/info' component={info} />
          {/*<Route path='/sign-up' component={SignUp} />*/}
          {/*<Route path='/log-in' component={LogIn} onLogin={onLogin}/>*/}
          <SignUp path='/sign-up' component={SignUp} onLogin={onLogin} />
          <LogIn path='/log-in' component={LogIn} onLogin={onLogin} />
          <Route path='/logInRedirect' component={LogInRedirect} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
