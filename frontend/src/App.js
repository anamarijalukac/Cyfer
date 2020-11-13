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

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/RangList' component={RangList} />
          <Route path='/RegUdr' component={RegUdr} />
          <Route path='/LogInUdr' component={LogInUdr} />
          <Route path='/info' component={info} />
          <Route path='/sign-up' component={SignUp} />
          <Route path='/log-in' component={LogIn} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
