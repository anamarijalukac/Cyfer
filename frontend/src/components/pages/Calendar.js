import React, {Component} from 'react'
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import ReactToPdf from "react-to-pdf";
import '../../components/pages/profile.css';
import {Button} from 'bootstrap';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';


function Calendar() {

    const [reservations, setReservations] = React.useState([]);

    const korisnik = JSON.parse(localStorage.getItem("korisnik"))
    var auth = 'Basic ' + new Buffer(korisnik.username + ':' + localStorage.getItem("password")).toString('base64');
    debugger
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
    };


    React.useEffect(() => {
        fetch('/reservations/' + korisnik.walkerId, options)
            .then(data => data.json())
            .then(reservations => {
                var walks
                var sorted = []
                for (var res in reservations) {
                    console.log(res)
                    if (reservations.hasOwnProperty(res)) {
                        console.log(res + " -> " + reservations[res]);
                        walks = reservations[res]
                        var novi = new Object()
                        novi.dogs = []
                        for (var walk in walks) {
                            var oneWalk = walks[walk]
                            novi.start = new Date(oneWalk.walk.dateTime)
                            novi.end = new Date()
                            novi.duration = oneWalk.walk.duration
                            novi.end.setTime(novi.start.getTime()+novi.duration*60*1000)
                            novi.dogs.push(oneWalk.dog.name)
                        }
                        novi.title = novi.dogs.join(" ")
                        sorted.push(novi)
                    }
                }
                const events = []
                sorted.forEach(walk => {
                    var event = []
                    event.title = walk.title
                    event.start = walk.start
                    event.end=walk.end
                    events.push(event)
                })
                debugger
                setReservations(events)
            })

    }, []);

    function printDocument() {
        const input = document.getElementById('cal');
        html2canvas(input, {scrollY: -window.scrollY})
            .then((canvas) => {
                const imgData = canvas.toDataURL('image/png', 1.0);
                const pdf = new jsPDF();
                pdf.addImage(imgData, 'JPEG', 10, 10, 180, 150);
                pdf.save("raspored-setnji.pdf");
            })
        ;
    }

    const events = [{title: "Today", date: new Date()}];


    return (
        <div>
            <div className="cal" id="cal" ng-show="cal">
                <FullCalendar
                    defaultView="dayGridMonth"
                    header={{
                        left: "prev,next",
                        center: "title",
                        right: "dayGridMonth,timeGridWeek,timeGridDay"
                    }}
                    plugins={[dayGridPlugin, timeGridPlugin]}
                    events={reservations}
                />
            </div>
            <div>
                <button className="calbtn" onClick={printDocument}>Skini u pdf-u</button>
            </div>
        </div>
    )
}

export default Calendar

