import React, { Component } from 'react'
import FullCalendar from "@fullcalendar/react";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import ReactToPdf from "react-to-pdf";
import '../../components/pages/profile.css';

function Calendar() {
  const data = JSON.parse(localStorage.getItem("reservation"));
  const options = {
    orientation: 'landscape',
}
  console.log(data);
  const events = [{ title: "Today", date: new Date() }];
  const ref = React.createRef();
  return (
    <div className="cal" ref={ref} >
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

      <div>
        <ReactToPdf targetRef={ref} options={options} filename="raspored_setnje.pdf" x={.5} y={.7} scale={0.8}>
          {({ toPdf }) => (
            <button onClick={toPdf}>Skini u pdf-u</button>
          )}
        </ReactToPdf>
      </div>
    </div>
  )

}

export default Calendar