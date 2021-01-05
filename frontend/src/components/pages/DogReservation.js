import React from "react";
import {useHistory} from 'react-router-dom'
import '../../components/pages/LogIn.css';


function DogReservation(props) {

    //za dobivanje shelter id i dog id
    //props.location.state


    let history = useHistory();

    const [form, setForm] = React.useState({dateAndTime: '', duration: ''});


    function onChange(event) {
        const {name, value} = event.target;
        setForm(oldForm => ({...oldForm, [name]: value}))

    }


    function onSubmit(e) {

        e.preventDefault();

        const localWalker = JSON.parse(localStorage.getItem("korisnik"));
        let auth = 'Basic '+ new Buffer(localWalker.username + ':' + localStorage.getItem("password")).toString('base64');


        const data = {
            dateTime: form.dateAndTime + ":00.000",
            duration: form.duration,
        };

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
            body: JSON.stringify(data)
        };

        console.log(data);
        let path = '/shelter/' + props.location.state.shelterId + '/' + props.location.state.dogId + '/reserve';
        console.log(path);


        fetch(path, options)
            .then(response => {
                if (response.ok) {
                    alert("Uspješna rezervacija!");
                    history.push('/');
                } else {
                    setForm({dateAndTime: '', duration: ''});
                }
            })
            .catch(error => console.log(error));

    }


    return (
        <div class="container">
            <form onSubmit={onSubmit}>
                <h1>Rezerviraj šetnju!</h1>

                <div className="container">
                    <label>Datum i vrijeme šetnje: </label>
                    <input type="datetime-local" id="dateAndTime" name="dateAndTime"  onChange={onChange} value={form.dateAndTime}/>
                    <br/>
                    <label>Upiši trajanje šetnje: </label>
                    <input type="number" name={"duration"} onChange={onChange} value={form.duration}/>
                    <button class='loginbtn' type="submit">Rezerviraj!</button>
                </div>
            </form>
        </div>
    );
}

export default DogReservation;