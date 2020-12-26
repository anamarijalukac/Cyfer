import React from "react";
import {useHistory} from 'react-router-dom'
import '../../components/pages/LogIn.css';


function LogIn(props){

        let history = useHistory();

        const [form, setForm] = React.useState({username: '', password: '', checkbox: false});

        const [error, setError] = React.useState('');

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


          fetch('/walker/login', options)
          .then(response => {
            if(response.ok){
              alert("Uspješna prijava");
              props.onLogin();
              history.push('/');
            }
            else{
              setForm({username : '', password : '', checkbox : false});
              setError("Neuspješna prijava!");
            }
          }).catch(error => console.log(error));

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
                    {(error != "") ? <div className="error">{error}</div> : ""}
                <button class = 'loginbtn' type="submit" >Log in</button>

                <a href='./LogInUdr' className='linkToUdruga'>Prijavljujete se kao udruga?</a>
                </div>
            </form>
          </div>
        );
}

export default LogIn;