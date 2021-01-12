import React, { Component } from 'react'
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import ReactToPdf from "react-to-pdf";
import '../../components/pages/profile.css';
import { Button } from 'bootstrap';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

function Calendar(props){
    
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

      let path = '/walker/' + props.id + '/calendar';
      const localWalker = JSON.parse(localStorage.getItem("korisnik"));
      let auth = 'Basic '+ new Buffer(localWalker.username + ':' + localStorage.getItem("password")).toString('base64');

    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        }
    };

    const [events, setEvents] = React.useState([{title: "Danas", date: new Date()}]);

    React.useEffect(() => {
           fetch(path, options)
               .then(data => data.json())
               .then(data => {
                   console.log(data)
                   data.map(date =>
                   setEvents(events => [...events, {title:"Šetnja", date: new Date(date)}])
                   )
               })
      }, [])

        return (
            <div>
                <div className="cal" id="cal" ng-show="cal" >
                    <FullCalendar
                        defaultView="dayGridMonth"
                        header={{
                            left: "prev,next",
                            center: "title",
                            right: "dayGridMonth,timeGridWeek,timeGridDay"
                        }}
                        plugins={[dayGridPlugin, timeGridPlugin]}
                        events={events}
                    />
                </div>
                <div>
                    <button className="calbtn" onClick={printDocument} >Skini u pdf-u</button>
                </div>
            </div>
        )
}

export default Calendar;

