import React, { Component, useState } from "react";
import '../../components/pages/LogIn.css';


function LogIn(){

        const [form, setForm] = React.useState({email: '', password: '', checkbox: false});

        function onChange(event){
          const{name, value} = event.target;
          setForm(oldForm => ({...oldForm, [name] : value}))
        }

        function onSubmit(e){

          e.preventDefault();

          const data = {
            email : form.email,
            password: form.password,
            checkbox: form.checkbox
          };
        
          const options = {
            method: 'POST',
            headers: {
              'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
          };

          return fetch('/log-in', options);
        }


        return (
          <div class="container">
            <form onSubmit = {onSubmit}>
                <h1>Log in</h1>

                <div className="container">
                    <label>Email: </label>
                    <input type="text" name='email' placeholder="Upiši email" onChange = {onChange} value = {form.email} required/>

                    <label>Lozinka: </label>
                    <input type="password" name='password' placeholder="Upiši lozinku" onChange = {onChange} value = {form.password} required/>

                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" name="checkbox" onChange = {onChange} value = {form.checkbox} />
                        <label name="checkbox" htmlFor="customCheck1"> Zapamti</label>
                    </div>

                <button class = 'loginbtn' type="submit" >Log in</button>
                <p name="forgot-password text-right">
                    Forgot password?
                </p>
                </div>
            </form>
          </div>
        );
}

export default LogIn;