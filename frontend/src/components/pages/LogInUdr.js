import React  from "react";
import {useHistory} from 'react-router-dom';
import '../../components/pages/LogIn.css';

function LogInUdr(props) {
 
   
  let history = useHistory();
  const [form, setForm] = React.useState({username: '', password: '', checkbox: false});
  
  const[error, setError] = React.useState('');
  function onChange(event){
    const{name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name] : value}))
  }
  function onSubmit(e){
    e.preventDefault();
    setError("");
    const data = {
      username : form.username,
      password: form.password
    };
       const options = {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body: JSON.stringify(data)
    };

      fetch('/shelter/login', options)
      .then(response => {
      if(response.ok){
        localStorage.setItem("password", data.password)
        history.push('/');
        return response.json();
      }
      else{
        setForm({username: '', password: '', checkbox: false});
        setError("Neuspješna prijava");
      }
    })
    .then(data => {
        if(data === undefined){
            return;
        }
        localStorage.setItem("udruga", JSON.stringify(data))
        localStorage.setItem("lokacija", JSON.stringify(data.location))
        props.onLogin();
    })
    .catch(error => console.log(error));
  }


  return (
    <div className="container">
      <form onSubmit = {onSubmit}>
          <h1>Prijavi se kao udruga</h1>
           <div className="container">
              <label>Korisničko ime: </label>
              <input type="text" name='username' placeholder="Upiši korisničko ime" onChange = {onChange} value = {form.username} required/>
               <label>Lozinka: </label>
              <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password} required/>
               <div className="custom-control custom-checkbox">
                  <input type="checkbox" name="checkbox" onChange = {onChange} value = {form.checkbox} />
                  <span name="checkbox" htmlFor="customCheck1">   Zapamti</span>
              </div>
              {(error != "") ? <div className="error">{error}</div> : ""}
           <button className = 'loginbtn' type="submit" >Log in</button>

               <a href='./log-in' className='linkToUdruga'>Prijavljujete se kao korisnik?</a>
          </div>
      </form>
    </div>
  );
}

export default LogInUdr;