import React from "react";
import {useHistory} from 'react-router-dom'
import '../../components/pages/LogIn.css';


function DogReservation(props) {

    //za dobivanje shelter id i dog id
    //props.location.state


    let history = useHistory();

    console.log(props.dogs)

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
            dateTime: form.dateAndTime + ":00.000+01:00",
            duration: form.duration,
        };

        debugger
        if(data.duration <= 0 || data.duration > 180) {
            alert("Trajanje šetnje mora biti veće od 0 i manje od 180 minuta.")
        }

        console.log(data)

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': auth
            },
            body: JSON.stringify(data)
        };

        console.log(data);
        var path
        if(props.dogs) {
            path = '/api/reserve/dogs'+'?'+props.dogs
        } else {
            path =  '/api/reserve/'+props.location.state.dogId;
        }
        console.log(path);


        fetch(path, options)
            .then(response => {
                if (response.ok) {
                    alert("Uspješna rezervacija!");
                    history.push('/');
                } else {
                    alert("Željeni pas/psi su već rezervirani u željenom terminu. Pokušajte ponovno.")
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
                    <label style = {{marginRight: '10px'}}>Datum i vrijeme šetnje: </label>
                    <input type="datetime-local" id="dateAndTime" name="dateAndTime"  onChange={onChange} value={form.dateAndTime}/>
                    <br/>
                    <label style = {{marginRight: '10px'}}>Trajanje šetnje u minutama: </label>
                    <input type="number" name={"duration"} onChange={onChange} value={form.duration}/>
                    <button className='loginbtn' type="submit">Rezerviraj!</button>
                </div>
            </form>
        </div>
    );
}

export default DogReservation;
