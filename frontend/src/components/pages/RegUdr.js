import React from 'react';
import {useHistory} from 'react-router-dom';
import '../../components/pages/LogIn.css';

function RegUdr(props) {

  let history = useHistory();

  const [form, setForm] = React.useState({username: '', oib: '', name:'',city:'', address:'', password:'', repeatPassword:''});
  const [error, setError] = React.useState('');

  function onChange(event){
    const{name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name] : value}))
  }

  function onSubmit(e){

    e.preventDefault();
    setError("");

    const data = {
      username: form.username,
      oib: form.oib,
      name : form.name,
      city: form.city,
      address: form.address,
      password: form.password
    };

    if(form.password !== form.repeatPassword){
      setError("Lozinke se ne poklapaju.");
      return
    }

    if(data.oib.length !== 11) {
      setError("OIB se mora sastojati od 11 znamenki.")
      return
    }
  
    const options = {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body: JSON.stringify(data)
    };


      fetch('/shelter/signup', options)
      .then(response => {
        if(response.ok){
          localStorage.setItem("password", data.password)
          history.push('/');
          return response.json();
        }
        else{
          //setForm({username: '', oib: '', name:'',city:'', address:'', password:'', repeatPassword:''});
          if(response.status === 409)
            setError("Neuspješna registracija - korisničko ime je već zauzeto.")
          else if(response.status === 406)
            setError("Neuspješna registracija - postoji već udruga sa danim OIB-om.");
          else
            setError("Neuspješna registracija.")
        }
      })
      .then(data => {
        if(data === undefined){
          return;
        }
        localStorage.setItem("udruga", JSON.stringify(data))
        props.onLogin();
      })
      .catch(error => console.log(error));


  }


  return (
    <div class="container">
      <form onSubmit = {onSubmit}>
        <h1>Registracija udruge</h1>

        <div className="container">
          <label>Korisničko ime: </label>
          <input type="text" name='username' placeholder = "Upiši korisničko ime" onChange = {onChange} value = {form.username} required/>

          <label>OIB: </label>
          <input type="text" name='oib' placeholder = "Upiši OIB udruge" onChange = {onChange} value = {form.oib} required/>

          <label>Ime udruge: </label>
          <input type="text" name='name' placeholder="Upiši ime udruge" onChange = {onChange} value = {form.name}  required/>

          <label>Grad udruge: </label>
          <input type="text" name='city' placeholder="Upiši grad udruge" onChange = {onChange} value = {form.city}  required/>

          <label>Adresa udruge: </label>
          <input type="text" name='address' placeholder="Upiši adresu udruge" onChange = {onChange} value = {form.address}  required/>

          <label>Lozinka: </label>
          <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password}  required/>

          <label>Ponovi lozinku: </label>
          <input type="password" name='repeatPassword' placeholder="Ponovi lozinku" onChange = {onChange} value = {form.repeatPassword}  required/>
          {(error != "") ? <div className="error">{error}</div> : ""}
          <button class='loginbtn' type="submit">Registriraj se</button>

          <a href='./sign-up' className='linkToUdruga'>Želite se registrirati kao korisnik?</a>

        </div>
      </form>
    </div>
  );
}

export default RegUdr;
