import React, { Component } from "react";
import '../../components/pages/LogIn.css';

function SignUp() {
  return (
    <div class="container">
      <form>
        <h1>Registracija</h1>

        <div className="container">
          <label>Ime: </label>
          <input type="text" className="form-control" placeholder required/>

          <label>Prezime: </label>
          <input type="text" className="form-control" placeholder required/>

          <label>Email: </label>
          <input type="text" className="form-control" placeholder required/>

          <label>Lozinka: </label>
          <input type="password" className="form-control" placeholder required/>

          <label>Ponovi lozinku: </label>
          <input type="password" className="form-control" placeholder required/>

          <button class='loginbtn' type="submit">Registriraj se</button>

        </div>
      </form>
    </div>
  );
}

export default SignUp;