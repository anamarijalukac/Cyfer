import React from 'react';
import '../../components/pages/LogIn.css';

function RegUdr(props) {

  const [form, setForm] = React.useState({username: '', oib: '', name:'', password:'', repeatPassword:''});

  function onChange(event){
    const{name, value} = event.target;
    setForm(oldForm => ({...oldForm, [name] : value}))
  }

  function onSubmit(e){

    e.preventDefault();

    const data = {
      username: form.username,
      oib: form.oib,
      name : form.name,
      password: form.password
    };
  
    const options = {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json'
      },
      body: JSON.stringify(data)
    };

    if(form.password !== form.repeatPassword){
      return window.location.reload();
    }



    fetch('/shelter/signup', options)
    .then(response => {
      if(response.ok){
      alert("Uspješna registracija");
      props.history.push('/');
      }
      else{
        alert("Neuspješna registracija");
        window.location.reload();
      }
    }).catch(error => console.log(error));

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

          <label>Lozinka: </label>
          <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password}  required/>

          <label>Ponovi lozinku: </label>
          <input type="password" name='repeatPassword' placeholder="Ponovi lozinku" onChange = {onChange} value = {form.repeatPassword}  required/>

          <button class='loginbtn' type="submit">Registriraj se</button>

        </div>
      </form>
    </div>
  );
}

export default RegUdr;
