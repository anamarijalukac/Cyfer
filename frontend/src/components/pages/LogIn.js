import React, { Component } from "react";
import '../../components/pages/LogIn.css';

function LogIn(){
        return (
          <div class="container">
            <form>
                <h1>Log in</h1>

                <div className="container">
                    <label>Email: </label>
                    <input type="text" className="form-control" placeholder="Upiši email" required/>

                    <label>Lozinka: </label>
                    <input type="password" className="form-control" placeholder="Upiši lozinku" required/>

                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                        <label className="custom-control-label" htmlFor="customCheck1"> Zapamti</label>
                    </div>

                <button class = 'loginbtn' type="submit">Log in</button>
                <p className="forgot-password text-right">
                    Forgot password?
                </p>
                </div>
            </form>
          </div>
        );
}

export default LogIn;