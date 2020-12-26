import React from "react";
import '../../components/pages/LogIn.css';

function SignUp(props) {

  const [form, setForm] = React.useState({username: '', firstName: '', lastName: '', email:'', password:'', repeatPassword:''});

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
      firstName: form.firstName,
      lastName: form.lastName,
      email : form.email,
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

    fetch('/walker/signup', options)
    .then(response => {
      if(response.ok){
      alert("Uspješna registracija");
      props.history.push('/');
      }
      else{
        setError("Neuspješna registracija!");
        setForm({username: '', firstName: '', lastName: '', email:'', password:'', repeatPassword:''});
      }
    }).catch(error => console.log(error));
    

  }



  return (
    <div class="container">
      <form onSubmit = {onSubmit}>
        <h1>Registracija korisnika</h1>

        <div className="container">
          <label>Korisničko ime: </label>
          <input type="text" name='username' placeholder = "Upiši korisničko ime" onChange = {onChange} value = {form.username} required/>

          <label>Ime: </label>
          <input type="text" name='firstName' placeholder = "Upiši ime" onChange = {onChange} value = {form.firstName} required/>

          <label>Prezime: </label>
          <input type="text" name='lastName' placeholder = "Upiši prezime" onChange = {onChange} value = {form.lastName} required/>

          <label>E-mail: </label>
          <input type="text" name='email' placeholder="Upiši e-mail" onChange = {onChange} value = {form.email}  required/>

          <label>Lozinka: </label>
          <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password}  required/>

          <label>Ponovi lozinku: </label>
          <input type="password" name='repeatPassword' placeholder="Ponovi lozinku" onChange = {onChange} value = {form.repeatPassword}  required/>
          {(error != "") ? <div className="error">{error}</div> : ""}
          <button class='loginbtn' type="submit">Registriraj se</button>

          <a href='./RegUdr' className='linkToUdruga'>Želite registrirati udrugu?</a>

        </div>
      </form>
    </div>
  );
}

export default SignUp;