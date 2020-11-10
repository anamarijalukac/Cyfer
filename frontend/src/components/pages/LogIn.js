import React from "react";
import '../../components/pages/LogIn.css';


function LogIn(){

        const [form, setForm] = React.useState({username: '', password: '', checkbox: false});

        function onChange(event){
          const{name, value} = event.target;
          setForm(oldForm => ({...oldForm, [name] : value}))
        }

        function onSubmit(e){

          e.preventDefault();

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

          return fetch('/walkers/login', options);
        }


        return (
          <div class="container">
            <form onSubmit = {onSubmit}>
                <h1>Prijavi se kao korisnik</h1>

                <div className="container">
                    <label>Korisničko ime: </label>
                    <input type="text" name='username' placeholder="Upiši korisničko ime" onChange = {onChange} value = {form.username} required/>

                    <label>Lozinka: </label>
                    <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password} required/>

                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" name="checkbox" onChange = {onChange} value = {form.checkbox} />
                        <label name="checkbox" htmlFor="customCheck1"> Zapamti</label>
                    </div>

                <button class = 'loginbtn' type="submit" >Log in</button>
                </div>
            </form>
          </div>
        );
}

export default LogIn;