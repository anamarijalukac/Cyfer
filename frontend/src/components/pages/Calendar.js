import React, { Component } from 'react'
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import ReactToPdf from "react-to-pdf";
import '../../components/pages/profile.css';
import { Button } from 'bootstrap';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

export default class Calendar extends Component{
    constructor(props) {
        super(props);
      }
    
      printDocument() {
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

    render() {
        const events = [{ title: "Today", date: new Date() }];
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
                    <button className="calbtn" onClick={this.printDocument} >Skini u pdf-u</button>
                </div>
            </div>
        )
    }
}

